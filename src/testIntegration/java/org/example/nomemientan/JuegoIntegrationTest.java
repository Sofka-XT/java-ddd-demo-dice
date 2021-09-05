package org.example.nomemientan;

import co.com.sofka.infraestructure.handle.CommandWrapper;
import org.example.nomemientan.domain.juego.command.CrearJuego;
import org.example.nomemientan.domain.juego.command.InicializarJuego;
import org.example.nomemientan.domain.juego.values.Capital;
import org.example.nomemientan.domain.juego.values.JuegoId;
import org.example.nomemientan.domain.juego.values.JugadorId;
import org.example.nomemientan.domain.juego.values.Nombre;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.requestFields;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class JuegoIntegrationTest extends CommandBaseIntegrationTest {
    private static final String aggregateId = new JuegoId().value();

    @Test
    @Order(1)
    void createJuego() {
        List<CrearJuego.TuplaJugador> jugadores = new ArrayList<>();
        jugadores.add(new CrearJuego.TuplaJugador(new Capital(500), new Nombre("Raul Alzate"), JugadorId.of("xxxxx")));
        jugadores.add(new CrearJuego.TuplaJugador(new Capital(500), new Nombre("Andres Alzate"), JugadorId.of("ffff")));

        var commandRequired = new CommandWrapper(
                aggregateId,  "nomemientas.juego.crear",
                new CrearJuego(jugadores, JuegoId.of(aggregateId))
        );

        executor(commandRequired, requestFields(
                fieldWithPath("commandType").description("Tipo de comando"),
                fieldWithPath("aggregateId").description("Identificador del agregado"),
                fieldWithPath("payLoad.tuplaJugadores[0].capital.value").description("Capital del juador 1"),
                fieldWithPath("payLoad.tuplaJugadores[0].nombre.value").description("Nombre del juador 1"),
                fieldWithPath("payLoad.tuplaJugadores[0].jugadorId.uuid").description("ID del juador 1"),
                fieldWithPath("payLoad.juegoId.uuid").description("Juego id"),
                fieldWithPath("payLoad.when").description("Datos del comando").optional(),
                fieldWithPath("payLoad.uuid").description("Datos del comando").optional()
        ), 3);
    }

    @Test
    @Order(2)
    void inicializarJuego() {

        var commandRequired = new CommandWrapper(
                aggregateId,  "nomemientas.juego.inicializar",
                new InicializarJuego(JuegoId.of(aggregateId))
        );
       executor(commandRequired, requestFields(
               fieldWithPath("commandType").description("Tipo de comando"),
               fieldWithPath("aggregateId").description("Identificador del agregado"),
               fieldWithPath("payLoad.juegoId.uuid").description("Juego id"),
               fieldWithPath("payLoad.when").description("Datos del comando").optional(),
               fieldWithPath("payLoad.uuid").description("Datos del comando").optional()
        ), 3);
    }
}
