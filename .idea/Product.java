package org.example;
// это клас должен быть помещен уже существующий файл
@ManyToOne(fetch = FetchType.LAZY)
@JoinColumn(name = "category_id")
private Category category;

// Getters / Setters
public Category getCategory() { return category; }
public void setCategory(Category category) { this.category = category; }

// toString (категорию для отладки)
@Override
public String toString() {
    return "Product{id=" + id + ", name='" + name + "', price=" + price +
            ", category=" + (category != null ? category.getName() : "null") + "}";
}
// Написал Магауин Мади