package hr.java.production.main;

import hr.java.input.MockData;
import hr.java.input.ModelScannerChoice;
import hr.java.input.PersonModelCreator;
import hr.java.input.RestaurantModelCreator;
import hr.java.restaurant.model.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static hr.java.input.ModelScannerChoice.ChooseMeals;

public class Main {
    static List<Restaurant> restaurants;

    public static void main(String[] args) {
        askForInput();

        var mealsAndTheirRestaurants = restaurants.stream()
                .flatMap(restaurant -> restaurant.getMeals().stream()
                        .map(meal -> new AbstractMap.SimpleEntry<>(meal, restaurant)))
                .collect(Collectors.groupingBy(Map.Entry::getKey, Collectors.mapping(Map.Entry::getValue, Collectors.toList())));

        System.out.println("Choose a meal to see where you can order it from: ");
        var scanner = new Scanner(System.in);
        var meal = ModelScannerChoice.ChooseMeal(scanner, mealsAndTheirRestaurants.keySet());

        System.out.println("Restaurants at which you can order '" + meal.getName() + "' are: ");
        mealsAndTheirRestaurants.get(meal).forEach(restaurant -> System.out.println(restaurant.getName()));
    }

    public static void askForInput() {
        if (true) {
            restaurants = MockData.createRestaurants();
        }
        else {
            var scanner = new Scanner(System.in);

            var categories = new HashSet<Category>();
            categories.add(RestaurantModelCreator.CreateCategory(scanner, categories));
            categories.add(RestaurantModelCreator.CreateCategory(scanner, categories));
            categories.add(RestaurantModelCreator.CreateCategory(scanner, categories));

            var ingredients = new HashSet<Ingredient>();
            ingredients.add(RestaurantModelCreator.CreateIngredient(scanner, categories, ingredients));
            ingredients.add(RestaurantModelCreator.CreateIngredient(scanner, categories, ingredients));
            ingredients.add(RestaurantModelCreator.CreateIngredient(scanner, categories, ingredients));
            ingredients.add(RestaurantModelCreator.CreateIngredient(scanner, categories, ingredients));
            ingredients.add(RestaurantModelCreator.CreateIngredient(scanner, categories, ingredients));

            var meals = new HashSet<Meal>();
            meals.add(RestaurantModelCreator.CreateMeal(scanner, categories, ingredients, meals));
            meals.add(RestaurantModelCreator.CreateMeal(scanner, categories, ingredients, meals));
            meals.add(RestaurantModelCreator.CreateMeal(scanner, categories, ingredients, meals));

            var chefs = new HashSet<Person>();
            chefs.add(PersonModelCreator.CreateChef(scanner, chefs));
            chefs.add(PersonModelCreator.CreateChef(scanner, chefs));
            chefs.add(PersonModelCreator.CreateChef(scanner, chefs));

            var waiters = new HashSet<Person>();
            waiters.add(PersonModelCreator.CreateWaiter(scanner, waiters));
            waiters.add(PersonModelCreator.CreateWaiter(scanner, waiters));
            waiters.add(PersonModelCreator.CreateWaiter(scanner, waiters));

            var deliverers = new HashSet<Person>();
            deliverers.add(PersonModelCreator.CreateDeliverer(scanner, deliverers));
            deliverers.add(PersonModelCreator.CreateDeliverer(scanner, deliverers));
            deliverers.add(PersonModelCreator.CreateDeliverer(scanner, deliverers));

            var restaurants = new HashSet<Restaurant>();
            var restaurant = RestaurantModelCreator.CreateRestaurant(scanner, meals, chefs, waiters, deliverers, restaurants);
        }
    }
}
