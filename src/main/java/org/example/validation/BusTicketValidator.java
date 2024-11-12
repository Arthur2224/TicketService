package org.example.validation;

import org.example.enums.TicketClasses;
import org.example.enums.TicketTypes;

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


    public static boolean isPriceValid(Integer price) {
        if (price == null || price <= 0) {
            return false;
        }
        return price % 2 == 0;
    }

}
