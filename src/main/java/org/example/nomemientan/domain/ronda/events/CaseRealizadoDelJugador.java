package org.example.nomemientan.domain.ronda.events;

import co.com.sofka.domain.generic.DomainEvent;
import org.example.nomemientan.domain.juego.values.JuegoId;
import org.example.nomemientan.domain.juego.values.JugadorId;
import org.example.nomemientan.domain.ronda.values.Case;
import org.example.nomemientan.domain.ronda.values.EtapaId;

public class CaseRealizadoDelJugador extends DomainEvent {
    private final JuegoId juegoId;
    private final EtapaId etapaId;
    private final JugadorId jugadorId;
    private final Case aCase;

    public CaseRealizadoDelJugador(JuegoId juegoId, EtapaId etapaId, JugadorId jugadorId, Case aCase) {
        super("nomemientan.ronda.aaserealizadodeljugador");
        this.juegoId = juegoId;
        this.etapaId = etapaId;
        this.jugadorId = jugadorId;
        this.aCase = aCase;
    }

    public JuegoId getJuegoId() {
        return juegoId;
    }

    public JugadorId getJugadorId() {
        return jugadorId;
    }

    public Case getCase() {
        return aCase;
    }

    public EtapaId getEtapaId() {
        return etapaId;
    }
}
