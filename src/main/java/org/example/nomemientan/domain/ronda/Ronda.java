package org.example.nomemientan.domain.ronda;

import co.com.sofka.domain.generic.AggregateEvent;
import co.com.sofka.domain.generic.DomainEvent;
import org.example.nomemientan.domain.juego.values.JuegoId;
import org.example.nomemientan.domain.juego.values.JugadorId;
import org.example.nomemientan.domain.ronda.events.*;
import org.example.nomemientan.domain.ronda.values.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Ronda extends AggregateEvent<RondaId> {

    protected JuegoId juegoId;
    protected Map<DadoId, Dado> dados;
    protected Map<EtapaId, Etapa> etapas;
    protected Map<JuegoId, Punto> puntaje;//TODO: definir puntaje
    protected Set<JugadorId> jugadorIds;
    protected List<Cara> caras;

    public Ronda(RondaId entityId, JuegoId juegoId, Set<JugadorId> jugadorIds) {
        super(entityId);
        appendChange(new RondaCreada(jugadorIds, juegoId)).apply();
    }

    private Ronda(RondaId entityId) {
        super(entityId);
        subscribe(new RondaChange(this));
    }

    public static Ronda from(RondaId entityId, List<DomainEvent> events) {
        var ronda = new Ronda(entityId);
        events.forEach(ronda::applyEvent);
        return ronda;
    }

    public void inicializarRonda() {
        appendChange(new RondaInicializada(juegoId, jugadorIds)).apply();
    }


    public void tirarDados() {
        var carasList = this.dados
                .values()
                .stream()
                .map(dado -> Map.of(dado.identity(), dado.caras()))
                .collect(Collectors.toList());
        appendChange(new DadosLanzados(juegoId, carasList)).apply();
    }

    public void realizarCasePorJugador(JugadorId jugadorId, EtapaId etapaId, Case aCase){
        appendChange(new CaseRealizadoDelJugador(juegoId, etapaId, jugadorId, aCase)).apply();
    }

    public void crearEtapaInicial() {
        List<Cara> carasVisibles = new ArrayList<>();
        appendChange(new EtapaCreada(juegoId, jugadorIds, EtapaId.of(1), carasVisibles)).apply();
    }

    public void crearNuevaEtapa() {
        List<Cara> carasVisibles = new ArrayList<>();
        var etapaId = EtapaId.of(etapas.size() + 1);
        if(etapaId.value().equals("2")){
            caras.stream().limit(3).forEach(carasVisibles::add);
        }
        if(etapaId.value().equals("3")){
            caras.stream().limit(5).forEach(carasVisibles::add);
        }

        if(etapaId.value().equals("4")){
            caras.stream().limit(6).forEach(carasVisibles::add);
        }
        appendChange(new EtapaCreada(juegoId, jugadorIds, etapaId, carasVisibles)).apply();
    }

    public void calificarRonda(EtapaId etapaId) {
        appendChange(new EtapaCalificada(etapaId)).apply();
    }

    public void finalizarRonda() {
        appendChange(new RondaFinalizada(juegoId, jugadorIds)).apply();
    }

    public Map<EtapaId, Etapa> etapas() {
        return etapas;
    }

    public JuegoId juegoId() {
        return juegoId;
    }

    public Set<JugadorId> jugadorIds() {
        return jugadorIds;
    }



}
