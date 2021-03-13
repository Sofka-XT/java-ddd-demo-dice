package org.example.nomemientan.domain.juego.values;

import co.com.sofka.domain.generic.ValueObject;

import java.util.Objects;

public class Apuesta implements ValueObject<Integer> {
    private final Integer value;

    public Apuesta(Integer value) {
        this.value = Objects.requireNonNull(value);
    }

    @Override
    public Integer value() {
        return value;
    }
}
