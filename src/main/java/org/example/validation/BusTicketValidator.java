package org.example.validation;

import org.example.enums.TicketTypes;
import org.example.exceptions.InvalidPriceException;

import java.time.LocalDate;

public class BusTicketValidator {
    public static boolean isStartDateValid(String startDate) {
        if (startDate == null || startDate.isEmpty()) {
            return false;
        }
        LocalDate currentDate = LocalDate.now();
        LocalDate dateofStart = LocalDate.parse(startDate);
        return !dateofStart.isAfter(currentDate);
    }


    public static boolean isTicketTypeValid(String ticketType) {
        if (ticketType == null || ticketType.isEmpty()) {
            return false;
        }

        try {

            TicketTypes type = TicketTypes.valueOf(ticketType);


            return true;
        } catch (IllegalArgumentException e) {
            // If the value is invalid (not a valid enum constant), return false
            return false;
        }
    }


    public static boolean isPriceValid(Integer price) throws InvalidPriceException {
        if (price == null || price <= 0) {
            throw new InvalidPriceException("Price cannot be null or zero or negative");
        }
        if (price % 2 != 0) {
            throw new InvalidPriceException("Ticket price must be an even number");
        }
        return true;
    }

}
