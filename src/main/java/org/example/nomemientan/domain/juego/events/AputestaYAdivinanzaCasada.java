package org.example.nomemientan.domain.juego.events;

import co.com.sofka.domain.generic.DomainEvent;
import org.example.nomemientan.domain.juego.values.JugadorId;
import org.example.nomemientan.domain.ronda.values.Case;
import org.example.nomemientan.domain.ronda.values.EtapaId;
import org.example.nomemientan.domain.ronda.values.RondaId;

public class AputestaYAdivinanzaCasada extends DomainEvent {
    private final JugadorId jugadorId;
    private final RondaId rondaId;
    private final EtapaId etapaId;
    private final Case aCase;

    public AputestaYAdivinanzaCasada(JugadorId jugadorId, RondaId rondaId, EtapaId etapaId, Case aCase) {
        super("nomemientan.juego.aputestayadivinanzacasada");
        this.jugadorId = jugadorId;
        this.rondaId = rondaId;
        this.etapaId = etapaId;
        this.aCase = aCase;
    }

    public JugadorId getJugadorId() {
        return jugadorId;
    }

    public EtapaId getEtapaId() {
        return etapaId;
    }

    public Case getaCase() {
        return aCase;
    }

    public RondaId getRondaId() {
        return rondaId;
    }
}
