package org.example.nomemientan.infra.bus;

public interface EventSubscriber {
    void subscribe(String eventType, String exchange);
}