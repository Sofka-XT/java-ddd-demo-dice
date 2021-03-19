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

    @Test
    @Order(1)
    void createJuego() {
        executor(Map.of(
                "commandType", "nomemientas.juego.crear",
                   "aggregateId", new JuegoId().value(),
                "jugadoresIds", "1111,2222,3333",
                "capitales", "3000,3000,3000",
                "nombres", "Raul,Pedro,Juan"
        ), requestFields(
                fieldWithPath("commandType").description("Tipo de comando"),
                fieldWithPath("jugadoresIds").description("IDs de los jugadores separados por comas"),
                fieldWithPath("aggregateId").description("IDs de los jugadores separados por comas"),
                fieldWithPath("capitales").description("Capitales de los jugadores separados por comas"),
                fieldWithPath("nombres").description("Nombres de los jugadores separados por comas")
        ), 4);
    }
}
