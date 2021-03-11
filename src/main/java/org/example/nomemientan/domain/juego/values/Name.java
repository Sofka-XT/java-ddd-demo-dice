package org.example.nomemientan.domain.juego.values;

import co.com.sofka.domain.generic.ValueObject;

import java.util.Objects;

public class Name implements ValueObject<String> {
    private final String value;

    public Name(String name) {
        this.value = Objects.requireNonNull(name);
        if(name.isBlank()){
            throw new IllegalArgumentException("El nombre no puede esta vacio");
        }
    }

    public String value() {
        return value;
    }
}
