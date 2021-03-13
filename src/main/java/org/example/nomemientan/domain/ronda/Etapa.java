package org.example.nomemientan.domain.ronda;

import co.com.sofka.domain.generic.Entity;
import org.example.nomemientan.domain.juego.values.JugadorId;
import org.example.nomemientan.domain.ronda.values.Cara;
import org.example.nomemientan.domain.ronda.values.Case;
import org.example.nomemientan.domain.ronda.values.EtapaId;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Etapa extends Entity<EtapaId> {
    private final Set<Cara> carasVisibles;
    private final Map<JugadorId, Case> cases;

    public Etapa(EtapaId entityId) {
        super(entityId);
        this.carasVisibles = new HashSet<>();
        this.cases = new HashMap<>();
    }

    public void agregarCaraVisible(Cara cara) {
        carasVisibles.add(cara);
    }

    public void aggregarCases(JugadorId jugadorId, Case aCase) {
        this.cases.put(jugadorId, aCase);
    }

    public Map<JugadorId, Case> cases() {
        return cases;
    }

    public Set<Cara> carasVisibles() {
        return carasVisibles;
    }
}
