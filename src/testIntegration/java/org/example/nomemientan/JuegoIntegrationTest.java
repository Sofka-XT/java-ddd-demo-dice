package org.example.nomemientan;

import org.example.nomemientan.domain.juego.values.JuegoId;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.util.Map;

import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.requestFields;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class JuegoIntegrationTest extends CommandBaseIntegrationTest {
    private static final String aggregateId = new JuegoId().value();

    @Test
    @Order(1)
    void createJuego() {
        executor(Map.of(
                "commandType", "nomemientas.juego.crear",
                   "aggregateId", aggregateId,
                "jugadoresIds", "1111,2222,3333",
                "capitales", "3000,3000,3000",
                "nombres", "Raul,Pedro,Juan"
        ), requestFields(
                fieldWithPath("commandType").description("Tipo de comando"),
                fieldWithPath("aggregateId").description("Identificador del agregado"),
                fieldWithPath("jugadoresIds").description("IDs de los jugadores separados por comas"),
                fieldWithPath("capitales").description("Capitales de los jugadores separados por comas"),
                fieldWithPath("nombres").description("Nombres de los jugadores separados por comas")
        ), 4);
    }

    @Test
    @Order(2)
    void inicializarJuego() {
        executor(Map.of(
                "commandType", "nomemientas.juego.inicializar",
                "aggregateId", aggregateId
        ), requestFields(
                fieldWithPath("commandType").description("Tipo de comando"),
                fieldWithPath("aggregateId").description("Identificador del agregado")
        ), 3);
    }
}
