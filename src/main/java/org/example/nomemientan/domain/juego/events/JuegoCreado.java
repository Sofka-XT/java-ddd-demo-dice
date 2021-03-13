package org.example.nomemientan.domain.juego.events;

import co.com.sofka.domain.generic.DomainEvent;
import org.example.nomemientan.domain.juego.values.JuegoId;

public class JuegoCreado extends DomainEvent {
    private final JuegoId juegoId;

    public JuegoCreado(JuegoId juegoId) {
        super("nomemientan.juego.creado");
        this.juegoId = juegoId;
    }

    public JuegoId getJuegoId() {
        return juegoId;
    }
}
