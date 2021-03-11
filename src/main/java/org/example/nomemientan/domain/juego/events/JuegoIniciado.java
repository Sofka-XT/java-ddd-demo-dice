package org.example.nomemientan.domain.juego.events;

import co.com.sofka.domain.generic.DomainEvent;


public class JuegoIniciado extends DomainEvent {
    public JuegoIniciado() {
        super("nomemientan.juego.iniciado");
    }
}
