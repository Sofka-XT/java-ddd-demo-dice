package org.example.nomemientan.usecase;

import co.com.sofka.business.generic.BusinessException;
import co.com.sofka.business.generic.UseCase;
import co.com.sofka.business.support.ResponseEvents;
import co.com.sofka.business.support.TriggeredEvent;
import org.example.nomemientan.domain.juego.Juego;
import org.example.nomemientan.domain.ronda.events.RondaFinalizada;

public class NuevaRondaUseCase extends UseCase<TriggeredEvent<RondaFinalizada>, ResponseEvents> {
    @Override
    public void executeUseCase(TriggeredEvent<RondaFinalizada> input) {
        var event = input.getDomainEvent();
        var juego = Juego.from(event.getJuegoId(), retrieveEvents());

        try {
            if(juego.tieneGanador().equals(Boolean.FALSE)) {
                juego.crearNuevaRonda();
            }
            emit().onResponse(new ResponseEvents(juego.getUncommittedChanges()));
        } catch (RuntimeException e){
            //TODO: falta cubrir esta linea de codigo
            emit().onError(new BusinessException(event.getJuegoId().value(), e.getMessage()));
        }
    }
}
