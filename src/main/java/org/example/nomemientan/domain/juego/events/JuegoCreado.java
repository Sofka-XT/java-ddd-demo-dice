package org.example.nomemientan.domain.juego.events;

import co.com.sofka.domain.generic.DomainEvent;
import org.example.nomemientan.domain.juego.Jugador;
import org.example.nomemientan.domain.juego.values.JugadorId;

import java.util.Map;

public class JuegoCreado extends DomainEvent {
    private final Map<JugadorId, Jugador> jugadores;

    public JuegoCreado(Map<JugadorId, Jugador> jugadores) {
        super("nomemientan.juego.creado");
        this.jugadores = jugadores;
    }

    public Map<JugadorId, Jugador> getJugadores() {
        return jugadores;
    }
}
