package org.example.nomemientan.domain.ronda;

import co.com.sofka.domain.generic.Entity;
import org.example.nomemientan.domain.juego.values.JuegoId;

public class Punto extends Entity<JuegoId> {
    private Integer valor;

    public Punto(JuegoId entityId) {
        super(entityId);
        this.valor = 0;
    }

    public void aumentarPuntos(Integer valor) {
        if (0 > valor) {
            throw new IllegalArgumentException("No se puede colocar puntos negativos");
        }
        this.valor = this.valor + valor;
    }

    public Integer valor() {
        return valor;
    }
}
