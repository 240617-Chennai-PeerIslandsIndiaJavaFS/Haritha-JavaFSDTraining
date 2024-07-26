import React, { useContext } from "react";
import { ExpenseContext } from "../context/ExpenseContext";

const CategoryList = () => {
  const { categoryBreakdown } = useContext(ExpenseContext);

  return (
    <div>
      <h2>Category Breakdown</h2>
      <ul>
        {categoryBreakdown.map(({ category, total }) => (
          <li key={category}>
            {category}: ${total.toFixed(2)}
          </li>
        ))}
      </ul>
    </div>
  );
};

export default CategoryList;
