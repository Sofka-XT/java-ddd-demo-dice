package org.example.nomemientan.domain.juego.command;

import co.com.sofka.domain.generic.Command;
import org.example.nomemientan.domain.juego.values.Capital;
import org.example.nomemientan.domain.juego.values.JugadorId;
import org.example.nomemientan.domain.juego.values.Nombre;

import java.util.Map;

public class CrearJuego implements Command {
    private final Map<JugadorId, Capital> capitales;
    private final Map<JugadorId, Nombre> nombres;

    public CrearJuego(Map<JugadorId, Capital> capitales, Map<JugadorId, Nombre> nombres) {
        this.capitales = capitales;
        this.nombres = nombres;
    }

    public Map<JugadorId, Capital> getCapitales() {
        return capitales;
    }

    public Map<JugadorId, Nombre> getNombres() {
        return nombres;
    }
}
