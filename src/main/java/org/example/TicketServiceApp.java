package org.example;

import org.example.configs.AppConfig;
import org.example.entities.Client;
import org.example.services.ClientService;
import org.example.services.TicketService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class TicketServiceApp {

    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        ClientService clientService = context.getBean(ClientService.class);
        TicketService ticketService = context.getBean(TicketService.class);

        clientService.printAllClients();
        Client newClient = Client.builder()
                .name("Arthur")
                .email("arthur@gmail.com")
                .build();
        clientService.addClient(newClient);
        clientService.activateClient(newClient);
        newClient.setName("ArThUR");
        clientService.updateClient(newClient);
        clientService.printAllClients();
        clientService.deleteClient(newClient.getId());
        clientService.printAllClients();
        ticketService.loadTickets().stream().forEach(ticket -> System.out.println(ticket));

    }
}
