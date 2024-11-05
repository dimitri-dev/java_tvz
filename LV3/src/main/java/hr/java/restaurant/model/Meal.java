package hr.java.restaurant.model;

import java.math.BigDecimal;
import java.util.Map;

public class Meal extends Entity {
    private String name;
    private Category category;
    private Ingredient[] ingredients;
    private BigDecimal price;

    public static class MealConstants {
        public static final int VEGAN = 1;
        public static final int VEGETARIAN = 2;
        public static final int MEAT = 3;

        public static final Map<Integer, String> values = Map.of(
                VEGAN, "Vegan",
                VEGETARIAN, "Vegetarian",
                MEAT, "Meat"
        );
    }

    public Meal(String name, Category category, Ingredient[] ingredients, BigDecimal price) {
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

    public Ingredient[] getIngredients() {
        return ingredients;
    }

    public void setIngredients(Ingredient[] ingredients) {
        this.ingredients = ingredients;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
