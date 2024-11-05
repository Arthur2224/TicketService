package org.example.entities;

import org.example.enums.StadiumSectors;
import org.example.interfaces.Identifiable;
import org.example.annotations.Nullable;
import org.example.interfaces.Printable;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Random;

public class Ticket implements Identifiable, Printable {
    @Nullable(key = "ID must not be NULL")
    private Integer id; // switched to Integer because of default value of this type is Null for annotation task
    private long time;
    private String concertHall;
    private int eventCode;
    private boolean isPromo;
    private StadiumSectors stadiumSector;
    private double maxBackpackWeight;
    private BigDecimal price = BigDecimal.valueOf(0.0);

    public Ticket() {
        //setId(); just for checking how ID annotation works
        setTime();
    }

    public Ticket(String concertHall, int eventCode) {
        setId(); // generate id
        setConcertHall(concertHall);
        setTime();
        this.eventCode = eventCode;

    }

    public Ticket(String concertHall, int eventCode, boolean isPromo, StadiumSectors stadiumSector, double maxBackpackWeight, BigDecimal price) {
        setId();
        setConcertHall(concertHall);
        setEventCode(eventCode);
        setTime();
        this.isPromo = isPromo;
        this.stadiumSector = stadiumSector;
        this.maxBackpackWeight = maxBackpackWeight;
        setPrice(price);
    }

    public long getTime() {
        return time;
    }

    public void setTime() {
       this.time= getCreationTime();
    }
    public void setStadiumSector(StadiumSectors stadiumSector){
        this.stadiumSector=stadiumSector;
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
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Ticket ticket = (Ticket) obj;
        return getId() == ticket.getId();
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(getId());
    }
}
