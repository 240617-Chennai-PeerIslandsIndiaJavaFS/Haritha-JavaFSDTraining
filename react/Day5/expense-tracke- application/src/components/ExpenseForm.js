import React, { useRef, useContext, useState } from "react";
import { ExpenseContext } from "../context/ExpenseContext";

const ExpenseForm = () => {
  const {
    addExpense,
    categories,
    addCategory,
    setTotalAmount,
    totalAmount,
    totalExpenses,
  } = useContext(ExpenseContext);
  const amountRef = useRef();
  const categoryRef = useRef();
  const newCategoryRef = useRef();
  const totalAmountRef = useRef();
  const [errorMessage, setErrorMessage] = useState("");

  const handleSubmit = (e) => {
    e.preventDefault();
    const amount = parseFloat(amountRef.current.value);
    const category = categoryRef.current.value;

    if (totalAmount - totalExpenses - amount < 0) {
      setErrorMessage("Insufficient funds to add this expense.");
      return;
    }

    if (amount && category) {
      const newExpense = { amount, category };
      addExpense(newExpense);
      amountRef.current.value = "";
      setErrorMessage("");
    }
  };

  const handleAddCategory = (e) => {
    e.preventDefault();
    const newCategory = newCategoryRef.current.value.trim();

    if (newCategory && !categories.includes(newCategory)) {
      addCategory(newCategory);
      newCategoryRef.current.value = "";
    }
  };

  const handleSetTotalAmount = (e) => {
    e.preventDefault();
    const totalAmount = parseFloat(totalAmountRef.current.value);
    if (totalAmount) {
      setTotalAmount(totalAmount);
      totalAmountRef.current.value = "";
    }
  };

  return (
    <div>
      <h2>Add Expense</h2>
      <form onSubmit={handleSubmit}>
        <input
          type="number"
          placeholder="Amount"
          ref={amountRef}
          step="0.01"
          required
        />
        <select ref={categoryRef} required>
          <option value="">Select Category</option>
          {categories.map((category, index) => (
            <option key={index} value={category}>
              {category}
            </option>
          ))}
        </select>
        <button type="submit">Add Expense</button>
      </form>
      {errorMessage && <p style={{ color: "red" }}>{errorMessage}</p>}

      <h2>Add Category</h2>
      <form onSubmit={handleAddCategory}>
        <input
          type="text"
          placeholder="New Category"
          ref={newCategoryRef}
          required
        />
        <button type="submit">Add Category</button>
      </form>

      <h2>Set Total Amount</h2>
      <form onSubmit={handleSetTotalAmount}>
        <input
          type="number"
          placeholder="Total Amount"
          ref={totalAmountRef}
          step="0.01"
          required
        />
        <button type="submit">Set Total Amount</button>
      </form>
    </div>
  );
};

export default ExpenseForm;
