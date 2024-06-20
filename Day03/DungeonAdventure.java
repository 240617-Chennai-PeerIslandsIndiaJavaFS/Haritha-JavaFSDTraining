import java.util.Scanner;

public class DungeonAdventure {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Player player = new Player("Hero", 100);

        Room currentRoom = new Room("Entrance");

        while (true) {
            System.out.println("You are in " + currentRoom.name + ".");
            System.out.println("Your health: " + player.health);

            if (currentRoom.enemy != null) {
                System.out.println("You see a " + currentRoom.enemy.name + "!");
                System.out.println("What do you do? (1) Attack, (2) Run");
                int choice = scanner.nextInt();
                if (choice == 1) {
                    player.attack(currentRoom.enemy);
                    if (currentRoom.enemy.health <= 0) {
                        System.out.println("You defeated the " + currentRoom.enemy.name + "!");
                        currentRoom.enemy = null;
                    } else {
                        currentRoom.enemy.attack(player);
                        if (player.health <= 0) {
                            System.out.println("You were defeated!");
                            break;
                        }
                    }
                } else {
                    System.out.println("You run away!");
                    currentRoom = new Room("Next Room");
                }
            } else if (currentRoom.item != null) {
                System.out.println("You see a " + currentRoom.item.name + "!");
                System.out.println("What do you do? (1) Take, (2) Leave");
                int choice = scanner.nextInt();
                if (choice == 1) {
                    currentRoom.item.use();
                    currentRoom.item = null;
                } else {
                    System.out.println("You leave the item behind.");
                }
            } else {
                System.out.println("The room is empty.");
                System.out.println("What do you do? (1) Move forward, (2) Exit");
                int choice = scanner.nextInt();
                if (choice == 1) {
                    currentRoom = new Room("Next Room");
                } else {
                    System.out.println("You exit the dungeon!");
                    break;
                }
            }
        }
    }
}

