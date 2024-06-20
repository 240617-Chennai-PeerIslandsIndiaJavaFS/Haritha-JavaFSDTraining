package org.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Scanner;

public class DungeonAdventure {
    private static final Logger logger = LoggerFactory.getLogger(DungeonAdventure.class);
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        logger.info("Starting Dungeon Adventure...");

        Player player = new Player("Hero", 100);

        Room currentRoom = new Room("Entrance");

        while (true) {
            logger.info("Current Room: {}", currentRoom.name);
            logger.info("Player's Health: {}", player.health);

            if (currentRoom.enemy != null) {
                logger.info("Encountered Enemy: {}", currentRoom.enemy.name);
                logger.info("Options: (1) Attack, (2) Run");
                int choice = scanner.nextInt();
                if (choice == 1) {
                    player.attack(currentRoom.enemy);
                    if (currentRoom.enemy.health <= 0) {
                        logger.info("Defeated the {}", currentRoom.enemy.name);
                        currentRoom.enemy = null;
                    } else {
                        currentRoom.enemy.attack(player);
                        if (player.health <= 0) {
                            logger.info("Player was defeated!");
                            break;
                        }
                    }
                } else {
                    logger.info("Player runs away to the next room.");
                    currentRoom = new Room("Next Room");
                }
            } else if (currentRoom.item != null) {
                logger.info("Found Item: {}", currentRoom.item.name);
                logger.info("Options: (1) Take, (2) Leave");
                int choice = scanner.nextInt();
                if (choice == 1) {
                    currentRoom.item.use();
                    currentRoom.item = null;
                } else {
                    logger.info("Player leaves the item behind.");
                }
            } else {
                logger.info("The room is empty.");
                logger.info("Options: (1) Move forward, (2) Exit");
                int choice = scanner.nextInt();
                if (choice == 1) {
                    currentRoom = new Room("Next Room");
                } else {
                    logger.info("Player exits the dungeon!");
                    break;
                }
            }
        }

        logger.info("Game over.");
    }
}
