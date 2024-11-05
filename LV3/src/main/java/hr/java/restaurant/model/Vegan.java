package hr.java.restaurant.model;

public sealed interface Vegan permits VeganMeal {
    public default Boolean hasHighFiberContent() {
        return true;
    }

    public default Boolean hasPlantBasedIngredients() {
        return true;
    }
}
