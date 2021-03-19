package org.example.nomemientan.usecase.juego.handle;

import co.com.sofka.business.annotation.CommandHandles;
import co.com.sofka.business.annotation.CommandType;
import co.com.sofka.business.asyn.UseCaseExecutor;
import co.com.sofka.business.support.RequestCommand;
import org.example.nomemientan.domain.juego.command.CrearJuego;
import org.example.nomemientan.domain.juego.values.Capital;
import org.example.nomemientan.domain.juego.values.JuegoId;
import org.example.nomemientan.domain.juego.values.JugadorId;
import org.example.nomemientan.domain.juego.values.Nombre;
import org.example.nomemientan.usecase.juego.CrearJuegoUseCase;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@CommandHandles
@CommandType(name = "nomemientas.juego.crear", aggregate = "juego")
public class CrearJuegoHandle extends UseCaseExecutor {

    private RequestCommand<CrearJuego> request;

    @Override
    public void run() {
        runUseCase(new CrearJuegoUseCase(), request);
    }

    @Override
    public void accept(Map<String, String> args) {
        Map<JugadorId, Nombre> nombreMap = new HashMap<>();
        Map<JugadorId, Capital> capiltalesMap = new HashMap<>();

        var ids = Objects.requireNonNull(args.get("jugadoresIds")).split(",");
        var nombres = Objects.requireNonNull(args.get("nombres")).split(",");
        var capiltales = Objects.requireNonNull(args.get("capitales")).split(",");
        for(var i = 0; i < ids.length; i++){
            nombreMap.put(JugadorId.of(ids[i]), new Nombre(nombres[i]));
            capiltalesMap.put(JugadorId.of(ids[i]), new Capital(Integer.parseInt(capiltales[i])));
        }

        request =  new RequestCommand<>(new CrearJuego( capiltalesMap, nombreMap, JuegoId.of(aggregateId())));
    }
}
