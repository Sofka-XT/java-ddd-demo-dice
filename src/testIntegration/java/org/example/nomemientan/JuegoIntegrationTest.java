package org.example.nomemientan;

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
                "commandType", "nomemientas.juego.crear"

        ), requestFields(
                fieldWithPath("commandType").description("core.clan.create")
        ), 1);
    }
}
