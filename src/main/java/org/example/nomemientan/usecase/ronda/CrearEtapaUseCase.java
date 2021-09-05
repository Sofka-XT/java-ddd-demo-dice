package org.example.nomemientan.usecase.ronda;

import co.com.sofka.business.annotation.EventListener;
import co.com.sofka.business.generic.UseCase;
import co.com.sofka.business.support.ResponseEvents;
import co.com.sofka.business.support.TriggeredEvent;
import org.example.nomemientan.domain.ronda.Ronda;
import org.example.nomemientan.domain.ronda.events.EtapaCalificada;
import org.example.nomemientan.domain.ronda.values.RondaId;

@EventListener(eventType = "nomemientan.ronda.etapacalificada")
public class CrearEtapaUseCase extends UseCase<TriggeredEvent<EtapaCalificada>, ResponseEvents> {
    @Override
    public void executeUseCase(TriggeredEvent<EtapaCalificada> input) {
        var event = input.getDomainEvent();
        var ronda = Ronda.from(RondaId.of(event.aggregateRootId()), retrieveEvents());

        if(hasGanador(ronda)){
            ronda.finalizarRonda();
        } else {
            ronda.crearNuevaEtapa();
        }

        emit().onResponse(new ResponseEvents(ronda.getUncommittedChanges()));
    }

    private boolean hasGanador(Ronda ronda) {
        var event = request().getDomainEvent();
        return ronda.etapas().get(event.getEtapaId()).ganadores().size() == 1;
    }

}
