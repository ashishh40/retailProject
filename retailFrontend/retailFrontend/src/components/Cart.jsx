import { useState, useEffect } from "react";
import axios from "axios";

const Cart = () => {
  const [cart, setCart] = useState([]);
  const userId = localStorage.getItem("userId");

  useEffect(() => {
    if (!userId) {
      console.error("No user ID found in localStorage");
      return;
    }
    axios.get(`http://localhost:8080/api/cart/${userId}`)
      .then(response => setCart(response.data))
      .catch(error => console.error("Error fetching cart items:", error));
  }, [userId]);

  const removeFromCart = async (itemId) => {
    try {
      await axios.delete(`http://localhost:8080/api/cart/${userId}/${itemId}`);
      setCart(cart.filter(cartItem => cartItem.item.itemId !== itemId));
    } catch (error) {
      console.error("Error removing item from cart:", error);
    }
  };

  return (
    <div>
      <h2>Your Cart</h2>
      {cart.length === 0 ? (
        <p>Your cart is empty.</p>
      ) : (
        cart.map(item => (
          <div key={item.item.itemId} style={{ border: "1px solid black", padding: "10px", margin: "10px" }}>
            <h4>{item.item.name}</h4>
            <p>{item.item.description}</p>
            <p>Price: ${item.item.price}</p>
            <button onClick={() => removeFromCart(item.item.itemId)}>Remove</button>
          </div>
        ))
      )}
    </div>
  );
};

export default Cart;
