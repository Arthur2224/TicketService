package org.example.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.example.enums.StadiumSectors;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@SuperBuilder
@NoArgsConstructor
@Entity
@Table(name = "tickets")
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "creation_date_time")
    private final LocalDateTime creationDateTime = LocalDateTime.now();

    @ManyToOne
    @JoinColumn(name = "client_id", nullable = false)
    private Client client;

    @Column(name = "concert_hall", nullable = false, length = 10)
    private String concertHall;

    @Column(name = "event_code", nullable = false)
    private int eventCode;

    @Column(name = "is_promo", nullable = false)
    private boolean isPromo;

    @Enumerated(EnumType.STRING)
    @Column(name = "stadium_sector", nullable = false)
    private StadiumSectors stadiumSector;

    @Column(name = "max_backpack_weight", nullable = false)
    private double maxBackpackWeight;

    @Column(name = "price", nullable = false)
    private BigDecimal price;

    public Ticket(String concertHall, int eventCode) {
        setConcertHall(concertHall);
        setEventCode(eventCode);
    }

    public Ticket(String concertHall, int eventCode, boolean isPromo, StadiumSectors stadiumSector, double maxBackpackWeight, BigDecimal price) {
        setConcertHall(concertHall);
        setEventCode(eventCode);
        this.isPromo = isPromo;
        this.stadiumSector = stadiumSector;
        this.maxBackpackWeight = maxBackpackWeight;
        setPrice(price);
    }

    public Ticket(String concertHall, int eventCode, boolean isPromo, StadiumSectors stadiumSector, double maxBackpackWeight, BigDecimal price, Client client) {
        setConcertHall(concertHall);
        setEventCode(eventCode);
        this.isPromo = isPromo;
        this.stadiumSector = stadiumSector;
        this.maxBackpackWeight = maxBackpackWeight;
        setPrice(price);
        this.client = client;
    }

    public void setConcertHall(String concertHall) {
        if (concertHall == null || concertHall.length() > 10) {
            throw new IllegalArgumentException("Concert hall name length cannot exceed 10 characters");
        }
        this.concertHall = concertHall;
    }

    public void setEventCode(int eventCode) {
        if (eventCode < 100 || eventCode > 999) {
            throw new IllegalArgumentException("The event code doesn't have the right format");
        }
        this.eventCode = eventCode;
    }

    public void setPrice(BigDecimal price) {
        if (price == null || price.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Price must be greater than 0");
        }
        this.price = price;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "id=" + id +
                ", concertHall='" + concertHall + '\'' +
                ", eventCode=" + eventCode +
                ", creationDateTime=" + creationDateTime +
                ", isPromo=" + isPromo +
                ", stadiumSector=" + stadiumSector +
                ", maxBackpackWeight=" + maxBackpackWeight +
                ", price=" + price +
                ", client name=" + client.getName() +
                '}';
    }
}
