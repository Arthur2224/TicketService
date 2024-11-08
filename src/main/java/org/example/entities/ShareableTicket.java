package org.example.entities;

import org.example.enums.StadiumSectors;

public class ShareableTicket extends Ticket {
    public ShareableTicket(StadiumSectors stadiumSector) {
        super.setStadiumSector(stadiumSector);
    }

    public void shared(String phone) {
        System.out.println("Ticket shared via phone: " + phone);
    }

    public void shared(String phone, String email) {
        System.out.println("Ticket shared via phone: " + phone + " and email: " + email);
    }
}
