import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import axios from "axios";

const RegisterPage = () => {
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");
  const [message, setMessage] = useState("");
  const navigate = useNavigate();

  const handleSubmit = async (e) => {
    e.preventDefault();
    setMessage("");

    try {
      await axios.post("http://localhost:8080/api/register", {
        username,
        password,
      });

      setMessage("Registration successful! Redirecting...");
      setTimeout(() => navigate("/login"), 1000);
    } catch (error) {
      setMessage("Registration failed: " + (error.response?.data?.message || "Something went wrong"));
    }
  };

  return (
    <div style={{ display: "flex", justifyContent: "center", alignItems: "center", height: "100vh" }}>
      <div style={{ textAlign: "center", border: "3px solid black", padding: "50px", minWidth: "400px" }}>
        <h1 style={{ marginBottom: "30px", fontSize: "32px" }}>Register</h1>
        {message && <p style={{ fontSize: "20px" }}>{message}</p>}
        <form onSubmit={handleSubmit}>
          <div style={{ marginBottom: "20px" }}>
            <label style={{ fontSize: "20px", display: "block" }}>Username:</label>
            <input
              type="text"
              value={username}
              onChange={(e) => setUsername(e.target.value)}
              required
              style={{ fontSize: "18px", padding: "10px", width: "100%" }}
            />
          </div>
          <div style={{ marginBottom: "20px" }}>
            <label style={{ fontSize: "20px", display: "block" }}>Password:</label>
            <input
              type="password"
              value={password}
              onChange={(e) => setPassword(e.target.value)}
              required
              style={{ fontSize: "18px", padding: "10px", width: "100%" }}
            />
          </div>
          <button type="submit" style={{ fontSize: "20px", padding: "12px 20px", cursor: "pointer" }}>Register</button>
        </form>
      </div>
    </div>
  );
};

export default RegisterPage;
