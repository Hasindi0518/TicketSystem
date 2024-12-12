package com.example.ticketing;


public class TicketPool {

    private int fullCapacity;
    private int leftTickets;
    private int presentTicketsinPool;



    public TicketPool(int fullCapacity, int allTickets) {
        this.fullCapacity = fullCapacity;
        this.leftTickets = allTickets;
        this.presentTicketsinPool = 0;
    }

    public synchronized boolean addTickets(int ticketsToAdd, String vendorName) {
        if (leftTickets <= 0 || presentTicketsinPool >= fullCapacity) {
            return false;
        }

        int ticketsYetToRelease = Math.min(ticketsToAdd, fullCapacity - presentTicketsinPool);
        ticketsYetToRelease = Math.min(ticketsYetToRelease, leftTickets);

        presentTicketsinPool += ticketsYetToRelease;
        leftTickets -= ticketsYetToRelease;

        System.out.println("Vendor "+vendorName+" added " + ticketsYetToRelease + " tickets. Tickets in pool: " + presentTicketsinPool);

        notifyAll();
        return true;
    }

    public synchronized boolean removeTicket(int ticketsYetToRetrieve, String customerName) {
        while (presentTicketsinPool < ticketsYetToRetrieve) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return false;
            }
        }

        presentTicketsinPool -= ticketsYetToRetrieve;

        System.out.println("Customer " + customerName +" bought " + ticketsYetToRetrieve + " ticket(s). Tickets left in pool: " + presentTicketsinPool);

        notifyAll();  // Notify vendors that space is available
        return true;
    }

    public boolean leftTickets() {
        return leftTickets > 0 || presentTicketsinPool > 0;
    }

    public void set(int fullCapacity, int allTickets) {
        this.fullCapacity = fullCapacity;
        this.leftTickets = allTickets;
    }

}