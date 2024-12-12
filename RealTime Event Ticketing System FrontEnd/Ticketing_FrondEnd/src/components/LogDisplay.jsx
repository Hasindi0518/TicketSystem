import React from "react";
import "../../src/assets/LogDisplay.css";

const LogDisplay = ({ logs }) => {
  return (
    <div className="log-display-container">
      <h2 className="h1">System Logs</h2>
      <ul>
        {logs.map((log, index) => (
          <li key={index}>{log}</li>
        ))}
      </ul>
    </div>
  );
};

export default LogDisplay;
