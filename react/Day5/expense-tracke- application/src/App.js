import React from "react";
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import { ExpenseProvider } from "./context/ExpenseContext";
import { UserSettingsProvider } from "./context/UserSettingsContext";
import Header from "./components/Header";
import Home from "./pages/Home";
import ExpensesPage from "./pages/ExpensesPage";
import CategoriesPage from "./pages/CategoriesPage";
import NotFound from "./pages/NotFound";

const App = () => {
  return (
    <Router>
      <UserSettingsProvider>
        <ExpenseProvider>
          <Header />
          <Routes>
            <Route path="/" element={<Home />} />
            <Route path="/expenses" element={<ExpensesPage />} />
            <Route path="/categories" element={<CategoriesPage />} />
            <Route path="*" element={<NotFound />} />
          </Routes>
        </ExpenseProvider>
      </UserSettingsProvider>
    </Router>
  );
};

export default App;
