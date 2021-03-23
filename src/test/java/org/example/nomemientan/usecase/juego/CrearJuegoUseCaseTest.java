package org.example.nomemientan.usecase.juego;

import co.com.sofka.business.generic.BusinessException;
import co.com.sofka.business.generic.UseCaseHandler;
import co.com.sofka.business.support.RequestCommand;
import org.example.nomemientan.domain.juego.command.CrearJuego;
import org.example.nomemientan.domain.juego.events.JuegoCreado;
import org.example.nomemientan.domain.juego.events.JugadorAdicionado;
import org.example.nomemientan.domain.juego.values.Capital;
import org.example.nomemientan.domain.juego.values.JuegoId;
import org.example.nomemientan.domain.juego.values.JugadorId;
import org.example.nomemientan.domain.juego.values.Nombre;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;


class CrearJuegoUseCaseTest {

    @Test
    void crearUnJuego() {
        List<CrearJuego.TuplaJugador> jugadores = new ArrayList<>();

        jugadores.add(new CrearJuego.TuplaJugador(new Capital(500), new Nombre("Andres Alzate"), JugadorId.of("ffff")));
        jugadores.add(new CrearJuego.TuplaJugador(new Capital(500), new Nombre("Raul Alzate"), JugadorId.of("xxxxx")));

        var command = new CrearJuego(jugadores, new JuegoId());
        var useCase = new CrearJuegoUseCase();

        var events = UseCaseHandler.getInstance()
                .syncExecutor(useCase, new RequestCommand<>(command))
                .orElseThrow()
                .getDomainEvents();

        var juegoCreado = (JuegoCreado) events.get(0);
        var jugadorAdicionadoParaRaul = (JugadorAdicionado) events.get(2);
        var jugadorAdicionadoParaAndres = (JugadorAdicionado) events.get(1);

        Assertions.assertTrue(Objects.nonNull(juegoCreado.getJuegoId().value()));

        Assertions.assertEquals("Raul Alzate", jugadorAdicionadoParaRaul.getNombre().value());
        Assertions.assertEquals(500, jugadorAdicionadoParaRaul.getCapital().value());
        Assertions.assertEquals("xxxxx", jugadorAdicionadoParaRaul.getJugadorId().value());

        Assertions.assertEquals("Andres Alzate", jugadorAdicionadoParaAndres.getNombre().value());
        Assertions.assertEquals(500, jugadorAdicionadoParaAndres.getCapital().value());
        Assertions.assertEquals("ffff", jugadorAdicionadoParaAndres.getJugadorId().value());

    }


    @Test
    void errorAlCrearJuego() {
        List<CrearJuego.TuplaJugador> jugadores = new ArrayList<>();
        jugadores.add(new CrearJuego.TuplaJugador(new Capital(500), new Nombre("Raul Alzate"), JugadorId.of("xxxxx")));
        var command = new CrearJuego(jugadores, new JuegoId());
        var useCase = new CrearJuegoUseCase();


        Assertions.assertThrows(BusinessException.class, () -> {
            UseCaseHandler.getInstance()
                    .syncExecutor(useCase, new RequestCommand<>(command))
                    .orElseThrow();
        }, "No se puede crear el juego por que no tiene la cantidad necesaria de jugadores");

    }

}