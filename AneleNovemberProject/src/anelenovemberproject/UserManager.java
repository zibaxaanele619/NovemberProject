/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package anelenovemberproject;

/**
 *
 * @author SOMEONE
 */
public class UserManager {

    public static void DisplayAllUsers() {

        System.out.println("\n========== ALL USERS ==========");

        if (AneleNovemberProject.users.isEmpty()) {

            System.out.println("No registered users.");
            return;
        }

        for (Users user : AneleNovemberProject.users) {

            System.out.println("\n--------------------------");

            System.out.println("User ID: " + user.getUserID());

            System.out.println("Username: " + user.getUsername());
        }
    }

    public static Users GetUserByID(int userID) {

        for (Users user : AneleNovemberProject.users) {

            if (user.getUserID() == userID) {

                return user;
            }
        }

        return null;
    }

    public static void FindUser() {

        System.out.println("\n========== FIND USER ==========");

        int userID = AneleNovemberProject.getNumber(
                "Enter User ID: ");

        Users user = GetUserByID(userID);

        if (user == null) {

            System.out.println("User not found.");

            return;
        }

        System.out.println("\nUser found!");

        System.out.println("User ID: " + user.getUserID());

        System.out.println("Username: " + user.getUsername());
    }
}
