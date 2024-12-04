package org.example.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.experimental.SuperBuilder;
import org.example.abstracts.IdentifiableEntity;
import org.example.enums.StadiumSectors;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@SuperBuilder
@Entity
@Table(name = "tickets")
public class Ticket extends IdentifiableEntity {
    @Column(name = "creation_datetime", nullable = false, updatable = false)
    private final LocalDateTime creationDateTime = LocalDateTime.now();

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "client_id")
    private Client client;

    @Column(name = "concert_hall", length = 10, nullable = false)
    @NotNull(message = "Concert hall cannot be null")
    @Size(max = 10, message = "Concert hall name length cannot exceed 10 characters")
    private String concertHall;

    @Column(name = "event_code", nullable = false)
    @Min(value = 100, message = "Event code must be at least 100")
    @Max(value = 999, message = "Event code must be at most 999")
    private int eventCode;

    @Column(name = "is_promo", nullable = false)
    private boolean isPromo;

    @Enumerated(EnumType.STRING)
    @Column(name = "stadium_sector")
    private StadiumSectors stadiumSector;

    @Column(name = "max_backpack_weight", nullable = false)
    @Positive(message = "Max backpack weight must be positive")
    private double maxBackpackWeight;

    @Column(name = "price", nullable = false)
    @NotNull(message = "Price cannot be null")
    @DecimalMin(value = "0.1", message = "Price must be greater than 0")
    private BigDecimal price;

    public Ticket() {
    }

    public Ticket(String concertHall, int eventCode, boolean isPromo, StadiumSectors stadiumSector, double maxBackpackWeight, BigDecimal price, Client client) {
        setId();
        this.concertHall = concertHall;
        this.eventCode = eventCode;
        this.isPromo = isPromo;
        this.stadiumSector = stadiumSector;
        this.maxBackpackWeight = maxBackpackWeight;
        this.price = price;
        this.client = client;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "id=" + super.id +
                "id=" + super.getId() +
                ", concertHall='" + concertHall + '\'' +
                ", eventCode=" + eventCode +
                ", time=" + creationDateTime +
                ", time=" + creationDateTime +
                ", isPromo=" + isPromo +
                ", stadiumSector=" + stadiumSector +
                ", maxBackpackWeight=" + maxBackpackWeight +
                ", price=" + price +
                ", client=" + client.getName() +
                ", client id=" + client.getId() +
                '}';
    }

}
