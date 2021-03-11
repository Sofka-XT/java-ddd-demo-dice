package org.example.nomemientan.usecase;

import co.com.sofka.business.generic.UseCaseHandler;
import co.com.sofka.business.repository.DomainEventRepository;
import co.com.sofka.business.support.TriggeredEvent;
import co.com.sofka.domain.generic.DomainEvent;
import org.example.nomemientan.domain.juego.Jugador;
import org.example.nomemientan.domain.juego.events.JuegoCreado;
import org.example.nomemientan.domain.juego.events.JuegoFinalizadoConGanador;
import org.example.nomemientan.domain.juego.events.JuegoIniciado;
import org.example.nomemientan.domain.juego.events.NuevaRondaCreada;
import org.example.nomemientan.domain.juego.values.JuegoId;
import org.example.nomemientan.domain.juego.values.JugadorId;
import org.example.nomemientan.domain.juego.values.Name;
import org.example.nomemientan.domain.ronda.events.RondaFinalizada;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Map;
import java.util.Objects;

import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class NuevaRondaUseCaseTest {
    @Mock
    private DomainEventRepository repository;

    @Test
    void crearNuevaRonda(){
        var juegoId = JuegoId.of("ffff");

        var nuevaRondaUseCase = new NuevaRondaUseCase();

        when(repository.getEventsBy(juegoId.value())).thenReturn(eventStored_JuegoInicializado());
        nuevaRondaUseCase.addRepository(repository);

        var events = UseCaseHandler.getInstance()
                .setIdentifyExecutor(juegoId.value())
                .syncExecutor(nuevaRondaUseCase, new TriggeredEvent<>(new RondaFinalizada(juegoId)))
                .orElseThrow()
                .getDomainEvents();

        var event = (NuevaRondaCreada)events.get(0);

        Assertions.assertEquals(3, event.getJugadoresIds().size());
        Assertions.assertTrue(Objects.nonNull(event.getRondaId().value()));
    }

    @Test
    void intentoDeCrearRonda(){
        var juegoId = JuegoId.of("ffff");

        var nuevaRondaUseCase = new NuevaRondaUseCase();

        when(repository.getEventsBy(juegoId.value())).thenReturn(eventStored_ConGanador());
        nuevaRondaUseCase.addRepository(repository);

        var events = UseCaseHandler.getInstance()
                .setIdentifyExecutor(juegoId.value())
                .syncExecutor(nuevaRondaUseCase, new TriggeredEvent<>(new RondaFinalizada(juegoId)))
                .orElseThrow()
                .getDomainEvents();

        Assertions.assertEquals(0, events.size());
    }

    private List<DomainEvent> eventStored_JuegoInicializado() {
        return List.of(
                new JuegoCreado(Map.of(
                    JugadorId.of("xxxx"), new Jugador(JugadorId.of("xxxx"), new Name("Raul Alzate")),
                    JugadorId.of("ffff"), new Jugador(JugadorId.of("ffff"), new Name("Andres")),
                    JugadorId.of("tttt"), new Jugador(JugadorId.of("tttt"), new Name("Camilo")))
                ),
                new JuegoIniciado());
    }

    private List<DomainEvent> eventStored_ConGanador() {
        return List.of(
                new JuegoCreado(Map.of(
                        JugadorId.of("xxxx"), new Jugador(JugadorId.of("xxxx"), new Name("Raul Alzate")),
                        JugadorId.of("ffff"), new Jugador(JugadorId.of("ffff"), new Name("Andres")),
                        JugadorId.of("tttt"), new Jugador(JugadorId.of("tttt"), new Name("Camilo")))
                ),
                new JuegoIniciado(),
                new JuegoFinalizadoConGanador(JugadorId.of("xxxx"), new Name("Raul Alzate"))
        );
    }
}