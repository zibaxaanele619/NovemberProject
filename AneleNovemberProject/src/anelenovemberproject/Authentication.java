/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package anelenovemberproject;

/**
 *
 * @author SOMEONE
 */
public class Authentication {

    public static void SignUpUser() {

        System.out.println("\n========== SIGN UP ==========");

        System.out.print("Enter username: ");
        String username = AneleNovemberProject.scanner.nextLine();

        if (username.trim().isEmpty()) {
            System.out.println("Username cannot be empty.");
            return;
        }

        for (Users user : AneleNovemberProject.users) {

            if (user.getUsername().equalsIgnoreCase(username)) {

                System.out.println("Username already exists.");
                return;
            }
        }

        System.out.print("Enter password: ");
        String password = AneleNovemberProject.scanner.nextLine();

        if (password.isEmpty()) {

            System.out.println("Password cannot be empty.");
            return;
        }

        int userID = AneleNovemberProject.nextUserID++;

        Users newUser = new Users(userID, username, password);

        AneleNovemberProject.users.add(newUser);

        System.out.println("\nRegistration successful!");
        System.out.println("Your username: " + username);
        System.out.println("Your User ID: " + userID);
    }

    public static Users SignInUser() {

        System.out.println("\n========== SIGN IN ==========");

        System.out.print("Enter username: ");
        String username = AneleNovemberProject.scanner.nextLine();

        System.out.print("Enter password: ");
        String password = AneleNovemberProject.scanner.nextLine();

        for (Users user : AneleNovemberProject.users) {

            if (user.getUsername().equalsIgnoreCase(username)
                    && user.getPassword().equals(password)) {

                System.out.println("\nLogin successful!");
                System.out.println("Welcome " + user.getUsername());

                return user;
            }
        }

        System.out.println("\nIncorrect username or password.");

        return null;
    }
}
