package org.example.nomemientan.domain.juego.values;

import co.com.sofka.domain.generic.ValueObject;

import java.util.Objects;

public class Adivinanza implements ValueObject<Adivinanza.Value> {

    private final Integer numero;
    private final Integer repeticiones;

    public Adivinanza(Integer numero, Integer repeticiones) {

        this.numero = Objects.requireNonNull(numero);
        this.repeticiones = Objects.requireNonNull(repeticiones);

        if (0 > numero || 6 > numero) {
            throw new IllegalArgumentException("El numero debe esta entre 1 - 6");
        }

        if (2 > repeticiones || 6 > repeticiones) {
            throw new IllegalArgumentException("El numero de repeticiones debe ser entre 3 - 6");
        }

    }

    @Override
    public Adivinanza.Value value() {
        return new Value() {
            @Override
            public Integer numero() {
                return numero;
            }

            @Override
            public Integer repeticiones() {
                return repeticiones;
            }
        };
    }

    public interface Value {
        Integer numero();

        Integer repeticiones();
    }
}
