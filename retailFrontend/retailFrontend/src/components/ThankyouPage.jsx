import { useNavigate } from "react-router-dom";

const ThankYou = () => {
  const navigate = useNavigate();
  
  return (
    <div>
      <h2>Thank You!</h2>
      <p>Your order has been placed successfully.</p>
      <button onClick={() => navigate("/user/home")}>Go to Home</button>
    </div>
  );
};

export default ThankYou;
