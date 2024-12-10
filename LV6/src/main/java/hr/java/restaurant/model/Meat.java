package hr.java.restaurant.model;

public sealed interface Meat permits MeatMeal {
    /* Meat type, e.g. beef, pork, chicken */
    public String getType();

    /* Meat cut, e.g. sirloin, tenderloin, drumstick */
    public String getCut();
}
