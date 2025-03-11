import { useState, useEffect } from "react";
import axios from "axios";

const AdminPage = () => {
  const [blockedUsers, setBlockedUsers] = useState([]);

  useEffect(() => {
    fetchBlockedUsers();
  }, []);

  const fetchBlockedUsers = async () => {
    try {
      const response = await axios.get("http://localhost:8080/api/admin/blocked");
      setBlockedUsers(response.data);
    } catch (error) {
      console.error("Error fetching blocked users:", error);
    }
  };

  const unblockUser = async (userId) => {
    try {
      await axios.put(`http://localhost:8080/api/admin/unblock/${userId}`);
      setBlockedUsers(blockedUsers.filter(user => user.userId !== userId));
      alert("User unblocked successfully!");
    } catch (error) {
      console.error("Error unblocking user:", error);
    }
  };

  return (
    <div style={{ display: "flex", justifyContent: "center", alignItems: "center", height: "100vh" }}>
      <div style={{ textAlign: "center", border: "3px solid black", padding: "50px", minWidth: "400px" }}>
        <h1 style={{ marginBottom: "30px", fontSize: "32px" }}>Blocked Users</h1>
        {blockedUsers.length === 0 ? (
          <p style={{ fontSize: "20px" }}>No blocked users found.</p>
        ) : (
          <ul style={{ listStyleType: "none", padding: "0" }}>
            {blockedUsers.map(user => (
              <li key={user.userId} style={{ fontSize: "18px", marginBottom: "15px" }}>
                <span>{user.username} (ID: {user.userId})</span>
                <button 
                  onClick={() => unblockUser(user.userId)} 
                  style={{ 
                    fontSize: "16px", 
                    padding: "8px 15px", 
                    marginLeft: "15px", 
                    cursor: "pointer", 
                    border: "none", 
                    background: "black", 
                    color: "white", 
                    borderRadius: "5px" 
                  }}
                >
                  Unblock
                </button>
              </li>
            ))}
          </ul>
        )}
      </div>
    </div>
  );
};

export default AdminPage;
