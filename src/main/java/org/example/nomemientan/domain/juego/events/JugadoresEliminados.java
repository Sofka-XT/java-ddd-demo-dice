package org.example.nomemientan.domain.juego.events;

import co.com.sofka.domain.generic.DomainEvent;
import org.example.nomemientan.domain.juego.values.JugadorId;

import java.util.Set;

public class JugadoresEliminados extends DomainEvent {
    private final Set<JugadorId> jugadoresIds;

    public JugadoresEliminados(Set<JugadorId> jugadoresIds) {
        super("nomemientan.juego.jugadoreseliminados");
        this.jugadoresIds = jugadoresIds;
    }

    public Set<JugadorId> getJugadoresIds() {
        return jugadoresIds;
    }
}
