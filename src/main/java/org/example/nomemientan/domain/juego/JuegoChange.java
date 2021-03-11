package org.example.nomemientan.domain.juego;

import co.com.sofka.domain.generic.EventChange;
import org.example.nomemientan.domain.juego.events.*;



public class JuegoChange extends EventChange {
    public JuegoChange(Juego juego){

        apply((JuegoCreado event) -> {
            juego.jugadores = event.getJugadores();
            juego.juegoIniciado = Boolean.FALSE;
            juego.tieneGanador = Boolean.FALSE;
        });

        apply((AcumuladoACapitalAsignado event) -> {
            juego.jugadores.get(event.getJugadorId())
                    .aumentarCapital(event.getValue());
        });

        apply((JuegoIniciado event) -> {
            if(Boolean.TRUE.equals(juego.juegoIniciado)){
                throw new IllegalArgumentException("El juego ya esta inicializado");
            }
            juego.juegoIniciado = Boolean.TRUE;
        });

        apply((JugadoresEliminados event) -> {
            event.getJugadoresIds()
                    .forEach(jugadorId -> juego.jugadores.remove(jugadorId));
        });

        apply((NuevaRondaCreada event) -> {
            if(Boolean.FALSE.equals(juego.juegoIniciado)){
                throw new IllegalArgumentException("El juego debe estar inicializado");
            }
            if(Boolean.TRUE.equals(juego.tieneGanador)){
                throw new IllegalArgumentException("El juego ya tiene un ganador");
            }
            juego.rondaId = event.getRondaId();
        });

        apply((JuegoFinalizadoConGanador event) -> {
            juego.tieneGanador = true;
        });
    }
}
