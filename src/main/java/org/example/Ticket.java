package org.example;

import org.example.interfaces.Identifiable;
import org.example.interfaces.Printable;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Random;

public class Ticket implements Identifiable, Printable {
    private final long time; // variable doesn't need to be changed
    private int id;
    private String concertHall;
    private int eventCode;
    private boolean isPromo;
    private StadiumSectors stadiumSector;
    private double maxBackpackWeight;
    private BigDecimal price = BigDecimal.valueOf(0.0);

    public Ticket() {
        setId();
        this.time = getCreationTime();
    }

    public Ticket(String concertHall, int eventCode) {
        setId(); // generate id
        setConcertHall(concertHall);
        this.eventCode = eventCode;
        this.time = getCreationTime();
    }

    public Ticket(String concertHall, int eventCode, boolean isPromo, StadiumSectors stadiumSector, double maxBackpackWeight, BigDecimal price) {
        setId();
        setConcertHall(concertHall);
        setEventCode(eventCode);
        this.time = getCreationTime();
        this.isPromo = isPromo;
        this.stadiumSector = stadiumSector;
        this.maxBackpackWeight = maxBackpackWeight;
        setPrice(price);
    }

    public StadiumSectors getStadiumSector() {
        return stadiumSector;
    }
    private long getCreationTime() { // method that provides detection and saves the time of creation
        return System.currentTimeMillis(); // Unix timestamp
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

    public int getId() {
        return this.id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    private void setId() {
        Random random = new Random();
        this.id = random.nextInt(8999) + 1000; // Generates a 4-digit number from 1000 to 9999
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "id=" + id +
                ", concertHall='" + concertHall + '\'' +
                ", eventCode=" + eventCode +
                ", time=" + new Date(time) + // convert a time from unix to more readable format
                ", isPromo=" + isPromo +
                ", stadiumSector=" + stadiumSector +
                ", maxBackpackWeight=" + maxBackpackWeight +
                ", price=" + price +
                '}';
    }
    @Override
    public void print(){
        System.out.println(this.toString());
    }
}
