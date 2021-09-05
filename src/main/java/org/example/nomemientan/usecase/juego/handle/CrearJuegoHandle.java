package org.example.nomemientan.usecase.juego.handle;

import co.com.sofka.business.annotation.CommandHandles;
import co.com.sofka.business.annotation.CommandType;
import co.com.sofka.business.asyn.UseCaseCommandExecutor;

import co.com.sofka.business.generic.UseCase;
import co.com.sofka.business.support.RequestCommand;
import co.com.sofka.business.support.ResponseEvents;
import org.example.nomemientan.domain.juego.command.CrearJuego;
import org.example.nomemientan.usecase.juego.CrearJuegoUseCase;


@CommandHandles
@CommandType(name = "nomemientas.juego.crear", aggregate = "juego")
public class CrearJuegoHandle extends UseCaseCommandExecutor<CrearJuego> {

    @Override
    public void accept(CrearJuego command) {
       //validation sync
    }

    @Override
    public UseCase<RequestCommand<CrearJuego>, ResponseEvents> registerUseCase() {
        return new CrearJuegoUseCase();
    }
}
