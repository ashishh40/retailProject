import React from "react";
import { Routes, Route } from "react-router-dom";
import HomePage from "./components/HomePage";
import RegisterPage from "./components/RegisterPage";
import LoginPage from "./components/LoginPage";
import UserHomePage from "./components/UserHomePage";
import Cart from "./components/Cart";
import AdminPage from "./components/AdminPage";
import CheckoutPage from "./components/CheckoutPage";
import ThankyouPage from "./components/ThankyouPage";





const App = () => {
  return (
    <Routes>
      <Route path="/" element={<HomePage />} />
      <Route path="/register" element={<RegisterPage />} />
      <Route path="/login" element={<LoginPage />} />
      <Route path="/user/home" element={<UserHomePage />} />
      <Route path="/user/cart" element={<Cart />} />
      <Route path="/admin" element={<AdminPage />} />
      <Route path="/checkout" element={<CheckoutPage />} />
      <Route path="/thankyou" element={<ThankyouPage />} />


    </Routes>
  );
};

export default App;
