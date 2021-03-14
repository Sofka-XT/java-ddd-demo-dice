package org.example.nomemientan.usecase.juego;

import co.com.sofka.business.generic.UseCaseHandler;
import co.com.sofka.business.repository.DomainEventRepository;
import co.com.sofka.business.support.RequestCommand;
import co.com.sofka.domain.generic.DomainEvent;
import org.example.nomemientan.domain.juego.command.InicializarJuego;
import org.example.nomemientan.domain.juego.events.JuegoCreado;
import org.example.nomemientan.domain.juego.events.JuegoInicializado;
import org.example.nomemientan.domain.juego.events.JugadorAdicionado;
import org.example.nomemientan.domain.juego.values.Capital;
import org.example.nomemientan.domain.juego.values.JuegoId;
import org.example.nomemientan.domain.juego.values.JugadorId;
import org.example.nomemientan.domain.juego.values.Nombre;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class InicializarJuegoUseCaseTest {

    @Mock
    private DomainEventRepository repository;

    @Test
    void inicializarJuego() {
        var id = JuegoId.of("xxxx");
        var command = new InicializarJuego(id);
        var useCase = new InicializarJuegoUseCase();

        when(repository.getEventsBy(id.value())).thenReturn(eventStored(id));
        useCase.addRepository(repository);

        var events = UseCaseHandler.getInstance()
                .setIdentifyExecutor(id.value())
                .syncExecutor(useCase, new RequestCommand<>(command))
                .orElseThrow()
                .getDomainEvents();

        var juegoInicializado = (JuegoInicializado) events.get(0);
        Assertions.assertEquals(3, juegoInicializado.getJugadoresIds().size());

    }

    private List<DomainEvent> eventStored(JuegoId id) {
        return List.of(
                new JuegoCreado(id),
                new JugadorAdicionado(JugadorId.of("ffff"), new Nombre("Raul Alzate"), new Capital(500)),
                new JugadorAdicionado(JugadorId.of("gggg"), new Nombre("Raul Alzate"), new Capital(500)),
                new JugadorAdicionado(JugadorId.of("hhhh"), new Nombre("Raul Alzate"), new Capital(500))
        );
    }
}