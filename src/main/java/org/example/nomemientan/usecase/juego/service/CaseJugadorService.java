package org.example.nomemientan.usecase.juego.service;

import org.example.nomemientan.domain.juego.values.JugadorId;
import org.example.nomemientan.domain.ronda.values.Case;

public interface CaseJugadorService {
    Case getCasePor(JugadorId jugadorId);
}
