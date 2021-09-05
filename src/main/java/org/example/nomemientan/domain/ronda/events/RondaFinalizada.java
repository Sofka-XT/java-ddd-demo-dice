package org.example.nomemientan.domain.ronda.events;

import co.com.sofka.domain.generic.DomainEvent;
import org.example.nomemientan.domain.juego.values.JuegoId;
import org.example.nomemientan.domain.juego.values.JugadorId;

import java.util.Set;

public class RondaFinalizada extends DomainEvent {
    private final JuegoId juegoId;

    public RondaFinalizada(JuegoId juegoId, Set<JugadorId> jugadorIds) {
        super("nomemientan.ronda.rondafinalizada");
        this.juegoId = juegoId;
    }

    public JuegoId getJuegoId() {
        return juegoId;
    }
}
