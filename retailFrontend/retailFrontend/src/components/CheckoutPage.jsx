import { useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";
import axios from "axios";

const Checkout = () => {
  const [totalAmount, setTotalAmount] = useState(0);
  const userId = localStorage.getItem("userId");
 
    const navigate = useNavigate();
  useEffect(() => {
    if (!userId) {
      console.error("No user ID found in localStorage");
      return;
    }
    axios.get(`http://localhost:8080/api/checkout/${userId}`)
      .then(response => setTotalAmount(response.data))
      .catch(error => console.error("Error fetching total amount:", error));
  }, [userId]);

  return (
    <div>
      <h2>Checkout</h2>
      <p>Total Bill Amount: <strong>${totalAmount}</strong></p>
      <button onClick={() => navigate("/thankyou")}>Place Order</button>
    </div>
  );
};

export default Checkout;
