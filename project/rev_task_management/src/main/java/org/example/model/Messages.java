package org.example.model;

import java.sql.Timestamp;

public class Messages {
    private int message_Id;

    private Users sender;

    private Users receiver;

    private String subject;

    private String message_description;

    private Timestamp date;

    private Timestamp createdAt;

    private Timestamp updatedAt;
    public Messages(){

    }
    public Messages(int message_Id, Users sender, Users receiver, String subject, String message_description, Timestamp date, Timestamp createdAt, Timestamp updatedAt) {
        this.message_Id = message_Id;
        this.sender = sender;
        this.receiver = receiver;
        this.subject = subject;
        this.message_description = message_description;
        this.date = date;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public int getMessage_Id() {
        return message_Id;
    }

    public void setMessage_Id(int message_Id) {
        this.message_Id = message_Id;
    }

    public Users getSender() {
        return sender;
    }

    public void setSender(Users sender) {
        this.sender = sender;
    }

    public Users getReceiver() {
        return receiver;
    }

    public void setReceiver(Users receiver) {
        this.receiver = receiver;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getMessage_description() {
        return message_description;
    }

    public void setMessage_description(String message_description) {
        this.message_description = message_description;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }
}
