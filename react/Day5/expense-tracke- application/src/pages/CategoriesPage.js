import React, { useContext } from "react";
import { ExpenseContext } from "../context/ExpenseContext";

const CategoriesPage = () => {
  const { categories } = useContext(ExpenseContext);

  return (
    <div>
      <h1>All Categories</h1>
      <ul>
        {categories.map((category, index) => (
          <li key={index}>{category}</li>
        ))}
      </ul>
    </div>
  );
};

export default CategoriesPage;
