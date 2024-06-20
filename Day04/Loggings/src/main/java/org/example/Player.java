package org.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class Player extends Creature implements Interactable {
    private static final Logger logger = LoggerFactory.getLogger(Player.class);

    public Player(String name, int health) {
        super(name, health);
        logger.info("Player created: {} with health: {}", name, health);
    }

    @Override
    public void attack(Creature target) {
        logger.info("You attack {} for 10 damage!", target.name);
        target.takeDamage(10);
    }

    @Override
    public void takeDamage(int damage) {
        health -= damage;
        logger.warn("You take {} damage!", damage);
    }

    @Override
    public void interact() {
        logger.info("You explore the room...");
    }
}
