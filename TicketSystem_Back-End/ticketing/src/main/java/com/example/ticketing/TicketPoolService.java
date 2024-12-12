package com.example.ticketing;

import com.example.ticketing.ConfigurationForm.ConfigurationForm;
import com.example.ticketing.ConfigurationForm.ConfigurationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class TicketPoolService {

    private final TicketPool tickets;
    List<Thread> vendorList = new ArrayList<>();
    List<Thread> customerList = new ArrayList<>();
    private boolean systemRunning = true;
    private int totalCustomers = 5;
    private int totalVendors = 3;

    @Autowired
    public TicketPoolService(TicketPool tickets) {

        this.tickets = tickets;
    }

    public void startSystem() throws IOException {

        // Load configuration details
        ConfigurationForm config = ConfigurationService.loadJson();
        tickets.set(config.getMaxTicketCapacity(), config.getTotalTickets());

        // Start vendor threads
        startVendors(config.getTicketReleaseRate(), totalVendors);

        // Start customer threads
        startCustomers(config.getCustomerRetrievalRate(), totalCustomers);

        System.out.println("Ticket sales system started. Processing ticket sales...");
    }


    public void stopSystem(){
        // Stop vendor threads
        stopThreads(vendorList, "Vendor");

        // Stop customer threads
        stopThreads(customerList, "Customer");

        // Clear all thread lists
        clearThreadLists();

    }


    private void startVendors(int ticketOverviewRate, int allVendors) {
        for (int i = 0; i < allVendors; i++) {
            String vendorId = "Vendor-" + (i + 1);
            Thread vThread = new Thread(new Vendor(tickets, ticketOverviewRate, vendorId));
            vendorList.add(vThread);
            vThread.start();
        }
        System.out.println(allVendors + " vendor threads started.");
    }

    private void startCustomers(int customerOverviewRate, int allCustomers) {
        for (int i = 0; i < totalCustomers; i++) {
            String customerId = "Customer-" + (i + 1);
            com.example.ticketing.Customer customer = new Customer(tickets, customerOverviewRate, customerId);
            Thread cThread = new Thread(customer);
            customerList.add(cThread);
            cThread.start();
        }
        System.out.println(totalCustomers + " customer threads started.");
    }

    private void stopThreads(List<Thread> threadList, String threadType) {
        for (Thread thread : threadList) {
            if (thread.isAlive()) {  // Check if the thread is active
                thread.interrupt();
                System.out.println(threadType + " thread " + thread.getName() + " interrupted.");
            }
        }
    }

    private void clearThreadLists() {
        vendorList.clear();
        customerList.clear();
        System.out.println("All vendor and customer threads cleared.");
    }
}
