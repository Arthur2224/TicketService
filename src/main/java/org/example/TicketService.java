package org.example;

import java.math.BigDecimal;

public class TicketService {
    public static void main(String[] args) {

        /*
            IMPORTANT FOR GIT HOMETASK

            In task i must create a new class called TicketService but name is already reserved for main class
            So i decided to change a name for new class to TicketManager
         */

        //full constructor
        Ticket ticket_1 = new Ticket("Red Rocks", 111, true, StadiumSectors.C, 2.0, BigDecimal.valueOf(5.34));
        //limited constructor
        Ticket ticket_2 = new Ticket("La Scala", 102);
        //empty constructor
        Ticket ticket_3 = new Ticket();

        // printing the tickets
        System.out.println(ticket_1);
        System.out.println(ticket_2);
        System.out.println(ticket_3);

        // GIT HOMETASK START FROM HERE

        TicketManager ticketManager=new TicketManager();
        ticketManager.addTicket(new Ticket("Red Rocks", 111, true, StadiumSectors.C, 2.0, BigDecimal.valueOf(5.34)));
        ticketManager.addTicket(new Ticket("Blue Sky", 112, true, StadiumSectors.A, 1.5, BigDecimal.valueOf(4.50)));
        ticketManager.addTicket(new Ticket("Green Sun", 113, true, StadiumSectors.B, 3.0, BigDecimal.valueOf(6.75)));
        ticketManager.addTicket(new Ticket("Yellow Sun", 114, true, StadiumSectors.C, 2.5, BigDecimal.valueOf(5.99)));
        ticketManager.addTicket(new Ticket("Pink Moon", 115, true, StadiumSectors.A, 1.8, BigDecimal.valueOf(4.25)));
        ticketManager.addTicket(new Ticket("Star", 116, true, StadiumSectors.B, 2.2, BigDecimal.valueOf(7.10)));
        ticketManager.addTicket(new Ticket("Cloud", 117, true, StadiumSectors.C, 1.2, BigDecimal.valueOf(3.80)));
        ticketManager.addTicket(new Ticket("Rain", 118, true, StadiumSectors.A, 2.1, BigDecimal.valueOf(6.00)));
        ticketManager.addTicket(new Ticket("WaterFall", 128, true, StadiumSectors.C, 4.23, BigDecimal.valueOf(9.99)));
        ticketManager.addTicket(ticket_1);




    }
}
