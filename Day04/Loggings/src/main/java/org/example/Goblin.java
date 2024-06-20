package org.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Goblin extends Creature implements Interactable {
    private static final Logger logger = LoggerFactory.getLogger(Goblin.class);

    public Goblin(String name, int health) {
        super(name, health);
        logger.info("Goblin created: {} with health: {}", name, health);
    }

    @Override
    public void attack(Creature target) {
        logger.info("The goblin attacks {} for 5 damage!", target.name);
        target.takeDamage(5);
    }

    @Override
    public void takeDamage(int damage) {
        health -= damage;
        logger.warn("The goblin takes {} damage!", damage);
    }

    @Override
    public void interact() {
        logger.info("You encounter a goblin!");
    }
}
