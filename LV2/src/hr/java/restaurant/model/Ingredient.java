package hr.java.restaurant.model;

import java.math.BigDecimal;

public class Ingredient extends Entity {
    private String name;
    private Category category;
    private BigDecimal kcal;
    private String preparationMethod;

    public Ingredient(String name, Category category, BigDecimal kcal, String preparationMethod) {
        setName(name);
        setCategory(category);
        setKcal(kcal);
        setPreparationMethod(preparationMethod);
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
        System.out.println(("\t".repeat(tabCount)) + "Kcal: " + getKcal());
        System.out.println(("\t".repeat(tabCount)) + "Preparation Method: " + getPreparationMethod());
        System.out.println(("\t".repeat(tabCount)) + "Category: ");
        getCategory().print(tabCount + 1, false);
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

    public BigDecimal getKcal() {
        return kcal;
    }

    public void setKcal(BigDecimal kcal) {
        this.kcal = kcal;
    }

    public String getPreparationMethod() {
        return preparationMethod;
    }

    public void setPreparationMethod(String preparationMethod) {
        this.preparationMethod = preparationMethod;
    }
}
