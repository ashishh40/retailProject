import React from "react";
import { useNavigate } from "react-router-dom";

const HomePage = () => {
  const navigate = useNavigate();

  return (
    <div style={{ display: "flex", justifyContent: "center", alignItems: "center", height: "100vh" }}>
      <div style={{ textAlign: "center", border: "1px solid black", padding: "30px", minWidth: "300px" }}>
        <h1 style={{ marginBottom: "20px" }}>Welcome to Retail App</h1>
        <button style={{ fontSize: "16px", padding: "8px 16px" }} onClick={() => navigate("/login")}>Login</button>
        <button 
          style={{ fontSize: "16px", padding: "8px 16px", marginLeft: "10px" }} 
          onClick={() => navigate("/register")}
        >
          Register
        </button>
      </div>
    </div>
  );
};

export default HomePage;
