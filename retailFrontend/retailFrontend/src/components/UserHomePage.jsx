import { useState, useEffect } from "react";
import axios from "axios";
import { useNavigate } from "react-router-dom";


const ItemList = () => {
  const [items, setItems] = useState([]);
  const [category, setCategory] = useState("");

  const userId = localStorage.getItem("userId");
  const navigate = useNavigate();

  useEffect(() => {
    let url = "http://localhost:8080/api/items";
    if (category) {
      url = `http://localhost:8080/api/category/${category}`;
    }
    axios.get(url)
      .then(response => setItems(response.data))
      .catch(error => console.error(error));
  }, [category]);

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

        <div>
        <label>Filter by Category: </label>
        <select value={category} onChange={(e) => setCategory(e.target.value)}>
        <option value="">All</option>
        <option value="Electronics">Electronics</option>
        <option value="Clothing">Clothing</option>
        <option value="Home & Kitchen">Home & Kitchen</option>
        <option value="Books">Books</option>
        <option value="Accessories">Accessories</option>
        <option value="Sports">Sports</option>
      </select>
      </div>

      <button onClick={() => navigate("/user/cart")} style={{ padding: "5px 10px", cursor: "pointer" }}>
          Go to Cart 
      </button>
      </div>
      {items.length === 0 ? (
        <p>Nothing yet on db</p>
      ) : (
        items.map(item => (
          <div key={item.itemId} style={{ border: "1px solid black", padding: "10px", margin: "10px" }}>
            <h4>{item.name}</h4>
            <p>{item.description}</p>
            <p>Price: ${item.price}</p>
            <p>Category: {item.category}</p>
            <button onClick={() => addToCart(item.itemId)}>Add to Cart</button>
          </div>
        ))
      )}
    </div>
  );
};

export default ItemList;
