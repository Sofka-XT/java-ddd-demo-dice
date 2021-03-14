package org.example.nomemientan.usecase.juego;

import co.com.sofka.business.generic.BusinessException;
import co.com.sofka.business.generic.UseCase;
import co.com.sofka.business.support.RequestCommand;
import co.com.sofka.business.support.ResponseEvents;
import org.example.nomemientan.domain.juego.Juego;
import org.example.nomemientan.domain.juego.command.CrearJuego;
import org.example.nomemientan.domain.juego.factory.JugadorFactory;
import org.example.nomemientan.domain.juego.values.JuegoId;


public class CrearJuegoUseCase extends UseCase<RequestCommand<CrearJuego>, ResponseEvents> {
    @Override
    public void executeUseCase(RequestCommand<CrearJuego> input) {
        var command = input.getCommand();
        var juegoId = new JuegoId();

        var factory = JugadorFactory.builder();
        command.getNombres()
                .forEach((jugadorId, nombre) ->
                        factory.nuevoJugador(
                                jugadorId, nombre, command.getCapitales().get(jugadorId)
                        ));

        if (factory.jugadores().size() < 2) {
            throw new BusinessException(juegoId.value(), "No se puede crear el juego por que no tiene la cantidad necesaria de jugadores");
        }

        var juego = new Juego(juegoId, factory);

        emit().onResponse(new ResponseEvents(juego.getUncommittedChanges()));
    }
}
