package org.example.nomemientan.domain.ronda.events;

import co.com.sofka.domain.generic.DomainEvent;
import org.example.nomemientan.domain.juego.values.JuegoId;
import org.example.nomemientan.domain.juego.values.JugadorId;

import java.util.Set;

public class RondaInicializada extends DomainEvent {
    private final JuegoId juegoId;
    private final Set<JugadorId> jugadorIds;

    public RondaInicializada(JuegoId juegoId, Set<JugadorId> jugadorIds) {
        super("nomemientan.ronda.rondainicializada");
        this.juegoId = juegoId;
        this.jugadorIds = jugadorIds;
    }

    public JuegoId getJuegoId() {
        return juegoId;
    }

    public Set<JugadorId> getJugadorIds() {
        return jugadorIds;
    }
}
