package org.example.nomemientan.domain.ronda.events;

import co.com.sofka.domain.generic.DomainEvent;
import org.example.nomemientan.domain.juego.values.JuegoId;
import org.example.nomemientan.domain.ronda.values.EtapaId;

public class EtapaCreada extends DomainEvent {
    private final JuegoId juegoId;
    private final EtapaId etapaId;

    public EtapaCreada(JuegoId juegoId, EtapaId etapaId) {
        super("nomemientan.ronda.etapacreada");
        this.juegoId = juegoId;
        this.etapaId = etapaId;
    }

    public JuegoId getJuegoId() {
        return juegoId;
    }

    public EtapaId getEtapaId() {
        return etapaId;
    }
}
