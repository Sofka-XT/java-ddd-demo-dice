package org.example.nomemientan.domain.juego.events;

import co.com.sofka.domain.generic.DomainEvent;
import org.example.nomemientan.domain.juego.values.JugadorId;

import java.util.Set;

public class JuegoInicializado extends DomainEvent {
    private final Set<JugadorId> jugadoresIds;

    public JuegoInicializado(Set<JugadorId> jugadoresIds) {
        super("nomemientan.juego.juegoinicializado");
        this.jugadoresIds = jugadoresIds;
    }

    public Set<JugadorId> getJugadoresIds() {
        return jugadoresIds;
    }
}
