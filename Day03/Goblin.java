public class Goblin extends Creature implements Interactable {
  public Goblin(String name, int health) {
      super(name, health);
  }

  @Override
  public void attack(Creature target) {
      System.out.println("The goblin attacks you for 5 damage!");
      target.takeDamage(5);
  }

  @Override
  public void takeDamage(int damage) {
      health -= damage;
      System.out.println("The goblin takes " + damage + " damage!");
  }

  @Override
  public void interact() {
      System.out.println("You encounter a goblin!");
  }
}