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
    private  final Set<JugadorId> ganadores;

    public Etapa(EtapaId entityId, List<Cara> carasVisibles) {
        super(entityId);
        this.carasVisibles = carasVisibles;
        this.cases = new HashMap<>();
        this.ganadores = new HashSet<>();
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

    public void calificar() {
        cases.forEach((jugadorId, aCase) -> {
           if(isGanador(aCase)){
               ganadores.add(jugadorId);
           }
        });
    }

    public Set<JugadorId> ganadores() {
        return ganadores;
    }

    private boolean isGanador(Case aCase){
        var adivinanza = aCase.value().adivinanza();
        var numero = adivinanza.value().numero();
        var repeticiones = adivinanza.value().repeticiones();
        var carasOcultas = 6 - carasVisibles.size();
        var cantidad = carasVisibles.stream().filter(cara -> cara.value().equals(numero)).count();
        var posibilida = cantidad + carasOcultas;

        if(carasVisibles.isEmpty()){
            return true;
        }
        if(posibilida <= repeticiones){
            return true;
        }
        return cantidad == repeticiones;
    }
}
