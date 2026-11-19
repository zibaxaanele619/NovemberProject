/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package anelenovemberproject;

/**
 *
 * @author SOMEONE
 */
import java.util.ArrayList;

public class Users {

    private int userID;
    private String username;
    private String password;

    public Users(int userID, String username, String password) {

        this.userID = userID;
        this.username = username;
        this.password = password;
    }

    public int getUserID() {
        return userID;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
