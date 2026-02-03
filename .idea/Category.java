package org.example;

@Entity
@Table(name = "categories")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    // Конструкторы
    public Category() {}
    public Category(String name) {
        this.name = name;
    }

    // Getters / Setters
    public Long getId() { return id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    // toString
    @Override
    public String toString() {
        return "Category{id=" + id + ", name='" + name + "'}";
    }
}
// Написал Магауин Мади