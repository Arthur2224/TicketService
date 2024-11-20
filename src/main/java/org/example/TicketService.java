package org.example;

import org.example.entities.BusTicket;
import org.example.exceptions.InvalidPriceException;
import org.example.utilities.BusTicketUtility;
import org.example.validation.BusTicketValidator;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TicketService {
    private static final String TICKETS_JSON_PATH = "src/main/resources/busTickets.json";
    private static final Logger logger = Logger.getLogger(TicketService.class.getName());

    public static void main(String[] args) {
        List<BusTicket> busTicketsList = BusTicketUtility.getBusTicketsFromJSON(TICKETS_JSON_PATH);
        busTicketsList.forEach(System.out::println);

        int totalTickets = busTicketsList.size();
        int validTickets = 0;
        int startDateViolations = 0, priceViolations = 0, typeViolations = 0;

        for (BusTicket ticket : busTicketsList) {
            boolean isValid = true;

            try {
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
            } catch (InvalidPriceException e) {
                logger.log(Level.INFO, "Invalid price for ticket: " + ticket.getPrice(), e);
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
