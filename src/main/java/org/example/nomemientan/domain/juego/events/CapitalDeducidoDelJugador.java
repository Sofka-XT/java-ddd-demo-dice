package org.example.nomemientan.domain.juego.events;

import co.com.sofka.domain.generic.DomainEvent;
import org.example.nomemientan.domain.juego.values.Apuesta;
import org.example.nomemientan.domain.juego.values.JugadorId;

public class CapitalDeducidoDelJugador extends DomainEvent {
    private final JugadorId jugadorId;
    private final Apuesta apuesta;

    public CapitalDeducidoDelJugador(JugadorId jugadorId, Apuesta apuesta) {
        super("nomemientan.juego.capitaldeducidodeljugador");
        this.jugadorId = jugadorId;
        this.apuesta = apuesta;
    }

    public JugadorId getJugadorId() {
        return jugadorId;
    }

    public Apuesta getApuesta() {
        return apuesta;
    }
}
