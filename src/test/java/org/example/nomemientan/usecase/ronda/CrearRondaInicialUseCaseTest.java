package org.example.nomemientan.usecase.ronda;


import co.com.sofka.business.generic.BusinessException;
import co.com.sofka.business.generic.UseCaseHandler;
import co.com.sofka.business.support.TriggeredEvent;
import org.example.nomemientan.domain.juego.events.JuegoInicializado;
import org.example.nomemientan.domain.juego.values.JugadorId;
import org.example.nomemientan.domain.ronda.events.RondaCreada;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Set;

class CrearRondaInicialUseCaseTest {

    @Test
    void crearRonda() {
        var event = new JuegoInicializado(Set.of(JugadorId.of("xxx"), JugadorId.of("fff")));
        event.setAggregateRootId("hhhhhh");
        var useCase = new CrearRondaInicialUseCase();

        var events = UseCaseHandler
                .getInstance()
                .syncExecutor(useCase, new TriggeredEvent<>(event))
                .orElseThrow()
                .getDomainEvents();

        var rondaCreada = (RondaCreada) events.get(0);
        Assertions.assertEquals("hhhhhh", rondaCreada.getJuegoId().value());
        Assertions.assertEquals(2, rondaCreada.getJugadorIds().size());
    }

    @Test
    void rondaConError() {
        var event = new JuegoInicializado(Set.of(JugadorId.of("xxx")));
        event.setAggregateRootId("hhhhhh");
        var useCase = new CrearRondaInicialUseCase();

        Assertions.assertThrows(BusinessException.class, () -> UseCaseHandler
                .getInstance()
                .syncExecutor(useCase, new TriggeredEvent<>(event))
                .orElseThrow(), "No se puede crear la ronda por falta de jugadores");

    }

}