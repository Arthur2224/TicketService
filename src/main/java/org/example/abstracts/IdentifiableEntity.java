package org.example.abstracts;


import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.example.annotations.Nullable;

import java.util.Random;

@SuperBuilder
@Getter
@NoArgsConstructor
@MappedSuperclass
public abstract class IdentifiableEntity {
    @Id
    @Nullable(key = "ID must not be NULL")
    protected Long id;

    public void setId() {
        long timestamp = System.currentTimeMillis();
        int randomValue = new Random().nextInt(1000);
        this.id = Long.parseLong(timestamp + String.format("%03d", randomValue));
    }
}
