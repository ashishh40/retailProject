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
    <div>
      <h2>Blocked Users</h2>
      {blockedUsers.length === 0 ? (
        <p>No blocked users found.</p>
      ) : (
        <ul>
          {blockedUsers.map(user => (
            <li key={user.userId}>
              <span>{user.username} (ID: {user.userId})</span>
              <button onClick={() => unblockUser(user.userId)}>Unblock</button>
            </li>
          ))}
        </ul>
      )}
    </div>
  );
};

export default AdminPage;
