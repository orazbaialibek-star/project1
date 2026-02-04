package com.company;

import com.company.controllers.interfaces.IAuthorisationController;
import com.company.controllers.interfaces.IDepositController;
import com.company.controllers.interfaces.IUserController;
import com.company.controllers.interfaces.ITransactionController;
import com.company.controllers.interfaces.ICategoryController;

import java.util.InputMismatchException;
import java.util.Scanner;

public class MyApp {
    private final Scanner sc = new Scanner(System.in);

    private final IUserController userCont;

    private final ITransactionController transCont;

    private final IAuthorisationController authCont;

    private final IDepositController depCont;

    private final ICategoryController catCont;


    public int currentUserId = 0;
    public String currentLogin = "NONDEFINED";
    public int currentUserRole = 0;

    public MyApp(IUserController userCont, ITransactionController transCont, IAuthorisationController authCont, IDepositController depCont, ICategoryController catCont) {
        this.userCont = userCont;
        this.transCont = transCont;
        this.authCont = authCont;
        this.depCont = depCont;
        this.catCont = catCont;
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
                currentUserRole = 1;
                launchForAdmin();
            }
            if (responce2 == 2){
                currentUserRole = 2;
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
        System.out.println("2. Go to transactions");
        System.out.println("3. Go to deposits");
        System.out.println("4. Open admin panel");
        System.out.println("0. Exit");
        System.out.println();
        System.out.print("Enter option (0-4): ");
    }

    private void mainMenuUser() {
        System.out.println();
        System.out.println("Welcome, " + currentLogin);
        System.out.println("Select option:");
        System.out.println("1. Get my info");
        System.out.println("2. Go to transactions");
        System.out.println("3. Go to deposits");
        System.out.println("0. Exit");
        System.out.println();
        System.out.print("Enter option (0-5): ");
    }

    private void adminpanelMenu() {
        System.out.println();
        System.out.println("Select option:");
        System.out.println("1. Get all users");
        System.out.println("2. Get user by id");
        System.out.println("3. Create user");
        System.out.println("4. Delete user");
        System.out.println("5. Get all users with transactions");
        System.out.println("6. Get all transactions");
        System.out.println("7. Get all deposits");
        System.out.println("8. View all categories");
        System.out.println("0. Go back");
        System.out.println();
        System.out.print("Enter option (0-7): ");
    }

    private void depositsMenu() {
        System.out.println();
        System.out.println("Select option:");
        System.out.println("1. Get my deposits");
        System.out.println("2. Create a new deposit");
        System.out.println("3. Replenish deposit");
        System.out.println("4. Withdraw from deposit");
        System.out.println("0. Go back");
        System.out.println();
        System.out.print("Enter option (0-4): ");
    }

    private void transactionsMenu() {
        System.out.println();
        System.out.println("Select option:");
        System.out.println("1. Get all my transactions");
        System.out.println("2. Start a new transaction");
        System.out.println("0. Go back");
        System.out.println();
        System.out.print("Enter option (0-2): ");
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

    public void getUsersWithTransactionsMenu(){
        String responce = userCont.getUsersWithTransactions();
        System.out.println(responce);
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

    public void getAllDepositsMenu() {
        String response = depCont.getAllDeposits();
        System.out.println(response);
    }

    public void createDepositMenu(){
        System.out.println("Insert a type of deposit(1 for 14.9% - 2 for 10.9%)");
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

    public void replenishDepositMenu(){
        System.out.println("Insert deposit id");
        int id = sc.nextInt();
        System.out.println("Insert an amount");
        int amount = sc.nextInt();
        String responce = depCont.replenishDeposit(id, currentUserId, amount);
        System.out.println(responce);
    }

    public void withdrawDepositMenu(){
        System.out.println("Insert deposit id");
        int id = sc.nextInt();
        System.out.println("Insert an amount");
        int amount = sc.nextInt();
        String responce = depCont.withdrawDeposit(id, currentUserId, amount);
        System.out.println(responce);
    }
    public void getAllCategoriesMenu() {
        System.out.println("=== Categories ===");
        try {
            catCont.getAllCategories().forEach(cat ->
                    System.out.println("ID: " + cat.getId() + ", Name: " + cat.getName())
            );
        } catch (Exception e) {
            System.out.println("Error fetching categories: " + e.getMessage());
        }
    }


    public void adminpanel(){
        while (true) {
            adminpanelMenu();
            try {
                int option = sc.nextInt();

                switch (option){
                    case 1: getAllUsersMenu(); break;
                    case 2: getUserMenu(); break;
                    case 3: createUserMenu(); break;
                    case 4: deleteUserMenu(); break;
                    case 5: getUsersWithTransactionsMenu(); break;
                    case 6: getAllTransactions_adminMenu(); break;
                    case 7: getAllDepositsMenu(); break;
                    case 8: getAllCategoriesMenu(); break;
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

    public void deposits(){
        while (true) {
            depositsMenu();
            try {
                int option = sc.nextInt();

                switch (option){
                    case 1: getAllMyDepositsMenu(); break;
                    case 2: createDepositMenu(); break;
                    case 3: replenishDepositMenu(); break;
                    case 4: withdrawDepositMenu(); break;
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

    public void transactions(){
        while (true) {
            transactionsMenu();
            try {
                int option = sc.nextInt();

                switch (option){
                    case 1: getAllTransactions_userMenu(); break;
                    case 2: startTransactionMenu(); break;
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
                    case 2: transactions(); break;
                    case 3: deposits(); break;
                    case 4: adminpanel(); break;
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
                    case 2: transactions(); break;
                    case 3: deposits(); break;
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
