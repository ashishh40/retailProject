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

      if (isAdmin) {
        setTimeout(() => navigate("/admin"), 1000);
      }
      else{
        setTimeout(() => navigate("/user/home"), 1000);
      }
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
