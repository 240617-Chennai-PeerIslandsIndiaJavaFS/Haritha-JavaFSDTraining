package org.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Weapon extends Item {
    private static final Logger logger = LoggerFactory.getLogger(Weapon.class);

    public Weapon(String name) {
        super(name);
        logger.info("Weapon created: {}", name);
    }

    @Override
    public void use() {
        logger.info("You equip the weapon {} and deal 15 damage to the next enemy!", name);
        // Actual logic of the weapon usage would go here
    }
}
