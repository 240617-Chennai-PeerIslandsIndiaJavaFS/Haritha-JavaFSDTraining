package org.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class Room {
    private static final Logger logger = LoggerFactory.getLogger(Room.class);

    public String name;
    public Creature enemy;
    public Item item;

    public Room(String name) {
        this.name = name;
        this.enemy = getRandomEnemy();
        this.item = getRandomItem();
        logger.info("Room '{}' created with {} and {}.", name, enemy.getClass().getSimpleName(), item.getClass().getSimpleName());
    }

    private Creature getRandomEnemy() {
        int random = (int) (Math.random() * 2);
        if (random == 0) {
            Goblin goblin = new Goblin("Goblin", 20);
            logger.info("Random enemy selected: Goblin");
            return goblin;
        } else {
            Dragon dragon = new Dragon("Dragon", 50);
            logger.info("Random enemy selected: Dragon");
            return dragon;
        }
    }

    private Item getRandomItem() {
        int random = (int) (Math.random() * 2);
        if (random == 0) {
            Potion potion = new Potion("Healing Potion");
            logger.info("Random item selected: Healing Potion");
            return potion;
        } else {
            Weapon weapon = new Weapon("Sword");
            logger.info("Random item selected: Sword");
            return weapon;
        }
    }
}
