package org.example.nomemientan.domain.ronda;


import co.com.sofka.domain.generic.Entity;
import org.example.nomemientan.domain.ronda.values.Cara;
import org.example.nomemientan.domain.ronda.values.DadoId;

import java.util.List;

public class Dado extends Entity<DadoId> {
    private List<Cara> caras;

    public Dado(DadoId entityId) {
        super(entityId);
    }

    public void lanzarDado() {
        for (var i = 1; i <= 6; i++) {
            var numero = (int) ((Math.random() * 6) + 1);
            caras.add(new Cara(numero));
        }
    }
}
