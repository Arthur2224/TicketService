package org.example.abstracts;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.example.annotations.Nullable;

import java.util.Random;

@SuperBuilder
@Getter
@NoArgsConstructor
public abstract class IdentifiableEntity {
    @Nullable(key = "ID must not be NULL")
    protected long id;

    public void setId() {
        long timestamp = System.currentTimeMillis();
        int randomValue = new Random().nextInt(1000);
        id = Long.parseLong(timestamp + String.format("%03d", randomValue));
    }
}
