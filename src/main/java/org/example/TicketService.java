package org.example;

import org.example.entities.BusTicket;
import org.example.utilities.BusTicketUtility;

import java.time.LocalDate;
import java.util.List;

public class TicketService {
    public static void main(String[] args) {
        List<BusTicket> busTicketsList = BusTicketUtility.getBusTicketsFromJSON("src/main/resources/busTickets.json");
        busTicketsList.forEach(System.out::println);

    }


}

