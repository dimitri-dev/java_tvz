package hr.java.restaurant.model;

import java.time.LocalDateTime;
import java.util.List;

public class Order extends Entity {
    private Restaurant restaurant;
    private List<Meal> meals;
    private Deliverer deliverer;
    private LocalDateTime deliveryDateAndTime;

    public Order(Restaurant restaurant, List<Meal> meals, Deliverer deliverer, LocalDateTime deliveryDateAndTime) {
        setRestaurant(restaurant);
        setMeals(meals);
        setDeliverer(deliverer);
        setDeliveryDateAndTime(deliveryDateAndTime);
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

        System.out.println(("\t".repeat(tabCount)) + "Restaurant: ");
        getRestaurant().print(tabCount + 1, false);

        System.out.println(("\t".repeat(tabCount)) + "Deliverer: ");
        getDeliverer().print(tabCount + 1, false);

        System.out.println(("\t".repeat(tabCount)) + "Delivery Date and Time: " + getDeliveryDateAndTime().toString());

        System.out.println(("\t".repeat(tabCount)) + "Meals: ");
        for (Meal meal : meals) {
            meal.print(tabCount + 1, false);
        }
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public List<Meal> getMeals() {
        return meals;
    }

    public void setMeals(List<Meal> meals) {
        this.meals = meals;
    }

    public Deliverer getDeliverer() {
        return deliverer;
    }

    public void setDeliverer(Deliverer deliverer) {
        this.deliverer = deliverer;
    }

    public LocalDateTime getDeliveryDateAndTime() {
        return deliveryDateAndTime;
    }

    public void setDeliveryDateAndTime(LocalDateTime deliveryDateAndTime) {
        this.deliveryDateAndTime = deliveryDateAndTime;
    }
}
