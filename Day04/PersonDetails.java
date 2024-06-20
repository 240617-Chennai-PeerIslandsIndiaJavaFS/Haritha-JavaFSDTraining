 class AgeException extends Exception {
  public AgeException(String str) {
      super(str);
  }
}

 class PasswordException extends Exception {
  public PasswordException(String str) {
      super(str);
  }
}

public class PersonDetails {
  String name;
  int age;
  String password;

  public PersonDetails(String name, int age, String password) throws AgeException, PasswordException {
      this.name = name;
      this.age = age;
      this.password = password;

      validateDetails();
  }

  private void validateDetails() throws AgeException, PasswordException {
      if (age < 0) {
          throw new AgeException("Age cannot be negative");
      }
      if (password.length() < 8) {
          throw new PasswordException("Password must be at least 8 characters long");
      }
  }

  public static void main(String[] args) {
      try {
          PersonDetails person = new PersonDetails("John", -5, "12345");
      } catch (AgeException ex) {
          System.out.println(ex.getMessage());
      } catch (PasswordException ex1) {
          System.out.println(ex1.getMessage());
      } finally {
          System.out.println("Finally block");
      }
  }
}
