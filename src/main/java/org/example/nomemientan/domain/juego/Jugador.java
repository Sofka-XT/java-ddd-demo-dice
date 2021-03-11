package org.example.nomemientan.domain.juego;


import co.com.sofka.domain.generic.Entity;
import org.example.nomemientan.domain.juego.values.Capital;
import org.example.nomemientan.domain.juego.values.JugadorId;
import org.example.nomemientan.domain.juego.values.Name;

public class Jugador extends Entity<JugadorId> {

    private final Name name;
    private Capital capital;

    public Jugador(JugadorId entityId, Name name, Capital capital) {
        super(entityId);
        this.name = name;
        this.capital = capital;
    }

    public Jugador(JugadorId entityId, Name name) {
        super(entityId);
        this.name = name;
        this.capital = new Capital(0);
    }

    public void aumentarCapital(Integer valor) {
        this.capital = this.capital.aumentar(valor);
    }

    public Capital capital() {
        return capital;
    }

    public Name name() {
        return name;
    }
}
