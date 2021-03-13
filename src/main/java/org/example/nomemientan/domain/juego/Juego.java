package org.example.nomemientan.domain.juego;

import co.com.sofka.domain.generic.AggregateEvent;
import co.com.sofka.domain.generic.DomainEvent;
import org.example.nomemientan.domain.juego.events.JuegoCreado;
import org.example.nomemientan.domain.juego.events.JuegoInicializado;
import org.example.nomemientan.domain.juego.events.JugadorAdicionado;
import org.example.nomemientan.domain.juego.factory.JugadorFactory;
import org.example.nomemientan.domain.juego.values.*;
import org.example.nomemientan.domain.ronda.values.RondaId;

import java.util.List;
import java.util.Map;

public class Juego extends AggregateEvent<JuegoId> {

    protected Boolean juegoInicializado;
    protected Map<JugadorId, Jugador> jugadores;
    protected RondaId rondaId;


    public Juego(JuegoId entityId, JugadorFactory jugadorFactory) {
        super(entityId);
        appendChange(new JuegoCreado(entityId)).apply();
        jugadorFactory.jugadores()
                .forEach(jugador -> adicionarJugador(jugador.identity(), jugador.nombre(), jugador.capital()));
    }

    private Juego(JuegoId entityId) {
        super(entityId);
        subscribe(new JuegoChange(this));
    }

    public static Juego from(JuegoId entityId, List<DomainEvent> events) {
        var juego = new Juego(entityId);
        events.forEach(juego::applyEvent);
        return juego;
    }

    public void adicionarJugador(JugadorId jugadorId, Nombre nombre, Capital capital) {
        appendChange(new JugadorAdicionado(jugadorId, nombre, capital)).apply();
    }

    public void iniciarJuego() {
        var jugadoresIds = jugadores.keySet();
        appendChange(new JuegoInicializado(jugadoresIds)).apply();
    }

    public RondaId rondaId() {
        return rondaId;
    }

    public Boolean isJegoInicializado() {
        return juegoInicializado;
    }

}
