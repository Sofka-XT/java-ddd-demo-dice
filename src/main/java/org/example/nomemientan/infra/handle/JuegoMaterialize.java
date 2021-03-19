package org.example.nomemientan.infra.handle;

import co.com.sofka.domain.generic.DomainEvent;
import org.example.nomemientan.domain.juego.events.JuegoCreado;
import org.example.nomemientan.domain.juego.events.JugadorAdicionado;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.event.EventListener;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.logging.Logger;

@Component
public class JuegoMaterialize {

    private static final String COLLECTION_NAME = "juegos";
    private static final Logger logger = Logger.getLogger(JuegoMaterialize.class.getName());

    @Autowired
    @Qualifier("mongoTemplateQueries")
    private MongoTemplate mongoTemplate;

    @Async
    @EventListener
    public void handleEventJuegoCreado(JuegoCreado juegoCreado) {
        logger.info("****** Handle event juegoCreado");
        Map<String, Object> data = new HashMap<>();
        data.put("_id", juegoCreado.getJuegoId().value());
        data.put("isJuegoInicializado", false);
        mongoTemplate.save(data, COLLECTION_NAME);
    }

    @Async
    @EventListener
    public void handleEventJuegoCreado(JugadorAdicionado jugadorAdicionado) {
        logger.info("****** Handle event jugadorAdicionado");
        Update update = new Update();
        var id = jugadorAdicionado.getJugadorId().value();
        update.set("jugadores."+id+".capital", jugadorAdicionado.getCapital().value());
        update.set("jugadores."+id+".nombre", jugadorAdicionado.getNombre().value());

        mongoTemplate.updateFirst(getFilterByAggregateId(jugadorAdicionado), update, COLLECTION_NAME);
    }


    private Query getFilterByAggregateId(DomainEvent event) {
        return new Query(
                Criteria.where("_id").is(event.aggregateRootId())
        );
    }



}