package org.example.nomemientan.domain.juego.events;

import co.com.sofka.domain.generic.DomainEvent;
import org.example.nomemientan.domain.juego.Jugador;
import org.example.nomemientan.domain.juego.values.JugadorId;
import org.example.nomemientan.domain.juego.values.Name;

public class JuegoFinalizadoConGanador extends DomainEvent {

    private final Name nombreDelJugador;
    private final JugadorId juegadorId;

    public JuegoFinalizadoConGanador(JugadorId juegadorId, Name nombreDelJugador ) {
        super("nomemientan.juego.finalizadoconganador");
        this.nombreDelJugador = nombreDelJugador;
        this.juegadorId = juegadorId;
    }

    public Name getNombreDelJugador() {
        return nombreDelJugador;
    }

    public JugadorId getJuegadorId() {
        return juegadorId;
    }
}
