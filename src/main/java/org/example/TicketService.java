package org.example;

import org.example.annotations.AnnotaionProcessor;
import org.example.entities.*;
import org.example.enums.StadiumSectors;
import org.example.utilities.TicketUtility;

import java.math.BigDecimal;

public class TicketService {
    public static void main(String[] args) {

        /*
            IMPORTANT FOR GIT HOMETASK

            In task i must create a new class called TicketService but name is already reserved for main class
            So i decided to change a name for new class to TicketUtility
         */

        //full constructor
        Ticket ticketFullConstructor = new Ticket("Red Rocks", 111, true, StadiumSectors.C, 2.0, BigDecimal.valueOf(5.34));
        //limited constructor
        Ticket ticketLimitedConstructor = new Ticket("La Scala", 102);
        //empty constructor
        Ticket ticketEmptyConstructor = new Ticket();

        // printing the tickets
        System.out.println(ticketFullConstructor);
        System.out.println(ticketLimitedConstructor);
        System.out.println(ticketEmptyConstructor);

        // GIT HOMETASK START FROM HERE
        /*
        TicketUtility ticketUtility =new TicketUtility();
        ticketUtility.addTicket(new Ticket("Red Rocks", 111, true, StadiumSectors.C, 2.0, BigDecimal.valueOf(5.34)));
        ticketUtility.addTicket(new Ticket("Blue Sky", 112, true, StadiumSectors.A, 1.5, BigDecimal.valueOf(4.50)));
        ticketUtility.addTicket(new Ticket("Green Sun", 113, true, StadiumSectors.B, 3.0, BigDecimal.valueOf(6.75)));
        ticketUtility.addTicket(new Ticket("Yellow Sun", 114, true, StadiumSectors.C, 2.5, BigDecimal.valueOf(5.99)));
        ticketUtility.addTicket(new Ticket("Pink Moon", 115, true, StadiumSectors.A, 1.8, BigDecimal.valueOf(4.25)));
        ticketUtility.addTicket(new Ticket("Star", 116, true, StadiumSectors.B, 2.2, BigDecimal.valueOf(7.10)));
        ticketUtility.addTicket(new Ticket("Cloud", 117, true, StadiumSectors.C, 1.2, BigDecimal.valueOf(3.80)));
        ticketUtility.addTicket(new Ticket("Rain", 118, true, StadiumSectors.A, 2.1, BigDecimal.valueOf(6.00)));
        ticketUtility.addTicket(new Ticket("WaterFall", 128, true, StadiumSectors.C, 4.23, BigDecimal.valueOf(9.99)));
        ticketUtility.addTicket(ticketFullConstructor);
        ticketUtility.printAllTickets();
        System.out.println( "Find the ticked by ID ["+ticketFullConstructor.getId()+"]: " + ticketUtility.findTicketById(ticketFullConstructor.getId()));
        */
        // Solution for the latest task
        System.out.println("ShareableTickets");
        ShareableTicket shareableTicket1=new ShareableTicket(StadiumSectors.A);
        shareableTicket1.shared("111111");
        shareableTicket1.shared("11111","email@email.com");

        System.out.println("Users and theirs roles:");
        Admin admin=new Admin();
        admin.printRole();
        admin.print();

        Client client=new Client();
        client.printRole();
        client.print(); // from User class implementation of Printable

        System.out.println("Overwriting equal, hashcode:");

        System.out.println(ticketFullConstructor.equals(new Admin())); // false
        System.out.println(ticketFullConstructor.equals(ticketFullConstructor)); // true
        System.out.println(ticketFullConstructor.equals(ticketLimitedConstructor)); // false
        System.out.println(ticketFullConstructor.hashCode());

        System.out.println("Annotation task:");
        // Checks for fields annotated with @Nullable and prints a warning if they are null
        Ticket nullIdTicket=new Ticket();
        AnnotaionProcessor.checkNullValues(nullIdTicket);
        AnnotaionProcessor.checkNullValues(ticketFullConstructor); // No warning

    }


    }

