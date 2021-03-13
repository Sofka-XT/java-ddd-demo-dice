package org.example.nomemientan.usecase;

import co.com.sofka.business.generic.UseCase;
import co.com.sofka.business.support.RequestCommand;
import co.com.sofka.business.support.ResponseEvents;
import org.example.nomemientan.domain.juego.Juego;
import org.example.nomemientan.domain.juego.command.InicializarJuego;

public class InicializarJuegoUseCase extends UseCase<RequestCommand<InicializarJuego>, ResponseEvents> {
    @Override
    public void executeUseCase(RequestCommand<InicializarJuego> input) {
        var command = input.getCommand();

        var juego = Juego.from(command.getJuegoId(), retrieveEvents());

        juego.iniciarJuego();

        emit().onResponse(new ResponseEvents(juego.getUncommittedChanges()));

    }
}
