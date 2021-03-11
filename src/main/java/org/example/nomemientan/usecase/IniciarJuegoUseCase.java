package org.example.nomemientan.usecase;

import co.com.sofka.business.generic.BusinessException;
import co.com.sofka.business.generic.UseCase;
import co.com.sofka.business.support.RequestCommand;
import co.com.sofka.business.support.ResponseEvents;
import org.example.nomemientan.domain.juego.Juego;
import org.example.nomemientan.domain.juego.command.IniciarJuego;

public class IniciarJuegoUseCase extends UseCase<RequestCommand<IniciarJuego>, ResponseEvents> {
    @Override
    public void executeUseCase(RequestCommand<IniciarJuego> input) {
        var command = input.getCommand();
        var juego = Juego.from(command.getJuegoId(), retrieveEvents());
        try {
            juego.iniciarJuego();
            emit().onResponse(new ResponseEvents(juego.getUncommittedChanges()));
        } catch (RuntimeException e){
            emit().onError(new BusinessException(juego.identity().value(), e.getMessage()));
        }
    }
}
