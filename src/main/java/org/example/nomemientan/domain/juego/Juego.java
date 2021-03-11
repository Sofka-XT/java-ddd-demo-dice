package org.example.nomemientan.domain.juego;

import co.com.sofka.domain.generic.AggregateEvent;
import co.com.sofka.domain.generic.DomainEvent;
import org.example.nomemientan.domain.juego.events.*;
import org.example.nomemientan.domain.juego.values.*;

import java.util.*;

public class Juego extends AggregateEvent<JuegoId> {

    protected Map<JugadorId, Jugador> jugadores;
    protected RondaId rondaId;
    protected Boolean juegoIniciado;
    protected Boolean tieneGanador;

    public Juego(JuegoId entityId, Set<Jugador> jugadores) {
        super(entityId);
        Map<JugadorId, Jugador> newJugadores = new HashMap<>();
        jugadores.forEach(jugador -> newJugadores.put(jugador.identity(), jugador));
        appendChange(new JuegoCreado(newJugadores)).apply();
    }

    private Juego(JuegoId entityId){
        super(entityId);
        subscribe(new JuegoChange(this));
    }

    public static Juego from(JuegoId entityId, List<DomainEvent> events){
        var juego = new Juego(entityId);
        events.forEach(juego::applyEvent);
        return juego;
    }

    public void iniciarJuego(){
        appendChange(new JuegoIniciado()).apply();
    }

    public void crearNuevaRonda(){
        var jugadoresIds = jugadores.keySet();
        var newRondaId = new RondaId();
        appendChange(new NuevaRondaCreada(newRondaId, jugadoresIds)).apply();
    }

    public void eliminarJugadoresDeRonda(Set<JugadorId> jugadoresIds){
        appendChange(new JugadoresEliminados(jugadoresIds)).apply();
    }

    public void asignarAcumuladoACapital(Set<JugadorId> jugadoresIds){
        appendChange(new JugadoresEliminados(jugadoresIds)).apply();
    }

    public void asignarAcumuladoACapital(JugadorId jugadorId, Integer value){
        Objects.requireNonNull(value);
        appendChange(new AcumuladoACapitalAsignado(jugadorId, value)).apply();
    }

    public void hacerApuestaConAdivinanza(JugadorId jugadorId, Apusta apusta, Adivinanza adivinanza){
        appendChange(new ApuestaHechaConAdivinanza(jugadorId, apusta, adivinanza)).apply();
    }

    public RondaId rondaId() {
        return rondaId;
    }

    public Boolean tieneGanador(){
        return tieneGanador;
    }

    public Map<JugadorId, Jugador> jugadores() {
        return jugadores;
    }
}
