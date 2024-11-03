package hr.java.restaurant.model;

import java.math.BigDecimal;

public final class MeatMeal extends Meal implements Meat {
    private String type;
    private String cut;

    public MeatMeal(String name, Category category, Ingredient[] ingredients, BigDecimal price, String type, String cut) {
        super(name, category, ingredients, price);
        setType(type);
        setCut(cut);
    }

    @Override
    public void print(int tabCount, boolean newLine) throws Exception {
        super.print(tabCount, newLine);
        System.out.println(("\t".repeat(tabCount)) + "Type: " + getType());
        System.out.println(("\t".repeat(tabCount)) + "Cut: " + getCut());
    }

    @Override
    public String getType() {
        return type;
    }

    @Override
    public String getCut() {
        return cut;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setCut(String cut) {
        this.cut = cut;
    }
}
