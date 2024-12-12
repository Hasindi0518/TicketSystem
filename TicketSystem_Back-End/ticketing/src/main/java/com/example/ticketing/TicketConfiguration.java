package com.example.ticketing;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TicketConfiguration {
    @Bean
    public TicketPool ticketPool() {
        // Initialize TicketPool with custom values
        int givingTickets = 100;
        int fullCapacity = 200;
        return new TicketPool(givingTickets, fullCapacity);
    }
}
