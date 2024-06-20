abstract class Creature {
  protected String name;
  protected int health;

  public Creature(String name, int health) {
      this.name = name;
      this.health = health;
  }

  public abstract void attack(Creature target);
  public abstract void takeDamage(int damage);
}