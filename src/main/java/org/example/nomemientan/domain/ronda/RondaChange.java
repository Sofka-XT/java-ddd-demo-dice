package org.example.nomemientan.domain.ronda;

import co.com.sofka.domain.generic.EventChange;
import org.example.nomemientan.domain.ronda.events.DadosLanzados;
import org.example.nomemientan.domain.ronda.events.EtapaCreada;
import org.example.nomemientan.domain.ronda.events.RondaCreada;
import org.example.nomemientan.domain.ronda.values.DadoId;

import java.util.HashMap;

public class RondaChange extends EventChange {
    public RondaChange(Ronda ronda) {
        apply((RondaCreada event) -> {
            ronda.juegoId = event.getJuegoId();
            ronda.dados = new HashMap<>();
            ronda.etapas = new HashMap<>();
            ronda.puntaje = new HashMap<>();
            ronda.jugadorIds = event.getJugadorIds();

            for (var i = 1; i <= 6; i++) {//inicializar dados
                ronda.dados.put(DadoId.of(i), new Dado(DadoId.of(i)));
            }
        });

        apply((DadosLanzados event) -> {
            ronda.dados.values().forEach(Dado::lanzarDado);
        });

        apply((EtapaCreada event) -> {
            ronda.etapas.put(event.getEtapaId(),
                    new Etapa(event.getEtapaId(), event.getCarasVisibles())
            );
        });
    }
}
