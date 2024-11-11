package hr.java.production.main;

import hr.java.input.MockData;
import hr.java.input.PersonModelCreator;
import hr.java.input.RestaurantModelCreator;
import hr.java.restaurant.exception.DeliveryTrackNotSupportedException;
import hr.java.restaurant.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    static Person[] staff;
    static Meal[] meals;
    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        askForInput();

        var orders = MockData.createOrders();

        for (var order : orders) {
            try {
                System.out.println("Order ID: " + order.getId());
                if (order instanceof TrackableOrder) {

                    ((TrackableOrder)order).Track();
                } else {
                    throw new DeliveryTrackNotSupportedException("Delivery tracking is not supported for this order.", null);
                }
            } catch (DeliveryTrackNotSupportedException e) {
                logger.error(e.getMessage());
                System.out.println(e.getMessage());
            }

        }
    }

    public static void askForInput() {
        if (!true) {
            staff = MockData.createStaff();
            meals = MockData.createMeals();
        }
        else {
            var scanner = new Scanner(System.in);

            var categories = new ArrayList<Category>();
            categories.add(RestaurantModelCreator.CreateCategory(scanner, categories));
            categories.add(RestaurantModelCreator.CreateCategory(scanner, categories));
            categories.add(RestaurantModelCreator.CreateCategory(scanner, categories));

            var ingredients = new ArrayList<Ingredient>();
            ingredients.add(RestaurantModelCreator.CreateIngredient(scanner, categories, ingredients));
            ingredients.add(RestaurantModelCreator.CreateIngredient(scanner, categories, ingredients));
            ingredients.add(RestaurantModelCreator.CreateIngredient(scanner, categories, ingredients));
            ingredients.add(RestaurantModelCreator.CreateIngredient(scanner, categories, ingredients));
            ingredients.add(RestaurantModelCreator.CreateIngredient(scanner, categories, ingredients));

            var meals = new ArrayList<Meal>();
            meals.add(RestaurantModelCreator.CreateMeal(scanner, categories, ingredients, meals));
            meals.add(RestaurantModelCreator.CreateMeal(scanner, categories, ingredients, meals));
            meals.add(RestaurantModelCreator.CreateMeal(scanner, categories, ingredients, meals));

            var staff = new ArrayList<Person>();
            staff.add(PersonModelCreator.CreateChef(scanner, staff));
            staff.add(PersonModelCreator.CreateChef(scanner, staff));
            staff.add(PersonModelCreator.CreateChef(scanner, staff));
            staff.add(PersonModelCreator.CreateWaiter(scanner, staff));
            staff.add(PersonModelCreator.CreateWaiter(scanner, staff));
            staff.add(PersonModelCreator.CreateWaiter(scanner, staff));
            staff.add(PersonModelCreator.CreateDeliverer(scanner, staff));
            staff.add(PersonModelCreator.CreateDeliverer(scanner, staff));
            staff.add(PersonModelCreator.CreateDeliverer(scanner, staff));

            Main.staff = (Person[])staff.toArray();
            Main.meals = (Meal[])meals.toArray();
        }
    }
}
