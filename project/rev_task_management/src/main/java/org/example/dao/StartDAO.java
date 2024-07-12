package org.example.dao;

import org.example.model.Users;

public interface StartDAO {
  public Users Login(String email, String password);
}
