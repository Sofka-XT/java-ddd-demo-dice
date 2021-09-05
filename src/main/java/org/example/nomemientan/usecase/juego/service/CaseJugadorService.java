package org.example.nomemientan.usecase.juego.service;

import org.example.nomemientan.domain.juego.values.JugadorId;
import org.example.nomemientan.domain.ronda.values.Cara;
import org.example.nomemientan.domain.ronda.values.Case;

import java.util.List;

public interface CaseJugadorService {
    Case getCasePor(JugadorId jugadorId, List<Cara> carasVisibles);
}
