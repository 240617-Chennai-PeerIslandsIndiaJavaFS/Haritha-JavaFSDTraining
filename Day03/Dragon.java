class Dragon extends Creature implements Interactable {
  public Dragon(String name, int health) {
      super(name, health);
  }

  @Override
  public void attack(Creature target) {
      System.out.println("The dragon breathes fire on you for 20 damage!");
      target.takeDamage(20);
  }

  @Override
  public void takeDamage(int damage) {
      health -= damage;
      System.out.println("The dragon takes " + damage + " damage!");
  }

  @Override
  public void interact() {
      System.out.println("You encounter a dragon!");
  }
}