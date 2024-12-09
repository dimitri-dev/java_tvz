package hr.java.restaurant.model;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

public class Meal extends Entity {
    private String name;
    private Category category;
    private Set<Ingredient> ingredients;
    private BigDecimal price;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Meal meal = (Meal) o;
        return Objects.equals(name, meal.name) && Objects.equals(category, meal.category) && Objects.equals(ingredients, meal.ingredients) && Objects.equals(price, meal.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, category, ingredients, price);
    }

    public Meal(String name, Category category, Set<Ingredient> ingredients, BigDecimal price) {
        setName(name);
        setCategory(category);
        setIngredients(ingredients);
        setPrice(price);
    }

    public void print() throws Exception {
        print(0);
    }

    public void print(int tabCount) throws Exception {
        print(tabCount, true);
    }

    public void print(int tabCount, boolean newLine) throws Exception {
        if (newLine) {
            System.out.println();
        }

        if (tabCount < 0) {
            throw new Exception("Number of tabs cannot be negative. Something went wrong.");
        }

        System.out.println(("\t".repeat(tabCount)) + "Name: " + getName());
        System.out.println(("\t".repeat(tabCount)) + "Price: " + getPrice());

        System.out.println(("\t".repeat(tabCount)) + "Category: ");
        getCategory().print(tabCount + 1, false);

        System.out.println(("\t".repeat(tabCount)) + "Ingredients: ");
        for (Ingredient ingredient : getIngredients()) {
            ingredient.print(tabCount + 1, false);
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Set<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(Set<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
