package org.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

abstract class Creature {
    protected String name;
    protected int health;

    // Initialize logger
    private static final Logger logger = LoggerFactory.getLogger(Creature.class);

    public Creature(String name, int health) {
        this.name = name;
        this.health = health;
        logger.info("Creature created: {} with health: {}", name, health);
    }

    public abstract void attack(Creature target);
    public abstract void takeDamage(int damage);

    protected void logAttack(String attackerName, String targetName) {
        logger.info("{} attacks {}", attackerName, targetName);
    }

    protected void logDamageTaken(String creatureName, int damage) {
        logger.warn("{} takes {} damage", creatureName, damage);
    }

    protected void logHealth(String creatureName, int health) {
        logger.info("{}'s current health: {}", creatureName, health);
    }
}
