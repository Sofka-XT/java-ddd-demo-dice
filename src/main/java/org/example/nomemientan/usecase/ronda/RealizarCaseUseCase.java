package org.example.nomemientan.usecase.ronda;

import co.com.sofka.business.generic.UseCase;
import co.com.sofka.business.support.ResponseEvents;
import co.com.sofka.business.support.TriggeredEvent;
import org.example.nomemientan.domain.juego.events.AputestaYAdivinanzaCasada;
import org.example.nomemientan.domain.ronda.Ronda;

public class RealizarCaseUseCase extends UseCase<TriggeredEvent<AputestaYAdivinanzaCasada>, ResponseEvents> {
    @Override
    public void executeUseCase(TriggeredEvent<AputestaYAdivinanzaCasada> input) {
        var event = input.getDomainEvent();
        var ronda = Ronda.from(event.getRondaId(), retrieveEvents());

        ronda.realizarCasePorJugador(
                event.getJugadorId(),
                event.getEtapaId(),
                event.getaCase()
        );

        emit().onResponse(new ResponseEvents(ronda.getUncommittedChanges()));
    }
}
