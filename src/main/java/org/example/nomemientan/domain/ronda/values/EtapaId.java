package org.example.nomemientan.domain.ronda.values;

import co.com.sofka.domain.generic.Identity;

public class EtapaId extends Identity {
    private EtapaId(Integer num) {
        super(num.toString());
    }

    public static EtapaId of(Integer num) {
        return new EtapaId(num);
    }

}
