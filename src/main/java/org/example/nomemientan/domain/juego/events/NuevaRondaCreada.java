package org.example.nomemientan.domain.juego.events;

import co.com.sofka.domain.generic.DomainEvent;
import org.example.nomemientan.domain.juego.values.RondaId;
import org.example.nomemientan.domain.juego.values.JugadorId;

import java.util.Set;

public class NuevaRondaCreada extends DomainEvent {
    private final Set<JugadorId> jugadoresIds;
    private final RondaId rondaId;

    public NuevaRondaCreada(RondaId rondaId, Set<JugadorId> jugadoresIds) {
        super("nomemientan.juego.creada");
        this.jugadoresIds = jugadoresIds;
        this.rondaId = rondaId;
    }

    public Set<JugadorId> getJugadoresIds() {
        return jugadoresIds;
    }

    public RondaId getRondaId() {
        return rondaId;
    }
}
