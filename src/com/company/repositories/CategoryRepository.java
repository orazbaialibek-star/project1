package com.company.repositories;

import com.company.data.interfaces.IDB;
import com.company.models.Category;
import com.company.repositories.interfaces.ICategoryRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CategoryRepository implements ICategoryRepository {

    private final IDB db;

    public CategoryRepository(IDB db) {
        this.db = db;
    }

    public boolean createCategory(Category category) {
        Connection con = null;

        try {
            con = db.getConnection();

            if (category.getName() == null || category.getName().isEmpty()) {
                return false;
            }

            String sql = "INSERT INTO categories(name) VALUES (?)";
            PreparedStatement st = con.prepareStatement(sql);

            st.setString(1, category.getName());
            st.execute();

            return true;
        } catch (Exception e) {
            System.out.println("sql error: " + e.getMessage());
        }

        return false;
    }

    public List<Category> getAllCategories() {
        Connection con = null;

        try {
            con = db.getConnection();
            String sql = "SELECT id, name FROM categories";
            Statement st = con.createStatement();

            ResultSet rs = st.executeQuery(sql);
            List<Category> categories = new ArrayList<>();

            while (rs.next()) {
                Category category = new Category(
                        rs.getInt("id"),
                        rs.getString("name")
                );
                categories.add(category);
            }

            return categories;
        } catch (Exception e) {
            System.out.println("sql error: " + e.getMessage());
        }

        return null;
    }

    public Category getCategoryById(int id) {
        Connection con = null;

        try {
            con = db.getConnection();
            String sql = "SELECT id, name FROM categories WHERE id=?";
            PreparedStatement st = con.prepareStatement(sql);

            st.setInt(1, id);
            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                return new Category(
                        rs.getInt("id"),
                        rs.getString("name")
                );
            }
        } catch (Exception e) {
            System.out.println("sql error: " + e.getMessage());
        }

        return null;
    }
}
