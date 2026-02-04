package org.example;

@ManyToOne(fetch = FetchType.LAZY)
@JoinColumn(name = "category_id")
private Category category;

// Getters / Setters
public Category getCategory() { return category; }
public void setCategory(Category category) { this.category = category; }

// toString 
@Override
public String toString() {
    return "Product{id=" + id + ", name='" + name + "', price=" + price +
            ", category=" + (category != null ? category.getName() : "null") + "}";
}


