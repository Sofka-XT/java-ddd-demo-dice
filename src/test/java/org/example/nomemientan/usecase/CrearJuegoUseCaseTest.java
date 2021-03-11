package org.example.nomemientan.usecase;

import co.com.sofka.business.generic.UseCaseHandler;
import co.com.sofka.business.support.RequestCommand;
import org.example.nomemientan.domain.juego.Jugador;
import org.example.nomemientan.domain.juego.command.CrearJuego;
import org.example.nomemientan.domain.juego.events.JuegoCreado;
import org.example.nomemientan.domain.juego.values.JuegoId;
import org.example.nomemientan.domain.juego.values.JugadorId;
import org.example.nomemientan.domain.juego.values.Name;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;



class CrearJuegoUseCaseTest {

    @Test
    void crearJuego(){
        var command = new CrearJuego(Set.of(
                 new Jugador(JugadorId.of("xxxx"), new Name("Raul Alzate")),
                 new Jugador(JugadorId.of("ffff"), new Name("Andres")),
                 new Jugador(JugadorId.of("tttt"), new Name("Camilo"))
        ));
        var crearJuegoUseCase = new CrearJuegoUseCase();
        var events = UseCaseHandler
                .getInstance()
                .syncExecutor(crearJuegoUseCase, new RequestCommand<>(command))
                .orElseThrow()
                .getDomainEvents();

        var juegoCreado = (JuegoCreado)events.get(0);

        Assertions.assertEquals(3, juegoCreado.getJugadores().size());
        Assertions.assertEquals("Raul Alzate", juegoCreado.getJugadores().get(JugadorId.of("xxxx")).name().value());
        Assertions.assertEquals("Andres", juegoCreado.getJugadores().get(JugadorId.of("ffff")).name().value());
        Assertions.assertEquals("Camilo", juegoCreado.getJugadores().get(JugadorId.of("tttt")).name().value());
    }

}