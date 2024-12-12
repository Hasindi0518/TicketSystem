import React, { useState } from "react";
import ConfigurationForm from "./components/ConfigurationForm";
import TicketDisplay from "./components/TicketDisplay";
import ControlPanel from "./components/ControlPanel";
import LogDisplay from "./components/LogDisplay";
import "./App.css";

const App = () => {
  const [config, setConfig] = useState(null);
  const [logs, setLogs] = useState([]);
  const [totalTickets, setTotalTickets] = useState(0);

  const handleConfigSubmit = (config) => {
    setConfig(config);
    setTotalTickets(config.totalTickets);
    setLogs([...logs, "Configuration submitted successfully."]);
  };

  const startSystem = async () => {
    try {
      const response = await fetch("http://localhost:8052/ticket/start", {
        method: "POST",
      });
      if (response.ok) {
        const message = await response.text();
        setLogs([...logs, message]);
      } else {
        setLogs([...logs, "Failed to start the system."]);
      }
    } catch (error) {
      setLogs([...logs, `Error starting system: ${error.message}`]);
    }
  };

  const stopSystem = async () => {
    try {
      const response = await fetch("http://localhost:8052/ticket/stop", {
        method: "POST",
      });
      if (response.ok) {
        const message = await response.text();
        setLogs([...logs, message]);
      } else {
        setLogs([...logs, "Failed to stop the system."]);
      }
    } catch (error) {
      setLogs([...logs, `Error stopping system: ${error.message}`]);
    }
  };

  return (
    <div className="App">
      <h1 className="h12">Real-Time Event Ticketing System</h1>
      {!config ? (
        <ConfigurationForm onSubmit={handleConfigSubmit} />
      ) : (
        <>
          <TicketDisplay totalTickets={totalTickets} />
          <ControlPanel onStartSystem={startSystem} onStopSystem={stopSystem} />
          <LogDisplay logs={logs} />
        </>
      )}
    </div>
  );
};

export default App;

