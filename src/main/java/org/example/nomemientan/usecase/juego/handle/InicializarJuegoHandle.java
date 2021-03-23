package org.example.nomemientan.usecase.juego.handle;

import co.com.sofka.business.annotation.CommandHandles;
import co.com.sofka.business.annotation.CommandType;
import co.com.sofka.business.asyn.UseCaseCommandExecutor;
import co.com.sofka.business.generic.UseCase;
import co.com.sofka.business.support.RequestCommand;
import co.com.sofka.business.support.ResponseEvents;
import org.example.nomemientan.domain.juego.command.InicializarJuego;
import org.example.nomemientan.usecase.juego.InicializarJuegoUseCase;


@CommandHandles
@CommandType(name = "nomemientas.juego.inicializar", aggregate = "juego")
public class InicializarJuegoHandle extends UseCaseCommandExecutor<InicializarJuego> {

    @Override
    public UseCase<RequestCommand<InicializarJuego>, ResponseEvents> registerUseCase() {
        return new InicializarJuegoUseCase();
    }

    @Override
    public void accept(InicializarJuego inicializarJuego) {

    }
}
