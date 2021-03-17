package org.example.nomemientan.domain.juego;

import co.com.sofka.domain.generic.AggregateEvent;
import co.com.sofka.domain.generic.DomainEvent;
import org.example.nomemientan.domain.juego.events.*;
import org.example.nomemientan.domain.juego.factory.JugadorFactory;
import org.example.nomemientan.domain.juego.values.*;
import org.example.nomemientan.domain.ronda.values.Case;
import org.example.nomemientan.domain.ronda.values.EtapaId;
import org.example.nomemientan.domain.ronda.values.RondaId;

import java.util.List;
import java.util.Map;

public class Juego extends AggregateEvent<JuegoId> {

    protected Boolean juegoInicializado;
    protected Map<JugadorId, Jugador> jugadores;


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

    public void deducirCapitalDelJugador(JugadorId jugadorId, Apuesta apuesta) {
        appendChange(new CapitalDeducidoDelJugador(jugadorId, apuesta)).apply();
    }


    public void casarApuestaEnEtapa(JugadorId jugadorId, RondaId rondaId, EtapaId etapaId,  Case aCase) {
        appendChange(new AputestaYAdivinanzaCasada(jugadorId, rondaId, etapaId, aCase)).apply();
    }



    public Boolean isJuegoInicializado() {
        return juegoInicializado;
    }
}
