import { useState, useEffect } from "react";
import axios from "axios";
import { useNavigate } from "react-router-dom";


const ItemList = () => {
  const [items, setItems] = useState([]);
  const userId = localStorage.getItem("userId");
  const navigate = useNavigate();

  useEffect(() => {
    axios.get("http://localhost:8080/api/items")
      .then(response => setItems(response.data))
      .catch(error => console.error("Error fetching items:", error));
  }, []);

  const addToCart = async (itemId) => {
    if (!userId) {
      alert("User ID not found! Please log in.");
      return;
    }
    try {
      await axios.post(`http://localhost:8080/api/cart/${userId}/${itemId}`);
      alert("Item added to cart!");
    } catch (error) {
      alert(error.response?.data || "Error: Could not add item");
    }
  };

  return (
    <div>  
      <div style={{ display: "flex", justifyContent: "space-between", alignItems: "center" }}>
        <h2>Items</h2>
        <button onClick={() => navigate("/user/cart")} style={{ padding: "5px 10px", cursor: "pointer" }}>
          Go to Cart 
        </button>
      </div>
      {items.length === 0 ? (
        <p>Loading items...</p>
      ) : (
        items.map(item => (
          <div key={item.itemId} style={{ border: "1px solid black", padding: "10px", margin: "10px" }}>
            <h4>{item.name}</h4>
            <p>{item.description}</p>
            <p>Price: ${item.price}</p>
            <button onClick={() => addToCart(item.itemId)}>Add to Cart</button>
          </div>
        ))
      )}
    </div>
  );
};

export default ItemList;
