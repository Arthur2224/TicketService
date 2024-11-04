package org.example;

import java.util.ArrayList;
import java.util.List;

public class TicketManager {

  private final List<Ticket> tickets = new ArrayList<>();

  public List<Ticket> getTickets() {
    return tickets;
  }

  public List<Ticket> findAllByStadiumSector(StadiumSectors stadiumSectors) {
    return tickets.stream().filter(o -> o.getStadiumSector().equals(stadiumSectors)).toList();
  }

    public void addTicket(Ticket ticket) {
        tickets.add(ticket);
    }

    public void printAllTickets() {
        for (Ticket ticket : tickets) {
            System.out.println(ticket);
        }
    }

    public Ticket findTicketById(String id) {
        for (Ticket ticket : tickets) {
            if (ticket.getId() == id) {
                return ticket;
            }
        }
        return null;
    }


}
