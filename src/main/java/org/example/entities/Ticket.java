package org.example.entities;

import lombok.Data;
import lombok.experimental.SuperBuilder;
import org.example.abstracts.IdentifiableEntity;
import org.example.enums.StadiumSectors;
import org.example.interfaces.Printable;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@SuperBuilder
public class Ticket extends IdentifiableEntity implements Printable {
    private final LocalDateTime creationDateTime = LocalDateTime.now();
    private Client client;
    private String concertHall;
    private int eventCode;
    private boolean isPromo;
    private StadiumSectors stadiumSector;
    private double maxBackpackWeight;
    private BigDecimal price = BigDecimal.valueOf(0.0);

    public Ticket() {
    }

    public Ticket(String concertHall, int eventCode) {
        super.setId();
        setConcertHall(concertHall);
        setEventCode(eventCode);
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

    public Ticket(String concertHall, int eventCode, boolean isPromo, StadiumSectors stadiumSector, double maxBackpackWeight, BigDecimal price, Client client) {
        super.setId();
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
                "id=" + super.getId() +
                ", concertHall='" + concertHall + '\'' +
                ", eventCode=" + eventCode +
                ", time=" + creationDateTime +
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
            return true;
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
