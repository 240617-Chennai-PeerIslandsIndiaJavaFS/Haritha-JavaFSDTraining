import React, { useContext } from "react";
import { ExpenseContext } from "../context/ExpenseContext";

const ExpenseList = () => {
  const { expenses, removeExpense } = useContext(ExpenseContext);

  return (
    <div>
      <h2>Expenses</h2>
      <ul>
        {expenses.map((expense, index) => (
          <li key={index}>
            {expense.category}: ${expense.amount.toFixed(2)}
            <button onClick={() => removeExpense(index)}>Remove</button>
          </li>
        ))}
      </ul>
    </div>
  );
};

export default ExpenseList;
