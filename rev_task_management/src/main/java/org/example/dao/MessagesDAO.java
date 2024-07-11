package org.example.dao;

import org.example.model.Messages;

import java.util.List;

public interface MessagesDAO<Messages> {
    public Messages create(org.example.model.Messages t);
    public boolean delete(int id);
    public boolean update(org.example.model.Messages t);
    public org.example.model.Messages getId(int id);
    public List<org.example.model.Messages> getAll();
}



