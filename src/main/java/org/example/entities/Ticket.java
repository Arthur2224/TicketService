package org.example.entities;

import org.example.enums.StadiumSectors;
import org.example.abstracts.IdentifiableEntity;
import org.example.annotations.Nullable;
import org.example.interfaces.Printable;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Random;

public class Ticket extends IdentifiableEntity implements Printable {

    private LocalDateTime creationDateTime;
    private String concertHall;
    private int eventCode;
    private boolean isPromo;
    private StadiumSectors stadiumSector;
    private double maxBackpackWeight;
    private BigDecimal price = BigDecimal.valueOf(0.0);

    public Ticket() {
        //setId(); just for checking how ID annotation works
        setDateTime();
    }

    public Ticket(String concertHall, int eventCode) {
        super.setId(); // generate id
        setConcertHall(concertHall);
        setDateTime();
        this.eventCode = eventCode;

    }

    public Ticket(String concertHall, int eventCode, boolean isPromo, StadiumSectors stadiumSector, double maxBackpackWeight, BigDecimal price) {
        setId();
        setConcertHall(concertHall);
        setEventCode(eventCode);
        setDateTime();
        this.isPromo = isPromo;
        this.stadiumSector = stadiumSector;
        this.maxBackpackWeight = maxBackpackWeight;
        setPrice(price);
    }


    public void setDateTime() {
        this.creationDateTime = getCreationDateTime();
    }

    public void setStadiumSector(StadiumSectors stadiumSector) {
        this.stadiumSector = stadiumSector;
    }

    public StadiumSectors getStadiumSector() {
        return stadiumSector;
    }

    private LocalDateTime getCreationDateTime() { // method that provides detection and saves the time of creation
        return LocalDateTime.now(); // Unix timestamp
    }

    public void setPrice(BigDecimal price) { // setter for price
        if (price.compareTo(BigDecimal.ZERO) > 0)
            this.price = price;
        else
            throw new IllegalArgumentException("Price cannot be negative");
    }

    public void setConcertHall(String concertHall) { // Setter for concertHall
        if (concertHall.length() > 10)
            throw new IllegalArgumentException("ConcertHall name length is too long, max length is 10 chars");
        else
            this.concertHall = concertHall;
    }

    public void setEventCode(int eventCode) { // Setter for eventCode
        if (eventCode < 100 || eventCode > 999)
            throw new IllegalArgumentException("The event code doesn't have the right format");
        else
            this.eventCode = eventCode;
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
        return Integer.hashCode(getId());
    }
}
