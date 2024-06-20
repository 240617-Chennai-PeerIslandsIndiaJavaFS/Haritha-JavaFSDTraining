package org.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

abstract class Item {
    protected String name;
    private static final Logger logger = LoggerFactory.getLogger(Item.class);

    public Item(String name) {
        this.name = name;
        logger.info("Item created: {}", name);
    }

    public abstract void use();

    protected void logUsage() {
        logger.info("Using item: {}", name);
    }
}
