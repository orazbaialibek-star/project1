package com.company.repositories;

import com.company.data.interfaces.IDB;
import com.company.models.User;
import com.company.repositories.interfaces.IUserRepository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserRepository implements IUserRepository {
    private final IDB db;  // Dependency Injection

    public UserRepository(IDB db) {
        this.db = db;
    }

    @Override
    public List<User> getAllUsers() {
        Connection con = null;

        try {
            con = db.getConnection();
            String sql = "SELECT id,name,surname,bank FROM users";
            Statement st = con.createStatement();

            ResultSet rs = st.executeQuery(sql);
            List<User> users = new ArrayList<>();
            while (rs.next()) {
                User user = new User(rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("surname"),
                        rs.getDouble("bank"));
                users.add(user);
            }

            return users;
        } catch (SQLException e) {
            System.out.println("sql error: " + e.getMessage());
        }

        return null;
    }
}
