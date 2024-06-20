package org.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class Dragon extends Creature implements Interactable {
    private static final Logger logger = LoggerFactory.getLogger(Dragon.class);

    public Dragon(String name, int health) {
        super(name, health);
    }

    @Override
    public void attack(Creature target) {
        logger.info("The dragon breathes fire on {} for 20 damage!", target.name);
        target.takeDamage(20);
    }

    @Override
    public void takeDamage(int damage) {
        health -= damage;
        logger.warn("The dragon takes {} damage!", damage);
    }

    @Override
    public void interact() {
        logger.info("You encounter a dragon!");
    }
}
