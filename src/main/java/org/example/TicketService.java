package org.example;

import org.example.custom_collections.CustomArrayList;
import org.example.custom_collections.CustomHashSet;
import org.example.entities.Ticket;

public class TicketService {
    public static void main(String[] args) {
        CustomArrayList<String> list = new CustomArrayList<>();
        list.put("Hello");
        list.put("World");
        list.put("It is my own custom");
        list.put("ArrayList");

        System.out.println(list.get(0) + "!");
        System.out.println("List: " + list);
        System.out.println("List size: " + list.size());

        System.out.println("Element at index 1: " + list.get(1));

        list.delete(2);

        System.out.println("List after deletion: " + list);
        System.out.println("List size after deletion: " + list.size());

        CustomArrayList<Ticket> ticketList = new CustomArrayList<>();
        ticketList.put(new Ticket());
        ticketList.put(new Ticket("hall", 212));
        ticketList.put(new Ticket("hall_2", 111));
        System.out.println("Ticket List: " + ticketList);
        ticketList.delete(0);
        System.out.println("Tickets after deletion:");
        for (Ticket ticket : ticketList
        ) {
            System.out.println(ticket);

        }

        CustomHashSet<String> set = new CustomHashSet<>();

        set.add("Cat");
        set.add("Dog");
        set.add("Frog");

        boolean addedDuplicate = set.add("Cat");
        System.out.println("Tried to add duplicate 'Cat': " + addedDuplicate);

        System.out.println("Set contains 'Cat': " + set.contains("Cat"));
        System.out.println("Set contains 'DOG': " + set.contains("DOG"));

        boolean removed = set.remove("Frog");
        System.out.println("Removed 'Frog': " + removed);

        System.out.println("Elements in set:");
        set.iterate();

        System.out.println("Size of set: " + set.size());

    }


}

