package com.example.ticketing;


public class Customer implements Runnable {

    private final TicketPool tickets;
    private final int customerOverviewRate;
    private final String customerName;




    public Customer(TicketPool tickets, int customerOverviewRate,  String customerName) {
        this.tickets = tickets;
        this.customerOverviewRate = customerOverviewRate;
        this.customerName = customerName;
    }


    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            if (tickets.leftTickets()) {
                boolean success = tickets.removeTicket(customerOverviewRate, customerName);
                if (!success) {
                    System.out.println("Customer " + customerName + " could not retrieve more tickets.");
                    break;
                }

                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt(); // Restore interrupt flag and exit loop
                    System.out.println("Customer " + customerName + " was interrupted while buying tickets.");
                    break;
                }
            } else {
                System.out.println("No tickets left for customer " + customerName);
                break;
            }
        }
        if (!Thread.currentThread().isInterrupted()) {
            System.out.println("Customer " + customerName + " has finished their ticket purchasing.");
        }
    }

}
