package com.company;

import com.company.controllers.interfaces.IAuthorisationController;
import com.company.controllers.interfaces.IUserController;
import com.company.controllers.interfaces.ITransactionController;

import java.util.InputMismatchException;
import java.util.Scanner;

public class MyApp {
    private final Scanner sc = new Scanner(System.in);

    private final IUserController userCont;

    private final ITransactionController transCont;

    private final IAuthorisationController authCont;

    public MyApp(IUserController userCont, ITransactionController transCont, IAuthorisationController authCont) {
        this.userCont = userCont;
        this.transCont = transCont;
        this.authCont = authCont;
    }

    public int currentUserId = 0;

    public void authorisation(){
        System.out.println("Please enter the login");
        String login = sc.next();
        System.out.println("Please enter the password");
        String password = sc.next();

        String responce1 = authCont.checkLogPas(login, password);
        int responce2 = authCont.checkRole(login);
        int responce3 = authCont.checkId(login);
        System.out.println(responce1);
        if (responce1 !=null){
            currentUserId = responce3;
            if (responce2 == 1){
                launchForAdmin();
            }
            if (responce2 == 2){
                launchForUser();
            }
            System.out.println(responce1 + currentUserId);
        } if (responce1 == null){
            System.out.println("An error occured");
        }
    }

    private void mainMenuAdmin() {
        System.out.println();
        System.out.println("Welcome to Bank accounts manager");
        System.out.println("Select option:");
        System.out.println("1. Get all users");
        System.out.println("2. Get user by id");
        System.out.println("3. Start transaction between users");
        System.out.println("4. Get all transactions");
        System.out.println("5. Create user");
        System.out.println("6. Delete user");
        System.out.println("0. Exit");
        System.out.println();
        System.out.print("Enter option (0-6): ");
    }

    private void mainMenuUser() {
        System.out.println();
        System.out.println("Welcome");
        System.out.println("Select option:");
        System.out.println("1. Start transaction between users");
        System.out.println("2. Get all my transactions");
        System.out.println("0. Exit");
        System.out.println();
        System.out.print("Enter option (0-3): ");
    }

    private void mainMenu(){
        System.out.println();
        System.out.println("Welcome to MyBank");
        System.out.println("1. Log-in");
        System.out.println("0. Exit");
        System.out.println();
        System.out.print("Enter option (0-1): ");
    }

    public void getAllUsersMenu() {
        String response = userCont.getAllUsers();
        System.out.println(response);
    }

    public void getUserMenu() {
        System.out.println("Please enter id");
        int id = sc.nextInt();

        String response = userCont.getUser(id);
        System.out.println(response);
    }

    public void startTransactionMenu(){
        int user1 = currentUserId;
        System.out.println("Please enter second users id");
        int user2 = sc.nextInt();
        System.out.println("Please enter the amount");
        int amount = sc.nextInt();

        String response = transCont.userTransaction_admin(user1, user2, amount);
        System.out.println(response);
    }

    public void getAllTransactionsMenu() {
        String response = transCont.getAllTransactions();
        System.out.println(response);
    }

    public void deleteUserMenu() {
        System.out.println("Please enter id");
        int id = sc.nextInt();

        String response = userCont.deleteUser(id);
        System.out.println(response);
    }

    public void createUserMenu() {
        System.out.println("Please enter name");
        String name = sc.next();
        System.out.println("Please enter surname");
        String surname = sc.next();
        System.out.println("Please enter the balance");
        int balance = sc.nextInt();
        System.out.println("Please enter the login");
        String login = sc.next();
        System.out.println("Please enter the password");
        String password = sc.next();
        System.out.println("Please enter the role(1 is for admin and 2 is for user");
        int role = sc.nextInt();

        String response = userCont.createUser(name, surname, balance, login, password, role);
        System.out.println(response);
    }

    public void launch(){
        while (true) {
            mainMenu();
            try {
                int option = sc.nextInt();

                switch (option){
                    case 1: authorisation(); break;
                    default: return;
                }
            } catch (InputMismatchException e) {
                System.out.println("Input must be integer: " + e);
                sc.nextLine();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public void launchForAdmin(){
        while (true) {
            mainMenuAdmin();
            try {
                int option = sc.nextInt();

                switch (option){
                    case 1: getAllUsersMenu(); break;
                    case 2: getUserMenu(); break;
                    case 3: startTransactionMenu(); break;
                    case 4: getAllTransactionsMenu(); break;
                    case 5: createUserMenu(); break;
                    case 6: deleteUserMenu(); break;
                    default: return;
                }
            } catch (InputMismatchException e) {
                System.out.println("Input must be integer: " + e);
                sc.nextLine();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public void launchForUser(){
        while (true) {
            mainMenuUser();
            try {
                int option = sc.nextInt();

                switch (option){
                    case 1: startTransactionMenu(); break;
                    case 2: getAllTransactionsMenu(); break;
                    default: return;
                }
            } catch (InputMismatchException e) {
                System.out.println("Input must be integer: " + e);
                sc.nextLine();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
