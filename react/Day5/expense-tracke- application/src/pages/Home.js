import React, { useContext } from "react";
import ExpenseForm from "../components/ExpenseForm";
import ExpenseList from "../components/ExpenseList";
import CategoryList from "../components/CategoryList";
import ResponsiveLayout from "../components/ResponsiveLayout";
import { ExpenseContext } from "../context/ExpenseContext";

const Home = () => {
  const { totalAmount, totalExpenses } = useContext(ExpenseContext);

  return (
    <ResponsiveLayout>
      <h1>Expense Tracker</h1>
      <h2>Total Amount: ${totalAmount.toFixed(2)}</h2>
      <h2>Total Expenses: ${totalExpenses.toFixed(2)}</h2>
      <h2>Remaining Amount: ${(totalAmount - totalExpenses).toFixed(2)}</h2>
      {totalAmount < totalExpenses && (
        <p style={{ color: "red" }}>You have exceeded your total amount!</p>
      )}
      <ExpenseForm />
      <ExpenseList />
      <CategoryList />
      <div>
        <a href="/expenses">View All Expenses</a>
        <a href="/categories">View All Categories</a>
      </div>
    </ResponsiveLayout>
  );
};

export default Home;
