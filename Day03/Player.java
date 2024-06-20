class Player extends Creature implements Interactable {
  public Player(String name, int health) {
      super(name, health);
  }

  @Override
  public void attack(Creature target) {
      System.out.println("You attack " + target.name + " for 10 damage!");
      target.takeDamage(10);
  }

  @Override
  public void takeDamage(int damage) {
      health -= damage;
      System.out.println("You take " + damage + " damage!");
  }

  @Override
  public void interact() {
      System.out.println("You explore the room...");
  }
}