package hr.java.restaurant.model;

public class CategoryBuilder {
    private String name;
    private String description;

    public CategoryBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public CategoryBuilder setDescription(String description) {
        this.description = description;
        return this;
    }

    public Category createCategory() {
        return new Category(name, description);
    }
}