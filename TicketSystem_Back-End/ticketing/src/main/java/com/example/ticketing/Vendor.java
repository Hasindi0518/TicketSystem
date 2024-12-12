package com.example.ticketing;

public class Vendor implements Runnable {

    private final TicketPool tickets;
    private final int ticketOverviewRate;
    private final String vendorName;


    public Vendor(TicketPool ticketPool, int ticketReleaseRate, String vendorName) {
        this.tickets = ticketPool;
        this.ticketOverviewRate = ticketReleaseRate;
        this.vendorName = vendorName;
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            if (tickets.leftTickets()) {
                boolean success = tickets.addTickets(ticketOverviewRate, vendorName);
                if (!success) {
                    System.out.println("Vendor " + vendorName + " cannot add more tickets to the pool.");
                    break;
                }
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    System.out.println("Vendor " + vendorName + " was interrupted while releasing tickets.");
                    break;
                }
            } else {
                System.out.println("Ticket pool is full. Vendor " + vendorName + " cannot add more tickets.");
                break;
            }
        }

        if (!Thread.currentThread().isInterrupted()) {
            System.out.println("Vendor " + vendorName + " has finished releasing tickets.");
        }
    }
}
