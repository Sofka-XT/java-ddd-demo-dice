package org.example.nomemientan.domain.juego.command;

import co.com.sofka.domain.generic.Command;
import org.example.nomemientan.domain.juego.values.JuegoId;

public class InicializarJuego implements Command {

    private final JuegoId juegoId;

    public InicializarJuego(JuegoId juegoId) {
        this.juegoId = juegoId;
    }

    public JuegoId getJuegoId() {
        return juegoId;
    }
}
