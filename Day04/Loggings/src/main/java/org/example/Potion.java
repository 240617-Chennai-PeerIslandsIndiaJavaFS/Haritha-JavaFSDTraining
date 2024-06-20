package org.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class Potion extends Item {
    private static final Logger logger = LoggerFactory.getLogger(Potion.class);

    public Potion(String name) {
        super(name);
        logger.info("Potion created: {}", name);
    }

    @Override
    public void use() {
        logger.info("You drink the potion {} and regain 10 health!", name);
        // Actual logic of the potion would go here
    }
}
