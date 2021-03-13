package org.example.nomemientan.domain.ronda.values;

import co.com.sofka.domain.generic.Identity;

public class RondaId extends Identity {
    private RondaId(String uid) {
        super(uid);
    }

    public RondaId() {
    }

    public static RondaId of(String uid) {
        return new RondaId(uid);
    }
}
