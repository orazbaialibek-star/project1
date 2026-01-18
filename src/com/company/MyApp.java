package com.company;

import com.company.controllers.interfaces.IUserController;
import com.company.controllers.interfaces.ITransactionController;

import java.util.InputMismatchException;
import java.util.Scanner;

public class MyApp {
    private final Scanner sc = new Scanner(System.in);

    private final IUserController userCont;

    private final ITransactionController transCont;

    public MyApp(IUserController userCont, ITransactionController transCont) {
        this.userCont = userCont;
        this.transCont = transCont;
    }

    private void mainMenu() {
        System.out.println();
        System.out.println("Welcome to Bank accounts manager");
        System.out.println("Select option:");
        System.out.println("1. Get all users");
        System.out.println("2. Get user by id");
        System.out.println("3. Start transaction");
        System.out.println("4. Get all transactions");
        System.out.println("5. Create user");
        System.out.println("6. Delete user");
        System.out.println("0. Exit");
        System.out.println();
        System.out.print("Enter option (0-5): ");
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

    public void getTransactionMenu(){
        System.out.println("Please enter first users id");
        int user1 = sc.nextInt();
        System.out.println("Please enter second users id");
        int user2 = sc.nextInt();
        System.out.println("Please enter the amount");
        int amount = sc.nextInt();

        String response = transCont.userTransaction(user1, user2, amount);
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
        System.out.println("Please enter gender (male/female)");
        int balance = sc.nextInt();

        String response = userCont.createUser(name, surname, balance);
        System.out.println(response);
    }

    public void launch(){
        while (true) {
            mainMenu();
            try {
                int option = sc.nextInt();

                switch (option){
                    case 1: getAllUsersMenu(); break;
                    case 2: getUserMenu(); break;
                    case 3: getTransactionMenu(); break;
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

    public void deleteUserMenu() {
        System.out.println("Please enter id");
        int id = sc.nextInt();
        String response = userCont.deleteUser(id);
        System.out.println(response);
    }
}
