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
  
      console.log("Response Data:", response.data); // Debugging
  
      if (response.data && response.data.userId) { 
        console.log("Trying to store userId:", response.data.userId); // Debugging
  
        localStorage.setItem("userId", response.data.userId.toString()); // Ensure it's stored as a string
  
        console.log("Stored userId in localStorage:", localStorage.getItem("userId")); // Check if stored correctly
      } else {
        console.error("userId is missing in the response");
      }
  
      setMessage("Login successful! Redirecting...");
      setTimeout(() => navigate("/user/home"), 1000);
    } catch (error) {
      console.error("Login failed:", error.response?.data || error);
      setMessage("Login failed: " + (error.response?.data || "Invalid credentials"));
    }
  };
  
  

  return (
    <div>
      <h2>Login</h2>
      {message && <p>{message}</p>}
      <form onSubmit={handleSubmit}>
        <div>
          <label>Username:</label>
          <input
            type="text"
            value={username}
            onChange={(e) => setUsername(e.target.value)}
            required
          />
        </div>
        <div>
          <label>Password:</label>
          <input
            type="password"
            value={password}
            onChange={(e) => setPassword(e.target.value)}
            required
          />
        </div>
        <button type="submit">Login</button>
      </form>
    </div>
  );
};

export default LoginPage;
