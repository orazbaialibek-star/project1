package com.company;

import com.company.controllers.TransactionController;
import com.company.controllers.UserController;
import com.company.controllers.interfaces.ITransactionController;
import com.company.controllers.interfaces.IUserController;
import com.company.data.PostgresDB;
import com.company.data.interfaces.IDB;
import com.company.repositories.TransactionRepository;
import com.company.repositories.UserRepository;
import com.company.repositories.interfaces.ITransactionRepository;
import com.company.repositories.interfaces.IUserRepository;

public class Main {
    public static void main(String[] args){
        IDB db = new PostgresDB("jdbc:postgresql://localhost:5432", "postgres", "0000", "project1");
        IUserRepository repo1 = new UserRepository(db);
        IUserController controller1 = new UserController(repo1);
        ITransactionRepository repo2 = new TransactionRepository(db);
        ITransactionController controller2 = new TransactionController(repo2);

        MyApp app = new MyApp(controller1, controller2);

        app.launch();

        db.close();
    }
}
