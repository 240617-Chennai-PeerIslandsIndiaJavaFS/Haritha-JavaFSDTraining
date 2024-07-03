public class MultiLevel {
  public static void main(String[] args) {

    Dog d = new Dog();
    d.bark();
    d.color = "black";
    System.out.println(d.color);
    
  }
}

class Animal {
  String color;
  int legs;
  int age;

  void eat() {
    System.out.println("This animal eats food.");
 }
}
class Mammal extends Animal {
  void walk() {
      System.out.println("This mammal walks on land.");
  }
}

class Dog extends Mammal {
  void bark() {
      System.out.println("The dog barks.");
  }
}

