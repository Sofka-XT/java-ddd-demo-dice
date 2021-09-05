package org.example.nomemientan.domain.ronda;

import co.com.sofka.domain.generic.EventChange;
import org.example.nomemientan.domain.ronda.events.*;
import org.example.nomemientan.domain.ronda.values.DadoId;

import java.util.ArrayList;
import java.util.HashMap;

public class RondaChange extends EventChange {
    public RondaChange(Ronda ronda) {
        apply((RondaCreada event) -> {
            ronda.juegoId = event.getJuegoId();
            ronda.dados = new HashMap<>();
            ronda.etapas = new HashMap<>();
            ronda.puntaje = new HashMap<>();
            ronda.caras = new ArrayList<>();
            ronda.jugadorIds = event.getJugadorIds();

            for (var i = 1; i <= 6; i++) {//inicializar dados
                ronda.dados.put(DadoId.of(i), new Dado(DadoId.of(i)));
            }
        });

        apply((DadosLanzados event) -> {
            ronda.dados.values().forEach(dado -> {
                dado.lanzarDado();
                ronda.caras.add(dado.caras().get(0));//selecciona la primera cara
            });
        });

        apply((EtapaCreada event) -> {
            ronda.etapas.put(event.getEtapaId(),
                    new Etapa(event.getEtapaId(), event.getCarasVisibles())
            );
        });

        apply((CaseRealizadoDelJugador event) -> {
            ronda.etapas.get(event.getEtapaId())
                    .aggregarCase(event.getJugadorId(), event.getCase());
        });

        apply((EtapaCalificada event) -> {
            var etapa = ronda.etapas.get(event.getEtapaId());
            etapa.calificar();
            ronda.jugadorIds.clear();
            ronda.jugadorIds.addAll(etapa.ganadores());
        });

        apply((RondaFinalizada event) -> {
            ronda.juegoId = event.getJuegoId();
            ronda.etapas.clear();
            ronda.puntaje.clear();
            ronda.caras.clear();
            ronda.jugadorIds.clear();
            ronda.dados.clear();
        });
    }
}
