package org.example.nomemientan.domain.ronda;

import co.com.sofka.domain.generic.Entity;
import org.example.nomemientan.domain.juego.values.JugadorId;
import org.example.nomemientan.domain.ronda.values.Cara;
import org.example.nomemientan.domain.ronda.values.Case;
import org.example.nomemientan.domain.ronda.values.EtapaId;

import java.util.*;

public class Etapa extends Entity<EtapaId> {
    private final List<Cara> carasVisibles;
    private final Map<JugadorId, Case> cases;

    public Etapa(EtapaId entityId, List<Cara> carasVisibles) {
        super(entityId);
        this.carasVisibles = carasVisibles;
        this.cases = new HashMap<>();
    }

    public void agregarCaraVisible(Cara cara) {
        carasVisibles.add(cara);
    }

    public void aggregarCase(JugadorId jugadorId, Case aCase) {
        this.cases.put(jugadorId, aCase);
    }

    public Map<JugadorId, Case> cases() {
        return cases;
    }

    public List<Cara> carasVisibles() {
        return carasVisibles;
    }
}
