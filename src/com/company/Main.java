package com.company;

import com.company.controllers.AuthorisationController;
import com.company.controllers.DepositController;
import com.company.controllers.TransactionController;
import com.company.controllers.UserController;
import com.company.controllers.interfaces.IAuthorisationController;
import com.company.controllers.interfaces.IDepositController;
import com.company.controllers.interfaces.ITransactionController;
import com.company.controllers.interfaces.IUserController;
import com.company.data.PostgresDB;
import com.company.data.interfaces.IDB;
import com.company.repositories.AuthorisaionRepository;
import com.company.repositories.DepositRepository;
import com.company.repositories.TransactionRepository;
import com.company.repositories.UserRepository;
import com.company.repositories.interfaces.IAuthorisationRepository;
import com.company.repositories.interfaces.IDepositRepository;
import com.company.repositories.interfaces.ITransactionRepository;
import com.company.repositories.interfaces.IUserRepository;

public class Main {
    public static void main(String[] args){
        PostgresDB db = PostgresDB.instance;
        db.setHost("jdbc:postgresql://localhost:5432");
        db.setUsername("postgres");
        db.setPassword("0000");
        db.setDbName("project1");
        IUserRepository repoU= new UserRepository(db);
        IUserController controllerU = new UserController(repoU);
        ITransactionRepository repoT = new TransactionRepository(db);
        ITransactionController controllerT = new TransactionController(repoT, repoU);
        IAuthorisationRepository repoAuth = new AuthorisaionRepository(db);
        IAuthorisationController controllerAuth = new AuthorisationController(repoAuth);
        IDepositRepository repoDep = new DepositRepository(db);
        IDepositController controllerDep = new DepositController(repoDep, repoU);

        MyApp app = new MyApp(controllerU, controllerT, controllerAuth, controllerDep);

        app.launch();

        db.close();
    }
}
