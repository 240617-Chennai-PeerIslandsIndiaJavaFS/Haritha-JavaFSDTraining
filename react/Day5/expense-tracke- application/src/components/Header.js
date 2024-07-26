import React from "react";
import { Link } from "react-router-dom";
import "./Header.css";

const Header = () => {
  return (
    <nav>
      <Link to="/">Home</Link>
      <Link to="/expenses">Expenses</Link>
      <Link to="/categories">Categories</Link>
    </nav>
  );
};

export default Header;
