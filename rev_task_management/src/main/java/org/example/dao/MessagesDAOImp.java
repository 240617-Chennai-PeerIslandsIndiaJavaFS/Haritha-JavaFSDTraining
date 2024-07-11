package org.example.dao;

import org.example.connection.DBConnection;
import org.example.model.Messages;
import org.example.model.Users;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MessagesDAOImp implements MessagesDAO {
    private static final Logger logger = LoggerFactory.getLogger(MessagesDAOImp.class);
    static Connection con;
    Users users = new Users();

    public MessagesDAOImp() {
        con = DBConnection.getConnection();
    }

    @Override
    public Messages create(Messages message) {
        String query = "INSERT INTO messages(sender_id, receiver_id, subject, message_description, date, created_at, updated_at) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement ptmt = con.prepareStatement(query);
            ptmt.setInt(1, message.getSender().getUser_id());
            ptmt.setInt(2, message.getReceiver().getUser_id());
            ptmt.setString(3, message.getSubject());
            ptmt.setString(4, message.getMessage_description());
            ptmt.setTimestamp(5, new java.sql.Timestamp(message.getDate().getTime()));
            ptmt.setTimestamp(6, new java.sql.Timestamp(message.getCreatedAt().getTime()));
            ptmt.setTimestamp(7, new java.sql.Timestamp(message.getUpdatedAt().getTime()));
            ptmt.executeUpdate();

            logger.info("Message created successfully: {}", message);
            return message;
        } catch (SQLException ex) {
            logger.error("SQLException occurred: {}", ex.getMessage());
            System.out.println("SQLException occurred: " + ex.getMessage());
        }
        return null;
    }

    @Override
    public boolean delete(int id) {
        String query = "DELETE FROM messages WHERE message_id = ?";
        try {
            PreparedStatement ptmt = con.prepareStatement(query);
            ptmt.setInt(1, id);
            int row = ptmt.executeUpdate();
            logger.info("{} row(s) deleted", row);
            return row > 0;
        } catch (SQLException ex) {
            logger.error("SQLException occurred: {}", ex.getMessage());
            System.out.println("SQLException occurred: " + ex.getMessage());
        }
        return false;
    }

    @Override
    public boolean update(Messages message) {
        String query = "UPDATE messages SET sender_id = ?, receiver_id = ?, subject = ?, message_description = ?, date = ?, updated_at = ? WHERE message_id = ?";
        try {
            PreparedStatement ptmt = con.prepareStatement(query);
            ptmt.setInt(1, message.getSender().getUser_id());
            ptmt.setInt(2, message.getReceiver().getUser_id());
            ptmt.setString(3, message.getSubject());
            ptmt.setString(4, message.getMessage_description());
            ptmt.setTimestamp(5, new java.sql.Timestamp(message.getDate().getTime()));
            ptmt.setTimestamp(6, new java.sql.Timestamp(message.getUpdatedAt().getTime()));
            ptmt.setInt(7, message.getMessage_Id());
            int row = ptmt.executeUpdate();
            logger.info("{} row(s) updated", row);
            return row > 0;
        } catch (SQLException ex) {
            logger.error("SQLException occurred: {}", ex.getMessage());
            System.out.println("SQLException occurred: " + ex.getMessage());
        }
        return false;
    }

    @Override
    public Messages getId(int id) {
        String query = "SELECT * FROM messages WHERE message_id = ?";
        Messages message = new Messages();
        try {
            PreparedStatement ptmt = con.prepareStatement(query);
            ptmt.setInt(1, id);
            ResultSet res = ptmt.executeQuery();
            if (res.next()) {
                message.setMessage_Id(res.getInt("message_id"));
                Users users1 = new Users();
                users1.setUser_id(res.getInt("sender_id"));
                message.setSender(users1);
                Users users2 = new Users();
                users2.setUser_id(res.getInt("receiver_id"));
                message.setReceiver(users2);
                message.setSubject(res.getString("subject"));
                message.setMessage_description(res.getString("message_description"));
                message.setDate(res.getTimestamp("date"));
                message.setCreatedAt(res.getTimestamp("created_at"));
                message.setUpdatedAt(res.getTimestamp("updated_at"));
            }
            logger.info("Retrieved message with ID: {}", id);
            return message;
        } catch (SQLException ex) {
            logger.error("SQLException occurred: {}", ex.getMessage());
            System.out.println("SQLException occurred: " + ex.getMessage());
        }
        return null;
    }

    @Override
    public List getAll() {
        String query = "SELECT * FROM messages ORDER BY created_at DESC";
        List<Messages> messagesList = new ArrayList<>();
        try {
            Statement statement = con.createStatement();
            ResultSet res = statement.executeQuery(query);
            while (res.next()) {
                Messages message = new Messages();
                message.setMessage_Id(res.getInt("message_id"));

                Users sender = new Users();
                sender.setUser_id(res.getInt("sender_id"));
                message.setSender(sender);

                Users receiver = new Users();
                receiver.setUser_id(res.getInt("receiver_id"));
                message.setReceiver(receiver);

                message.setSubject(res.getString("subject"));
                message.setMessage_description(res.getString("message_description"));
                message.setDate(res.getTimestamp("date"));
                message.setCreatedAt(res.getTimestamp("created_at"));
                message.setUpdatedAt(res.getTimestamp("updated_at"));
                messagesList.add(message);
            }
            logger.info("Retrieved all messages");
        } catch (SQLException ex) {
            logger.error("SQLException occurred: {}", ex.getMessage());
            System.out.println("SQLException occurred: " + ex.getMessage());
        }
        return messagesList;
    }
}