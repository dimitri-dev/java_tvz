package hr.java.restaurant.model;

import java.math.BigDecimal;
import java.util.Set;

public final class VegetarianMeal extends Meal implements Vegetarian {
    private Boolean containsDairy;

    public VegetarianMeal(String name, Category category, Set<Ingredient> ingredients, BigDecimal price, Boolean containsDairy) {
        super(name, category, ingredients, price);
        setContainsDairy(containsDairy);
    }

    @Override
    public void print(int tabCount, boolean newLine) throws Exception {
        super.print(tabCount, newLine);
        System.out.println(("\t".repeat(tabCount)) + "Contains Dairy: " + (getContainsDairy() ? "Yes" : "No"));
        System.out.println(("\t".repeat(tabCount)) + "Alternative Protein Source: " + (hasAlternativeProteinSource() ? "Yes" : "No"));
    }

    @Override
    public Boolean hasDairy() {
        return containsDairy;
    }

    @Override
    public Boolean hasAlternativeProteinSource() {
        return false;
    }

    public Boolean getContainsDairy() {
        return containsDairy;
    }

    public void setContainsDairy(Boolean containsDairy) {
        this.containsDairy = containsDairy;
    }
}
