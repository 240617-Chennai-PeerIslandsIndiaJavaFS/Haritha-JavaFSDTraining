import React, { useContext } from "react";
import { ExpenseContext } from "../context/ExpenseContext";

const ExpensesPage = () => {
  const { expenses } = useContext(ExpenseContext);

  return (
    <div>
      <h1>All Expenses</h1>
      <ul>
        {expenses.map((expense, index) => (
          <li key={index}>
            {expense.category}: ${expense.amount.toFixed(2)}
          </li>
        ))}
      </ul>
    </div>
  );
};

export default ExpensesPage;
