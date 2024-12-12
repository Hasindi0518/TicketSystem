import React from "react";
import "../../src/assets/ControlPanel.css";

const ControlPanel = ({ onStartSystem, onStopSystem }) => {
  return (
    <div className="control-panel-container">
      <button onClick={onStartSystem} className="start-button">Start System</button>
      <button onClick={onStopSystem} className="stop-button">Stop System</button>
    </div>
  );
};

export default ControlPanel;
