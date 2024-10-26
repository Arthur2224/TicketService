package org.example;


import java.util.Date;
import java.util.Random;

public class Ticket {
    private  String id;
    private String concertHall;
    private int eventCode;
    private final long time; // variable doesn't need to be changed
    private boolean isPromo;
    private StadiumSectors stadiumSector;
    private double maxBackpackWeight;
    private double price=0.0;

    private long getCreationTime(){ // method that's provides detection and saves the time of creation
        return System.currentTimeMillis(); // Unix timestamp
    }
    public void setPrice(double price) { // setter for price
        if(price>0)
            this.price=price;
        else
            throw new IllegalArgumentException("price can not be negative");
    }
    private void setId(String id){ // Private because in other cases (limited or empty constructor sets id automatically, also id, in global, can not be changed because it is a unique value for ticket (identifier). I decided to hide from users ability to set id more than once
        if(id.length()>4)
            throw  new IllegalArgumentException("ID length is too long, max length is 4 symbols");
        else
            this.id=id;
    }
    public void setConcertHall(String concertHall){ // Setter for concertHall
        if(concertHall.length()>10)
            throw  new IllegalArgumentException("ConcertHall name length is too long, max length is 10 chars");
        else
            this.concertHall=concertHall;
    }
    public void setEventCode(int eventCode){ // Setter for eventCode
        if(eventCode<100 || eventCode>999)
            throw new IllegalArgumentException("The event code doesn't have the right format");
        else
            this.eventCode=eventCode;
    }

    private void setId() { // Overloading SetId method, for empty or limited constructors where user doesn't set id
        if (this.id == null) {
            this.id = ""; // initialize id
            Random random = new Random();
            int length = random.nextInt(4) + 2; // length from 2 to 4 (1 is too small)

            for (int i = 0; i < length; i++) {
                int charOrInt = random.nextInt(2); // 0 -char 1- int

                if (charOrInt == 0) { // generate a new char
                    this.id += (char) (random.nextInt(26) + 65); // A-Z unicode
                } else { // generate a int
                    this.id += random.nextInt(10); // 0-9
                }
            }

        }
    }
    public Ticket(String id, String concertHall, int eventCode, boolean isPromo, StadiumSectors stadiumSector, double maxBackpackWeight,double price) {
        setId(id); // checking length
        setConcertHall(concertHall);
        setEventCode(eventCode);
        this.time=getCreationTime();
        this.isPromo = isPromo;
        this.stadiumSector = stadiumSector;
        this.maxBackpackWeight = maxBackpackWeight;
        setPrice(price);
    }
    public Ticket(String concertHall, int eventCode) {
        setId(); // generate id
        setConcertHall(concertHall);
        this.eventCode = eventCode;
        this.time = getCreationTime();
    }

    public Ticket() {
        setId();
        this.time=getCreationTime();
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
                ", price="+ price +
                '}';
    }
}
