package org.example.nomemientan.usecase;

import co.com.sofka.business.generic.BusinessException;
import co.com.sofka.business.generic.UseCase;
import co.com.sofka.business.support.RequestCommand;
import co.com.sofka.business.support.ResponseEvents;
import org.example.nomemientan.domain.juego.Juego;
import org.example.nomemientan.domain.juego.command.CrearJuego;
import org.example.nomemientan.domain.juego.values.JuegoId;

public class CrearJuegoUseCase extends UseCase<RequestCommand<CrearJuego>, ResponseEvents> {
    @Override
    public void executeUseCase(RequestCommand<CrearJuego> input) {
        var command = input.getCommand();
        var juegoId = new JuegoId();

        if(command.getJugadores().size() < 3){
            throw new BusinessException(juegoId.value(), "Debe jugar minimo 3 jugadores");
        }

        var juego = new Juego(new JuegoId(), command.getJugadores());
        emit().onResponse(new ResponseEvents(juego.getUncommittedChanges()));
    }
}
