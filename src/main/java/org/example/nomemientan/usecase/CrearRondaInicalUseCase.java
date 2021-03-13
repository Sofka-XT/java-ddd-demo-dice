package org.example.nomemientan.usecase;

import co.com.sofka.business.generic.BusinessException;
import co.com.sofka.business.generic.UseCase;
import co.com.sofka.business.support.ResponseEvents;
import co.com.sofka.business.support.TriggeredEvent;
import org.example.nomemientan.domain.juego.events.JuegoInicializado;
import org.example.nomemientan.domain.juego.values.JuegoId;
import org.example.nomemientan.domain.ronda.values.RondaId;
import org.example.nomemientan.domain.ronda.Ronda;

public class CrearRondaInicalUseCase extends UseCase<TriggeredEvent<JuegoInicializado>, ResponseEvents> {
    @Override
    public void executeUseCase(TriggeredEvent<JuegoInicializado> input) {
        var event = input.getDomainEvent();
        var rondaId = new RondaId();
        var juegoId = JuegoId.of(event.aggregateRootId());
        var ronda = new Ronda(rondaId, juegoId, event.getJugadoresIds());

        if (event.getJugadoresIds().size() < 2) {
            throw new BusinessException(rondaId.value(), "No se puede crear la ronda por falta de jugadores");
        }
        emit().onResponse(new ResponseEvents(ronda.getUncommittedChanges()));
    }
}
