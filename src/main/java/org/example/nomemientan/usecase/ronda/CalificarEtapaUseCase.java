package org.example.nomemientan.usecase.ronda;

import co.com.sofka.business.annotation.EventListener;
import co.com.sofka.business.generic.UseCase;
import co.com.sofka.business.support.ResponseEvents;
import co.com.sofka.business.support.TriggeredEvent;
import org.example.nomemientan.domain.ronda.Ronda;
import org.example.nomemientan.domain.ronda.events.CaseRealizadoDelJugador;
import org.example.nomemientan.domain.ronda.values.RondaId;

@EventListener(eventType = "nomemientan.ronda.aaserealizadodeljugador")
public class CalificarEtapaUseCase extends UseCase<TriggeredEvent<CaseRealizadoDelJugador>, ResponseEvents> {
    @Override
    public void executeUseCase(TriggeredEvent<CaseRealizadoDelJugador> input) {
        var event = input.getDomainEvent();
        var ronda = Ronda.from(RondaId.of(event.aggregateRootId()), retrieveEvents());

        if(todosLosJugadoresCasaronPara(ronda)){
            ronda.calificarRonda(event.getEtapaId());
            emit().onResponse(new ResponseEvents(ronda.getUncommittedChanges()));
        }
    }

    private boolean todosLosJugadoresCasaronPara(Ronda ronda) {
        var event = request().getDomainEvent();
        var numJugadores = ronda.jugadorIds().size();
        var etapa = ronda.etapas().get(event.getEtapaId());
        var numCases =  etapa.cases().values().size();

        return numJugadores == numCases;
    }
}
