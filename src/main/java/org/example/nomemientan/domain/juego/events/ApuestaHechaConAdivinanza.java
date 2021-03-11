package org.example.nomemientan.domain.juego.events;

import co.com.sofka.domain.generic.DomainEvent;
import org.example.nomemientan.domain.juego.values.Adivinanza;
import org.example.nomemientan.domain.juego.values.Apusta;
import org.example.nomemientan.domain.juego.values.JugadorId;

public class ApuestaHechaConAdivinanza extends DomainEvent {
    private final JugadorId jugadorId;
    private final Apusta apusta;
    private final Adivinanza adivinanza;

    public ApuestaHechaConAdivinanza(JugadorId jugadorId, Apusta apusta, Adivinanza adivinanza) {
        super("nomemientan.juego.apuestahechaconadivinanza");
        this.jugadorId = jugadorId;
        this.apusta = apusta;
        this.adivinanza = adivinanza;
    }

    public Adivinanza getAdivinanza() {
        return adivinanza;
    }

    public Apusta getApusta() {
        return apusta;
    }

    public JugadorId getJugadorId() {
        return jugadorId;
    }
}
