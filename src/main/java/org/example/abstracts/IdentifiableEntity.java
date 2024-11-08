package org.example.abstracts;

import org.example.annotations.Nullable;

import java.util.Random;

public abstract class IdentifiableEntity {
    @Nullable(key = "ID must not be NULL")
    protected Integer id;

    public int getId() {
        return this.id;
    }

    public void setId() {
        Random random = new Random();
        this.id = random.nextInt(8999) + 1000;
    }

    ;
}
