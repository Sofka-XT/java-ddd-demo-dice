package org.example.nomemientan.usecase.ronda;

import co.com.sofka.business.generic.UseCaseHandler;
import co.com.sofka.business.repository.DomainEventRepository;
import co.com.sofka.business.support.TriggeredEvent;
import co.com.sofka.domain.generic.DomainEvent;
import org.example.nomemientan.domain.juego.values.JuegoId;
import org.example.nomemientan.domain.juego.values.JugadorId;
import org.example.nomemientan.domain.ronda.events.DadosLanzados;
import org.example.nomemientan.domain.ronda.events.RondaCreada;
import org.example.nomemientan.domain.ronda.events.RondaInicializada;
import org.example.nomemientan.domain.ronda.values.Cara;
import org.example.nomemientan.domain.ronda.values.RondaId;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Set;

import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class LanzarDadoUseCaseTest {

    private final Set<JugadorId> jugadoresIds = Set.of(
            JugadorId.of("gggg"), JugadorId.of("ttttt")
    );
    private final JuegoId juegoId = JuegoId.of("ffff");

    @Mock
    private DomainEventRepository repository;

    @Test
    void lanzarDadosDeLaRonda() {
        var rondaId = RondaId.of("aaaaa");
        var event = createTriggeredEventWith(rondaId);

        var useCase = new LanzarDadoUseCase();
        when(repository.getEventsBy(rondaId.value())).thenReturn(eventStored());
        useCase.addRepository(repository);

        var events = executor(rondaId, event, useCase);
        var dadosLanzados = (DadosLanzados) events.get(0);

        Assertions.assertEquals(juegoId, dadosLanzados.getJuegoId());
        Assertions.assertEquals(6, dadosLanzados.getCarasList().size());
        dadosLanzados.getCarasList().forEach(dadoIdListMap -> {
            List<Cara> list = ((List<Cara>) dadoIdListMap.values().toArray()[0]);
            Assertions.assertEquals(6, list.size(), "No se tiene las 6 caras del dado");
            for (Cara cara : list) {
                Assertions.assertTrue(() -> cara.value() > 0 && 6 >= cara.value(), "El valor de la cara no esta entre 1 y 6");
            }
        });
    }

    private RondaInicializada createTriggeredEventWith(RondaId rondaId) {
        var event = new RondaInicializada(juegoId, jugadoresIds);
        event.setAggregateRootId(rondaId.value());
        return event;
    }

    private List<DomainEvent> executor(RondaId rondaId, RondaInicializada event, LanzarDadoUseCase useCase) {
        return UseCaseHandler
                .getInstance()
                .setIdentifyExecutor(rondaId.toString())
                .syncExecutor(useCase, new TriggeredEvent<>(event))
                .orElseThrow()
                .getDomainEvents();
    }

    private List<DomainEvent> eventStored() {
        return List.of(
                new RondaCreada(jugadoresIds, juegoId),
                new RondaInicializada(juegoId, jugadoresIds)
        );
    }
}