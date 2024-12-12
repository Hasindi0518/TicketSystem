import React from "react";
import "../../src/assets/TicketDisplay.css";

const TicketDisplay = ({ totalTickets }) => {
  return (
    <div className="ticket-display-container">
      <h2 className="h22">Current Ticket Availability</h2>
      <p className="p1">Total Tickets: {totalTickets}</p>
    </div>
  );
};

export default TicketDisplay;
