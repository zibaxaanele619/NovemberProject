/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package anelenovemberproject;

/**
 *
 * @author SOMEONE
 */
import java.util.Scanner;
import java.util.ArrayList;

public class AneleNovemberProject {

    static Scanner scanner = new Scanner(System.in);

    static ArrayList<Users> users = new ArrayList<>();
    static ArrayList<Messages> messages = new ArrayList<>();

    static int nextUserID = 100;
    static int nextMessageID = 1;

    static Users currentUser = null;

    public static void main(String[] args) {

        boolean running = true;

        System.out.println("================================");
        System.out.println("       WELCOME TO MESSENGER");
        System.out.println("================================");

        while (running) {

            if (currentUser == null) {

                System.out.println("\n1. Sign Up");
                System.out.println("2. Sign In");
                System.out.println("3. Exit");

                int choice = getNumber("Choose an option: ");

                switch (choice) {

                    case 1:
                        Authentication.SignUpUser();
                        break;

                    case 2:
                        currentUser = Authentication.SignInUser();
                        break;

                    case 3:
                        running = false;
                        System.out.println("Goodbye!");
                        break;

                    default:
                        System.out.println("Invalid option.");
                }

            } else {

                NavigateMenu();
            }
        }

        scanner.close();
    }

    public static void NavigateMenu() {

        System.out.println("\n================================");
        System.out.println("           MAIN MENU");
        System.out.println("================================");

        System.out.println("Welcome, " + currentUser.getUsername());
        System.out.println("Your User ID: " + currentUser.getUserID());

        System.out.println("\n1. Send Message");
        System.out.println("2. Read Received Messages");
        System.out.println("3. Read Sent Messages");
        System.out.println("4. Display All Users");
        System.out.println("5. Find User By ID");

        System.out.println("\n========== MESSAGE TOOLS ==========");

        System.out.println("6. Find Longest Message (Characters)");
        System.out.println("7. Find Longest Message (Words)");
        System.out.println("8. Find Message By ID");
        System.out.println("9. Find Messages Sent To A User");
        System.out.println("10. Generate Message Report");

        System.out.println("\n11. Logout");

        int choice = getNumber("Choose an option: ");

        switch (choice) {

            case 1:
                MessageManager.SendMessage();
                break;

            case 2:
                MessageManager.ReadMyMessages();
                break;

            case 3:
                MessageManager.ReadSentMessages();
                break;

            case 4:
                UserManager.DisplayAllUsers();
                break;

            case 5:
                UserManager.FindUser();
                break;

            case 6:
                MessageManager.FindLongestMessageByCharacters();
                break;

            case 7:
                MessageManager.FindLongestMessageByWords();
                break;

            case 8:
                MessageManager.FindMessageByID();
                break;

            case 9:
                MessageManager.FindMessagesByReceiver();
                break;

            case 10:
                MessageManager.GenerateMessageReport();
                break;

            case 11:
                System.out.println("You have been logged out.");
                currentUser = null;
                break;

            default:
                System.out.println("Invalid option.");
        }
    }

    public static int getNumber(String message) {

        while (true) {

            System.out.print(message);

            try {

                int number = Integer.parseInt(scanner.nextLine());
                return number;

            } catch (NumberFormatException e) {

                System.out.println("Please enter a valid number.");
            }
        }
    }
}
