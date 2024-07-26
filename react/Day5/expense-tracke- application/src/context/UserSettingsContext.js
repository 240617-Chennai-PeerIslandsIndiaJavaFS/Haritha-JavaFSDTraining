import React, { createContext, useContext, useState } from "react";

const UserSettingsContext = createContext();

export const useUserSettings = () => {
  return useContext(UserSettingsContext);
};

export const UserSettingsProvider = ({ children }) => {
  const [userSettings, setUserSettings] = useState({
    theme: "light",
    currency: "USD",
  });

  return (
    <UserSettingsContext.Provider value={{ userSettings, setUserSettings }}>
      {children}
    </UserSettingsContext.Provider>
  );
};
