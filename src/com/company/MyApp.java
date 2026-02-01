package com.company;

import com.company.controllers.interfaces.IAuthorisationController;
import com.company.controllers.interfaces.IDepositController;
import com.company.controllers.interfaces.IUserController;
import com.company.controllers.interfaces.ITransactionController;

import java.util.InputMismatchException;
import java.util.Scanner;

public class MyApp {
    private final Scanner sc = new Scanner(System.in);

    private final IUserController userCont;

    private final ITransactionController transCont;

    private final IAuthorisationController authCont;

    private final IDepositController depCont;

    public int currentUserId = 0;
    public String currentLogin = "NONDEFINED";

    public MyApp(IUserController userCont, ITransactionController transCont, IAuthorisationController authCont, IDepositController depCont) {
        this.userCont = userCont;
        this.transCont = transCont;
        this.authCont = authCont;
        this.depCont = depCont;
    }

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
            currentLogin = login;
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
        System.out.println("Welcome, " + currentLogin);
        System.out.println("Select option:");
        System.out.println("1. Get my info");
        System.out.println("2. Get all users");
        System.out.println("3. Get user by id");
        System.out.println("4. Start transaction between users");
        System.out.println("5. Get all transactions");
        System.out.println("6. Create user");
        System.out.println("7. Delete user");
        System.out.println("0. Exit");
        System.out.println();
        System.out.print("Enter option (0-7): ");
    }

    private void mainMenuUser() {
        System.out.println();
        System.out.println("Welcome, " + currentLogin);
        System.out.println("Select option:");
        System.out.println("1. Get my info");
        System.out.println("2. Start transaction");
        System.out.println("3. Get all my transactions");
        System.out.println("4. Get all my deposits");
        System.out.println("5. Create a deposit");
        System.out.println("0. Exit");
        System.out.println();
        System.out.print("Enter option (0-5): ");
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

    public void getMyInfo(){
        int id = currentUserId;
        String responce = userCont.getUser(id);
        System.out.println(responce);
    }

    public void startTransactionMenu(){
        int user1 = currentUserId;
        System.out.println("Please enter users id");
        int user2 = sc.nextInt();
        System.out.println("Please enter the amount");
        int amount = sc.nextInt();

        String response = transCont.userTransaction(user1, user2, amount);
        System.out.println(response);
    }

    public void getAllTransactions_adminMenu() {
        String response = transCont.getAllTransactions_admin();
        System.out.println(response);
    }

    public void getAllTransactions_userMenu() {
        String response = transCont.getAllTransactions_user(currentUserId);
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

    public void getAllMyDepositsMenu(){
        String responce = depCont.getAllMyDeposits(currentUserId);
        System.out.println(responce);
    }

    public void createDepositMenu(){
        System.out.println("Insert a type of deposit(1-2)");
        int type = sc.nextInt();
        System.out.println("Insert a balance");
        int balance = sc.nextInt();
        String responce = "NONDEFINED";
        if (type == 1){
            double percentage = 14.9;
            responce = depCont.createDeposit(currentUserId, percentage, balance);
        }
        if (type == 2){
            double percentage = 10.9;
            responce = depCont.createDeposit(currentUserId, percentage, balance);
        }
        System.out.println(responce);
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
                    case 1: getMyInfo(); break;
                    case 2: getAllUsersMenu(); break;
                    case 3: getUserMenu(); break;
                    case 4: startTransactionMenu(); break;
                    case 5: getAllTransactions_adminMenu(); break;
                    case 6: createUserMenu(); break;
                    case 7: deleteUserMenu(); break;
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
                    case 1: getMyInfo(); break;
                    case 2: startTransactionMenu(); break;
                    case 3: getAllTransactions_userMenu(); break;
                    case 4: getAllMyDepositsMenu(); break;
                    case 5: createDepositMenu(); break;
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
