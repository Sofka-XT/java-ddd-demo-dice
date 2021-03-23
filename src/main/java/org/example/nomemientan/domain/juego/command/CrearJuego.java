package org.example.nomemientan.domain.juego.command;

import co.com.sofka.domain.generic.Command;
import org.example.nomemientan.domain.juego.values.Capital;
import org.example.nomemientan.domain.juego.values.JuegoId;
import org.example.nomemientan.domain.juego.values.JugadorId;
import org.example.nomemientan.domain.juego.values.Nombre;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class CrearJuego extends Command {
    private final List<TuplaJugador> tuplaJugadores;
    private final JuegoId juegoId;

    public CrearJuego(List<TuplaJugador> tuplaJugadores, JuegoId juegoId) {
        this.tuplaJugadores = tuplaJugadores;
        this.juegoId = juegoId;
    }

    public List<TuplaJugador> getTuplaJugadores() {
        return tuplaJugadores;
    }

    public JuegoId getJuegoId() {
        return juegoId;
    }

    public static class TuplaJugador implements Serializable {
        private  Capital capital;
        private  Nombre nombre;
        private  JugadorId jugadorId;

        public TuplaJugador(Capital capital, Nombre nombre, JugadorId jugadorId) {
            this.capital = capital;
            this.nombre = nombre;
            this.jugadorId = jugadorId;
        }

        public TuplaJugador(){

        }

        public JugadorId getJugadorId() {
            return jugadorId;
        }

        public Nombre getNombre() {
            return nombre;
        }

        public Capital getCapital() {
            return capital;
        }
    }
}
