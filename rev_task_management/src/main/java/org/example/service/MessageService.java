package org.example.service;

import org.example.dao.MessagesDAOImp;
import org.example.dao.UserDAOImplementation;
import org.example.model.Messages;
import org.example.model.Users;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Timestamp;
import java.util.Scanner;

public class MessageService {
    private static final Logger logger = LoggerFactory.getLogger(MessageService.class);
    private static Scanner sc = new Scanner(System.in);
    private static MessagesDAOImp messageDAO = new MessagesDAOImp();
    private static UserDAOImplementation userDAO = new UserDAOImplementation();

    public static void addMessage() {
        logger.info("Starting message creation process.");

        System.out.print("Enter Sender ID: ");
        int senderId = sc.nextInt();
        sc.nextLine();

        System.out.print("Enter Receiver ID: ");
        int receiverId = sc.nextInt();
        sc.nextLine();

        Users sender = userDAO.getUserById(senderId);
        Users receiver = userDAO.getUserById(receiverId);

        if (sender == null) {
            logger.warn("Invalid sender ID entered.");
            System.out.println("Invalid sender ID.");
            return;
        }

        if (receiver == null) {
            logger.warn("Invalid receiver ID entered.");
            System.out.println("Invalid receiver ID.");
            return;
        }

        Messages message = new Messages();
        message.setSender(sender);
        message.setReceiver(receiver);

        System.out.print("Enter Subject: ");
        message.setSubject(sc.nextLine());

        System.out.print("Enter Message Description: ");
        message.setMessage_description(sc.nextLine());

        message.setDate(new Timestamp(System.currentTimeMillis()));
        message.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        message.setUpdatedAt(new Timestamp(System.currentTimeMillis()));

        Messages addedMessage = messageDAO.create(message);
        if (addedMessage != null) {
            logger.info("Message added successfully. Message ID: {}", addedMessage.getMessage_Id());
            System.out.println("Message added successfully.");
        } else {
            logger.error("Failed to add message.");
            System.out.println("Failed to add message.");
        }
    }

    public static void updateMessage() {
        logger.info("Starting message update process.");

        System.out.print("Enter Message ID to update: ");
        int messageId = sc.nextInt();
        sc.nextLine();

        Messages message = messageDAO.getId(messageId);
        if (message == null) {
            logger.warn("Message not found for update. Message ID: {}", messageId);
            System.out.println("Message not found.");
            return;
        }

        System.out.print("Enter new Subject: ");
        message.setSubject(sc.nextLine());

        System.out.print("Enter new Message Description: ");
        message.setMessage_description(sc.nextLine());

        message.setUpdatedAt(new Timestamp(System.currentTimeMillis()));

        boolean success = messageDAO.update(message);
        if (success) {
            logger.info("Message updated successfully. Message ID: {}", messageId);
            System.out.println("Message updated successfully.");
        } else {
            logger.error("Failed to update message. Message ID: {}", messageId);
            System.out.println("Failed to update message.");
        }
    }

    public static void deleteMessage() {
        logger.info("Starting message deletion process.");

        System.out.print("Enter Message ID to delete: ");
        int messageId = sc.nextInt();
        sc.nextLine();

        boolean success = messageDAO.delete(messageId);
        if (success) {
            logger.info("Message deleted successfully. Message ID: {}", messageId);
            System.out.println("Message deleted successfully.");
        } else {
            logger.error("Failed to delete message. Message ID: {}", messageId);
            System.out.println("Failed to delete message.");
        }
    }

    public static void getMessageById() {
        logger.info("Starting message retrieval process by ID.");

        System.out.print("Enter Message ID to retrieve: ");
        int messageId = sc.nextInt();
        sc.nextLine();

        Messages message = messageDAO.getId(messageId);
        if (message != null) {
            logger.info("Message details retrieved successfully. Message ID: {}", messageId);
            System.out.println("Message Details:");
            System.out.println("ID: " + message.getMessage_Id());
            System.out.println("Sender ID: " + message.getSender().getUser_id());
            System.out.println("Receiver ID: " + message.getReceiver().getUser_id());
            System.out.println("Subject: " + message.getSubject());
            System.out.println("Description: " + message.getMessage_description());
            System.out.println("Date: " + message.getDate());
            System.out.println("Created At: " + message.getCreatedAt());
            System.out.println("Updated At: " + message.getUpdatedAt());
        } else {
            logger.warn("Message not found. Message ID: {}", messageId);
            System.out.println("Message not found.");
        }
    }
}
