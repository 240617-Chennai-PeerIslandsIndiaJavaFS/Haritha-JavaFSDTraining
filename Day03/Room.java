class Room {
  public String name;
  public Creature enemy;
  public Item item;

  public Room(String name) {
      this.name = name;
      this.enemy = getRandomEnemy();
      this.item = getRandomItem();
  }

  private Creature getRandomEnemy() {
      int random = (int) (Math.random() * 2);
      if (random == 0) {
          return new Goblin("Goblin", 20);
      } else {
          return new Dragon("Dragon", 50);
      }
  }

  private Item getRandomItem() {
      int random = (int) (Math.random() * 2);
      if (random == 0) {
          return new Potion("Healing Potion");
      } else {
          return new Weapon("Sword");
      }
  }
}