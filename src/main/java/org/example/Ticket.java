package org.example;


import java.math.BigDecimal;
import java.util.Date;
import java.util.Random;

public class Ticket {
    private final long time; // variable doesn't need to be changed
    private String id;
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



    private long getCreationTime() { // method that's provides detection and saves the time of creation
        return System.currentTimeMillis(); // Unix timestamp
    }

    public void setPrice(BigDecimal price) { // setter for price
        if (price.compareTo(BigDecimal.ZERO) > 0)
            this.price = price;
        else
            throw new IllegalArgumentException("price can not be negative");
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

    public String getId(){
        return this.id;
    }
    private void setId() {
        if (this.id == null) {
            this.id = ""; // initialize id
            Random random = new Random();
            int length = random.nextInt(3) + 2; // length from 2 to 4 (1 is too small)

            for (int i = 0; i < length; i++) {
                int charOrInt = random.nextInt(2); // 0 for char, 1 for int

                if (charOrInt == 0) {
                    this.id += (char) (random.nextInt(26) + 65); // A-Z unicode
                } else { // generate a int
                    this.id += random.nextInt(10); // 0-9
                }
            }

        }
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "id='" + id + '\'' +
                ", concertHall='" + concertHall + '\'' +
                ", eventCode=" + eventCode +
                ", time=" + new Date(time) + // convert a time from unix to more readable format
                ", isPromo=" + isPromo +
                ", stadiumSector=" + stadiumSector +
                ", maxBackpackWeight=" + maxBackpackWeight +
                ", price=" + price +
                '}';
    }
}
