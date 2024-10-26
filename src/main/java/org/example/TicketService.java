package org.example;

public class TicketService {
    public static void main(String[] args) {

        //full constructor
        Ticket ticket1 = new Ticket("AE86", "Red Rocks", 111, true, StadiumSectors.C, 2.0,100.25);
        //limited constructor
        Ticket ticket2 = new Ticket("La Scala", 102);
        //empty constructor
        Ticket ticket3 = new Ticket();

        // printing the tickets
        System.out.println(ticket1);
        System.out.println(ticket2);
        System.out.println(ticket3);





        }
    }
