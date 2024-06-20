class Potion extends Item {
  public Potion(String name) {
      super(name);
  }

  @Override
  public void use() {
      System.out.println("You drink the potion and regain 10 health!");
  }
}