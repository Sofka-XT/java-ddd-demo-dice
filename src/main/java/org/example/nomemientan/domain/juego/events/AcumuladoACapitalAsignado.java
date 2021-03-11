package org.example.nomemientan.domain.juego.events;

import co.com.sofka.domain.generic.DomainEvent;
import org.example.nomemientan.domain.juego.values.JugadorId;

public class AcumuladoACapitalAsignado extends DomainEvent {
    private final JugadorId jugadorId;
    private final Integer value;

    public AcumuladoACapitalAsignado(JugadorId jugadorId, Integer value) {
        super("nomemientan.juego.acumuladoacapitalasignado");
        this.jugadorId = jugadorId;
        this.value = value;
    }

    public JugadorId getJugadorId() {
        return jugadorId;
    }

    public Integer getValue() {
        return value;
    }
}
