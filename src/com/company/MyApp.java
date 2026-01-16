package com.company;

import com.company.controllers.interfaces.IUserController;

import java.util.InputMismatchException;
import java.util.Scanner;

public class MyApp {
    private final Scanner sc = new Scanner(System.in);

    private final IUserController controller;

    public MyApp(IUserController controller) {
        this.controller = controller;
    }

    private void mainMenu() {
        System.out.println();
        System.out.println("Welcome to Bank accounts manager");
        System.out.println("Select option:");
        System.out.println("1. Get all users");
        System.out.println("0. Exit");
        System.out.println();
        System.out.print("Enter option (0-1): ");
    }

    public void getAllUsersMenu() {
        String response = controller.getAllUsers();
        System.out.println(response);
    }

    public void launch(){
        while (true) {
            mainMenu();
            try {
                int option = sc.nextInt();

                switch (option){
                    case 1: getAllUsersMenu(); break;
                    default: return;
                }
            } catch (InputMismatchException e) {
                System.out.println("Input must be integer: " + e);
                sc.nextLine(); // to ignore incorrect input
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
