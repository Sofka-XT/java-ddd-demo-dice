package org.example.nomemientan.usecase.juego.handle;

import co.com.sofka.business.annotation.CommandHandles;
import co.com.sofka.business.annotation.CommandType;
import co.com.sofka.business.asyn.UseCaseExecutor;
import co.com.sofka.business.support.RequestCommand;
import org.example.nomemientan.domain.juego.command.CrearJuego;

import java.util.Map;

@CommandHandles
@CommandType(name = "nomemientas.juego.crear", aggregate = "juego")
public class CrearJuegoHandle extends UseCaseExecutor {

    private RequestCommand<CrearJuego> request;

    @Override
    public void run() {

    }


    @Override
    public void accept(Map<String, String> args) {
        System.out.println(args);

    }
}
