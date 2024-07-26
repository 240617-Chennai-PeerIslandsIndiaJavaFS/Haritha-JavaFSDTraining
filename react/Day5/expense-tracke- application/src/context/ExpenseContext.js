import React, { createContext, useMemo, useState } from "react";

export const ExpenseContext = createContext();

export const ExpenseProvider = ({ children }) => {
  const [expenses, setExpenses] = useState([]);
  const [categories, setCategories] = useState([]);
  const [totalAmount, setTotalAmount] = useState(0);

  const addExpense = (expense) => {
    setExpenses([...expenses, expense]);
    setTotalAmount((prevTotal) => prevTotal - expense.amount);
  };

  const addCategory = (category) => {
    setCategories([...categories, category]);
  };

  const removeExpense = (index) => {
    const expenseToRemove = expenses[index];
    setExpenses(expenses.filter((_, i) => i !== index));
    setTotalAmount((prevTotal) => prevTotal + expenseToRemove.amount);
  };

  const totalExpenses = useMemo(() => {
    return expenses.reduce((total, expense) => total + expense.amount, 0);
  }, [expenses]);

  const categoryBreakdown = useMemo(() => {
    return categories.map((category) => ({
      category,
      total: expenses
        .filter((expense) => expense.category === category)
        .reduce((total, expense) => total + expense.amount, 0),
    }));
  }, [expenses, categories]);

  return (
    <ExpenseContext.Provider
      value={{
        expenses,
        categories,
        totalAmount,
        setTotalAmount,
        addExpense,
        addCategory,
        removeExpense,
        totalExpenses,
        categoryBreakdown,
      }}
    >
      {children}
    </ExpenseContext.Provider>
  );
};
