package hr.java.restaurant.model;

public sealed interface Vegetarian permits VegetarianMeal {
    public default Boolean hasDairy() {
        return true;
    }

    public default Boolean hasAlternativeProteinSource() {
        return true;
    }
}
