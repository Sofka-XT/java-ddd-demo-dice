package org.example.nomemientan.domain.ronda.events;

import co.com.sofka.domain.generic.DomainEvent;
import org.example.nomemientan.domain.juego.values.JuegoId;
import org.example.nomemientan.domain.ronda.values.Cara;
import org.example.nomemientan.domain.ronda.values.DadoId;

import java.util.List;
import java.util.Map;

public class DadosLanzados extends DomainEvent {
    private final JuegoId juegoId;
    private final List<Map<DadoId, List<Cara>>> carasList;


    public DadosLanzados(JuegoId juegoId, List<Map<DadoId, List<Cara>>> carasList) {
        super("nomemientan.ronda.dadoslanzados");
        this.juegoId = juegoId;
        this.carasList = carasList;
    }


    public List<Map<DadoId, List<Cara>>> getCarasList() {
        return carasList;
    }

    public JuegoId getJuegoId() {
        return juegoId;
    }
}
