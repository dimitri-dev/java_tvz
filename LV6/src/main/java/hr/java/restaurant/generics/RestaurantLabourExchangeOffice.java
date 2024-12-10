package hr.java.restaurant.generics;

import hr.java.restaurant.model.*;

import java.util.List;

public class RestaurantLabourExchangeOffice<T extends Restaurant> {
    private List<T> restaurants;

    public RestaurantLabourExchangeOffice(List<T> restaurants) {
        setRestaurants(restaurants);
    }

    public List<T> getRestaurants() {
        return restaurants;
    }

    public void setRestaurants(List<T> restaurants) {
        this.restaurants = restaurants;
    }
}
