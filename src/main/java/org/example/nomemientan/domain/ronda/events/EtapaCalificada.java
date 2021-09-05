package org.example.nomemientan.domain.ronda.events;

import co.com.sofka.domain.generic.DomainEvent;
import org.example.nomemientan.domain.ronda.values.EtapaId;

public class EtapaCalificada extends DomainEvent {
    private final EtapaId etapaId;

    public EtapaCalificada(EtapaId etapaId) {
        super("nomemientan.ronda.etapacalificada");
        this.etapaId = etapaId;
    }

    public EtapaId getEtapaId() {
        return etapaId;
    }
}
