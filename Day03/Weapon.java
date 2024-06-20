class Weapon extends Item {
  public Weapon(String name) {
      super(name);
  }

  @Override
  public void use() {
      System.out.println("You equip the weapon and deal 15 damage to the next enemy!");
  }
}