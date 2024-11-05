package hr.java.restaurant.model;

import java.math.BigDecimal;

public final class VeganMeal extends Meal implements Vegan {
    private BigDecimal fiberContentPercentage;

    public VeganMeal(String name, Category category, Ingredient[] ingredients, BigDecimal price, BigDecimal fiberContentPercentage) {
        super(name, category, ingredients, price);
        setFiberContentPercentage(fiberContentPercentage);
    }

    @Override
    public void print(int tabCount, boolean newLine) throws Exception {
        super.print(tabCount, newLine);
        System.out.println(("\t".repeat(tabCount)) + "Fiber Content Percentage: " + getFiberContentPercentage() + "%");
    }

    @Override
    public Boolean hasHighFiberContent() {
        return getFiberContentPercentage().intValue() > 50;
    }

    public BigDecimal getFiberContentPercentage() {
        return fiberContentPercentage;
    }

    public void setFiberContentPercentage(BigDecimal fiberContentPercentage) {
        if ((fiberContentPercentage.intValue() < 0 || fiberContentPercentage.intValue() > 100)) {
            throw new IllegalArgumentException("Fiber content percentage must be between 0% and 100%.");
        }

        this.fiberContentPercentage = fiberContentPercentage;
    }
}
