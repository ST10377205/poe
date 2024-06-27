/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.mavenproject2;

import javax.swing.JOptionPane;

public class Mavenproject2 {
    public static void main(String[] args) {
        // Registration
        String username = JOptionPane.showInputDialog(null, "Enter username:");
        while (!Account.isUsernameValid(username)) {
            JOptionPane.showMessageDialog(null, "Username is not correctly formatted, please ensure that your username contains an underscore and is no more than 5 characters in length", "Invalid Username", JOptionPane.ERROR_MESSAGE);
            username = JOptionPane.showInputDialog(null, "Enter username:");
        }

        String password = JOptionPane.showInputDialog(null, "Enter password:");
        while (!Account.isPasswordValid(password)) {
            JOptionPane.showMessageDialog(null, "Password is not correctly formatted, please ensure that the password contains at least 8 characters, a capital letter, a number, and a special character.", "Invalid Password", JOptionPane.ERROR_MESSAGE);
            password = JOptionPane.showInputDialog(null, "Enter password:");
        }

        String firstName = JOptionPane.showInputDialog(null, "Enter first name:");
        String lastName = JOptionPane.showInputDialog(null, "Enter last name:");

        // Create account
        Account account = new Account(username, password, firstName, lastName);

        // Create login instance
        Login login = new Login(account);

        // Login process
        boolean isLoggedIn = false;
        while (!isLoggedIn) {
            String enteredUsername = JOptionPane.showInputDialog(null, "Enter your credentials to login:\nEnter username:");
            String enteredPassword = JOptionPane.showInputDialog(null, "Enter password:");
            isLoggedIn = login.loginUser(enteredUsername, enteredPassword);
            JOptionPane.showMessageDialog(null, login.returnLoginStatus(isLoggedIn), "Login Status", isLoggedIn ? JOptionPane.INFORMATION_MESSAGE : JOptionPane.ERROR_MESSAGE);
        }

        // Display welcome message
        JOptionPane.showMessageDialog(null, "Welcome to EasyKanban", "Welcome", JOptionPane.INFORMATION_MESSAGE);

        // Main menu
        boolean running = true;
        TaskManager taskManager = new TaskManager();
        while (running) {
            String menu = "Select an option:\n1) Add tasks\n2) Manage tasks\n3) Show report\n4) Quit";
            String choice = JOptionPane.showInputDialog(menu);
            switch (choice) {
                case "1" -> {
                    int numTasks = Integer.parseInt(JOptionPane.showInputDialog("Enter the number of tasks:"));
                    taskManager.addTasks(numTasks);
                }
                case "2" -> taskManager.manageTasks();
                case "3" -> taskManager.showReport();
                case "4" -> running = false;
                default -> JOptionPane.showMessageDialog(null, "Invalid option. Please enter 1, 2, 3, or 4.", "Invalid Option", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
