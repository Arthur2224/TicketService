package org.example.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import org.example.enums.TicketClasses;
import org.example.enums.TicketTypes;

import java.time.LocalDate;

public class BusTicket {

    @JsonProperty("ticketClass")
    private TicketClasses ticketClass;

    @JsonProperty("ticketType")
    private TicketTypes ticketType;

    @JsonProperty("startDate")
    private String startDate;

    @JsonProperty("price")
    private int price;

    // Getteri un setteri
    public TicketClasses getTicketClass() {
        return ticketClass;
    }

    public void setTicketClass(TicketClasses ticketClass) {
        this.ticketClass = ticketClass;
    }

    public TicketTypes getTicketType() {
        return ticketType;
    }

    public void setTicketType(TicketTypes ticketType) {
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
