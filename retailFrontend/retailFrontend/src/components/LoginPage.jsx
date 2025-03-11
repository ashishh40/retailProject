import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import axios from "axios";

const LoginPage = () => {
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");
  const [message, setMessage] = useState("");
  const navigate = useNavigate();

  const handleSubmit = async (e) => {
    e.preventDefault();
    setMessage("");

    try {
      const response = await axios.post("http://localhost:8080/api/login/user", {
        username,
        password,
      });

      const { userId, isAdmin } = response.data;
      localStorage.setItem("userId", userId);
      localStorage.setItem("isAdmin", isAdmin);

      setMessage("Login successful! Redirecting...");

      setTimeout(() => {
        isAdmin ? navigate("/admin") : navigate("/user/home");
      }, 1000);
    } catch (error) {
      console.error("Login failed:", error.response?.data || error);
      setMessage("Login failed: " + (error.response?.data || "Invalid credentials"));
    }
  };

  return (
    <div style={{ display: "flex", justifyContent: "center", alignItems: "center", height: "100vh" }}>
      <div style={{ textAlign: "center", border: "3px solid black", padding: "50px", minWidth: "400px" }}>
        <h1 style={{ marginBottom: "30px", fontSize: "32px" }}>Login</h1>
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
          <button type="submit" style={{ fontSize: "20px", padding: "12px 20px", cursor: "pointer" }}>Login</button>
        </form>
      </div>
    </div>
  );
};

export default LoginPage;
