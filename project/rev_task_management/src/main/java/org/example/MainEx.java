//package org.example;
//
//import java.util.Scanner;
//
//public class MainEx {
//
//    public static void main(String[] args) {
//
//        Scanner sc = new Scanner(System.in);
//        System.out.println("Welcome!....");
//
//        while(true) {
//            System.out.println("Menu :");
//            System.out.println("1. User Registration");
//            System.out.println("2. Login");
//            System.out.println("3. Create project");
//            System.out.println("4. Assign task");
//            System.out.println("5. Update task status");
//            System.out.println("6. View reports");
//            System.out.println("7. Exit");
//            System.out.println("Enter your choice: ");
//
//            int choice = sc.nextInt();
//            sc.nextLine();
//
//            switch (choice) {
//                case 1:
//                    System.out.println("User Registration");
//                    EmcommerceApp.createUser();
//                    break;
//                case 2:
//                    System.out.println("Login");
//                    EmcommerceApp.loginUser();
//                    break;
//                case 3:
//                    System.out.println("Create project selected");
//                    break;
//                case 4:
//                    System.out.println("Assign task selected");
//                    break;
//                case 5:
//                    System.out.println("Update task status selected");
//                    break;
//                case 6:
//                    System.out.println("View reports selected");
//                    break;
//                case 7:
//                    System.out.println("Exit ...");
//                    return;
//                default:
//                    System.out.println("Invalid choice. Please try again.");
//                    break;
//            }
//        }
//    }
//}
