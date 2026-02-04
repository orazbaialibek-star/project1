package com.company.models;

public class Category {

    private int id;
    private String name;

    public Category(String name) {
        setName(name);
    }

    public Category(int id, String name) {
        this(name);
        setId(id);
    }

    public int getId() {return id;}
    public void setId(int id) {this.id = id;}
    public String getName() {return name;}
    public void setName(String name) {this.name = name;}

    @Override
    public String toString() {
        return "Category{" +
                "id=" + getId() +
                ", name='" + getName() + '\'' +
                '}';
    }
}
