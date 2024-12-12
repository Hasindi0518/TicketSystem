package com.example.ticketing;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/ticket")
public class TicketPoolController {

    private final TicketPool tickets;
    private final TicketPoolService service;
    private boolean systemRunning = false;

    public TicketPoolController(TicketPool tickets, TicketPoolService service) {
        this.tickets = tickets;
        this.service = service;
    }

    @PostMapping("/start")
    public String startSystem() throws IOException {
        try {
            // Attempt to start the system
            service.startSystem();
            System.out.println("System has been started successfully."); // Log the success
            return "System started successfully.";
        } catch (IOException e) {
            System.err.println("IOException occurred while starting the system: " + e.getMessage()); // Log the error
            return "Failed to start the system due to an input/output error: " + e.getMessage();
        } catch (Exception e) {
            System.err.println("Unexpected error occurred: " + e.getMessage()); // Log the error
            return "Failed to start the system due to an unexpected error: " + e.getMessage();
        }
    }

    @PostMapping("/stop")
    public String stopSystem() {
        try {

            String successMessage = "System stopped successfully";

            System.out.println(successMessage);
            return successMessage;
        } catch (IllegalStateException e) {
            // Handle specific exception
            System.err.println("System is not running: " + e.getMessage());
            return "Failed to stop the system: System is not running.";
        } catch (Exception e) {
            // Handle generic exception
            System.err.println("Unexpected error occurred: " + e.getMessage());
            return "Failed to stop the system: " + e.getMessage();
        }
    }
}
