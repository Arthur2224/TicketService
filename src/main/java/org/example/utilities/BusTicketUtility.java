package org.example.utilities;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import org.example.entities.BusTicket;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Utility class for handling bus ticket data operations.
 */
public class BusTicketUtility {
    private static final Logger logger = Logger.getLogger(BusTicketUtility.class.getName());

    /**
     * Reads bus ticket data from a JSON file and deserializes it into a list of BusTicket objects.
     *
     * @param path the path to the JSON file containing bus ticket data.
     * @return a list of BusTicket objects, or an empty list if an error occurs.
     */
    public static List<BusTicket> getBusTicketsFromJSON(String path) {
        ObjectMapper mapper = new ObjectMapper(); // Jackson's ObjectMapper to deserialize JSON to Java objects

        List<BusTicket> tickets = Collections.emptyList();

        try {
            // Define the collection type for deserialization (BusTicket)
            CollectionType listType = mapper.getTypeFactory().constructCollectionType(List.class, BusTicket.class);

            tickets = mapper.readValue(new File(path), listType);
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error reading or deserializing the bus tickets JSON file", e); // Instead of throwing exception and stopping the program, we log the error and return an empty list.

        }

        return tickets;
    }
}
