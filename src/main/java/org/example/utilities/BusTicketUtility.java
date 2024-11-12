package org.example.utilities;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import org.example.entities.BusTicket;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class BusTicketUtility {
    public static List<BusTicket> getBusTicketsFromJSON(String path) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.findAndRegisterModules();

        List<BusTicket> tickets = null;
        try {

            CollectionType listType = mapper.getTypeFactory().constructCollectionType(List.class, BusTicket.class);
            tickets = mapper.readValue(new File(path), listType);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return tickets;
    }
}
