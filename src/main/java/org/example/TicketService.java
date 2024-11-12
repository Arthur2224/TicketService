package org.example;

import org.example.entities.BusTicket;
import org.example.utilities.BusTicketUtility;
import org.example.validation.BusTicketValidator;

import java.time.LocalDate;
import java.util.List;

public class TicketService {
    public static void main(String[] args) {
        List<BusTicket> busTicketsList = BusTicketUtility.getBusTicketsFromJSON("src/main/resources/busTickets.json");
        busTicketsList.forEach(System.out::println);
        int totalTickets = busTicketsList.size();
        int validTickets = 0;
        int startDateViolations = 0, priceViolations = 0, typeViolations = 0;
        for (BusTicket ticket : busTicketsList) {
            boolean isValid = true;
            if (!BusTicketValidator.isStartDateValid(ticket.getStartDate())) {
                startDateViolations++;
                isValid = false;
            }
            if (!BusTicketValidator.isTicketTypeValid(ticket.getTicketType())) {
                typeViolations++;
                isValid = false;
            }
            if (!BusTicketValidator.isPriceValid(ticket.getPrice())) {
                priceViolations++;
                isValid = false;
            }
            if (isValid) {
                validTickets++;
            }
        }
        System.out.println("Total Tickets: " + totalTickets);
        System.out.println("Valid Tickets: " + validTickets);
        System.out.println("Start Date Violations: " + startDateViolations);
        System.out.println("Ticket Type Violations: " + typeViolations);
        System.out.println("Price Violations: " + priceViolations);
    }


}

