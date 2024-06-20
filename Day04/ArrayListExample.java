import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;

public class ArrayListExample {

  public static void main(String[] args) {
    
    Scanner sc = new Scanner(System.in);
    System.out.println("How many hobbies do you have?");
    int hobbies = sc.nextInt();
    sc.nextLine();  
    
    ArrayList<String> hobby = new ArrayList<String>();
    for(int i = 0; i < hobbies; i++) {
      System.out.println("Enter your hobby:");
      hobby.add(sc.nextLine());
    }

    System.out.println("Your hobbies are:");
    for(int i = 0; i < hobby.size(); i++) {
      System.out.println(hobby.get(i));
    }
  }
}
