package org.example.nomemientan.usecase.juego;

import co.com.sofka.business.generic.UseCase;
import co.com.sofka.business.support.ResponseEvents;
import co.com.sofka.business.support.TriggeredEvent;
import org.example.nomemientan.domain.juego.Juego;
import org.example.nomemientan.domain.ronda.events.EtapaCreada;
import org.example.nomemientan.domain.ronda.values.RondaId;
import org.example.nomemientan.usecase.juego.service.CaseJugadorService;


public class SolicitarCaseDelJugadorUseCase extends UseCase<TriggeredEvent<EtapaCreada>, ResponseEvents> {
    @Override
    public void executeUseCase(TriggeredEvent<EtapaCreada> input) {
        var event = input.getDomainEvent();
        var juego = Juego.from(event.getJuegoId(), retrieveEvents());
        var service = getService(CaseJugadorService.class).orElseThrow();
        var rondaId = RondaId.of(event.aggregateRootId());

        event.getJugadorIds().forEach(jugadorId -> {
            var aCase = service.getCasePor(jugadorId, event.getCarasVisibles());
            juego.deducirCapitalDelJugador(jugadorId, aCase.value().apuesta());
            juego.casarApuestaEnEtapa(
                    jugadorId, rondaId, event.getEtapaId(), aCase
            );
        });

        emit().onResponse(new ResponseEvents(juego.getUncommittedChanges()));
    }
}
