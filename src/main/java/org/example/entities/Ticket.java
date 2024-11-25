package org.example.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Data;

import lombok.experimental.SuperBuilder;
import org.example.abstracts.IdentifiableEntity;
import org.example.enums.StadiumSectors;
import org.example.interfaces.Printable;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Data
@SuperBuilder
@Table(name = "tickets")
public class Ticket extends IdentifiableEntity implements Printable {
    @Column(name = "creation_datetime", nullable = false)
    private final LocalDateTime creationDateTime = LocalDateTime.now();

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id", referencedColumnName = "id", nullable = false)
    private Client client;

    @Size(max = 10, message = "Concert hall name length cannot exceed 10 characters")
    @Column(name = "concert_hall", nullable = false, length = 10)
    private String concertHall;

    @Column(name = "event_code", nullable = false)
    private int eventCode;
    @Column(name = "is_promo")
    private boolean isPromo;

    @Enumerated(EnumType.STRING)
    @Column(name = "stadium_sector")
    private StadiumSectors stadiumSector;

    @Column(name = "max_backpack_weight")
    private double maxBackpackWeight;

    @Positive(message = "Price must be greater than 0")
    @Column(name = "price", nullable = false, precision = 10, scale = 2)
    private BigDecimal price = BigDecimal.valueOf(0.0);

    public Ticket() {
        super.setId();
    }

    public Ticket(String concertHall, int eventCode) {
        super.setId();
        setConcertHall(concertHall);
        this.eventCode = eventCode;
    }

    public Ticket(String concertHall, int eventCode, boolean isPromo, StadiumSectors stadiumSector, double maxBackpackWeight, BigDecimal price) {
        super.setId();
        setConcertHall(concertHall);
        setEventCode(eventCode);
        this.isPromo = isPromo;
        this.stadiumSector = stadiumSector;
        this.maxBackpackWeight = maxBackpackWeight;
        setPrice(price);
    }
    public Ticket(String concertHall, int eventCode, boolean isPromo, StadiumSectors stadiumSector, double maxBackpackWeight, BigDecimal price,Client client) {
        super.setId();
        setConcertHall(concertHall);
        setEventCode(eventCode);
        this.isPromo = isPromo;
        this.stadiumSector = stadiumSector;
        this.maxBackpackWeight = maxBackpackWeight;
        setPrice(price);
        this.client=client;
    }

    public void setStadiumSector(StadiumSectors stadiumSector) {
        this.stadiumSector = stadiumSector;
    }

    public void setEventCode(int eventCode) {
        if (eventCode < 100 || eventCode > 999) {
            throw new IllegalArgumentException("The event code doesn't have the right format");
        } else {
            this.eventCode = eventCode;
        }
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "id=" + super.id +
                ", concertHall='" + concertHall + '\'' +
                ", eventCode=" + eventCode +
                ", time=" + creationDateTime + // convert a time from unix to more readable format
                ", isPromo=" + isPromo +
                ", stadiumSector=" + stadiumSector +
                ", maxBackpackWeight=" + maxBackpackWeight +
                ", price=" + price +
                '}';
    }

    @Override
    public void print() {
        System.out.println(this.toString());
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true; // by reference at the same part of memory
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Ticket ticket = (Ticket) obj;
        return super.getId() == ticket.getId();
    }

    @Override
    public int hashCode() {
        return Long.hashCode(getId());
    }
}
