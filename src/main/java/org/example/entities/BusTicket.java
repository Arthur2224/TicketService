package org.example.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.example.enums.TicketClasses;

/**
 * Represents a bus ticket with details like ticket class, type, start date, and price.
 * This class is used to map bus ticket data from JSON and handle it within the application.
 *
 */
public class BusTicket {

    @JsonProperty("ticketClass")
    private TicketClasses ticketClass;

    @JsonProperty("ticketType")
    private String ticketType;

    @JsonProperty("startDate")
    private String startDate;

    @JsonProperty("price")
    private int price;


    public TicketClasses getTicketClass() {
        return ticketClass;
    }

    public void setTicketClass(TicketClasses ticketClass) {
        this.ticketClass = ticketClass;
    }

    public String getTicketType() {
        return ticketType;
    }

    public void setTicketType(String ticketType) {
        this.ticketType = ticketType;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "BusTicket{" +
                "ticketClass=" + ticketClass +
                ", ticketType=" + ticketType +
                ", startDate=" + startDate +
                ", price=" + price +
                '}';
    }
}
