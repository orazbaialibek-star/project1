package com.company;

import com.company.controllers.UserController;
import com.company.controllers.interfaces.IUserController;
import com.company.data.PostgresDB;
import com.company.data.interfaces.IDB;
import com.company.repositories.UserRepository;
import com.company.repositories.interfaces.IUserRepository;

public class Main {
    public static void main(String[] args){
        IDB db = new PostgresDB("jdbc:postgresql://localhost:5432", "postgres", "0000", "project1");
        IUserRepository repo = new UserRepository(db);
        IUserController controller = new UserController(repo);

        MyApp app = new MyApp(controller);

        app.launch();

        db.close();
    }
}
