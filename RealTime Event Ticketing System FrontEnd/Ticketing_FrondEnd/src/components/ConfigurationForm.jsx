import React, { useState } from "react";
import "../../src/assets/ConfigurationForm.css";

const ConfigurationForm = ({ onSubmit }) => {
  const [formData, setFormData] = useState({
    totalTickets: "",
    releaseInterval: "",
    retrievalInterval: "",
    maxCapacity: "",
  });

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData({ ...formData, [name]: value });
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    const { totalTickets, releaseInterval, retrievalInterval, maxCapacity } = formData;
    if (totalTickets && releaseInterval && retrievalInterval && maxCapacity) {
      onSubmit({
        totalTickets: parseInt(totalTickets),
        releaseInterval: parseInt(releaseInterval),
        retrievalInterval: parseInt(retrievalInterval),
        maxCapacity: parseInt(maxCapacity),
      });
      setFormData({
        totalTickets: "",
        releaseInterval: "",
        retrievalInterval: "",
        maxCapacity: "",
      });
    } else {
      alert("Please fill in all fields.");
    }
  };

  return (
    <div className="configuration-form-container">
      <h2 className="config">Enter Configuration</h2>
      <form onSubmit={handleSubmit}>
        {["totalTickets", "releaseInterval", "retrievalInterval", "maxCapacity"].map((field) => (
          <div className="form-group" key={field}>
            <label>{field.replace(/([A-Z])/g, " $1")}:</label>
            <input
              type="number"
              name={field}
              value={formData[field]}
              onChange={handleChange}
            />
          </div>
        ))}
        <button type="submit" className="submit-button">Submit</button>
      </form>
    </div>
  );
};

export default ConfigurationForm;
