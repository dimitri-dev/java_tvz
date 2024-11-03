package hr.java.input;

import hr.java.restaurant.model.*;
import java.util.Scanner;

import static hr.java.input.ModelScannerChoice.*;
import static hr.java.input.ModelScannerHelpers.*;

public class RestaurantModelCreator {
    public static Chef CreateChef(Scanner scanner) {
        System.out.println("Input Chef information");
        var firstName = ReadFirstName(scanner);
        var lastName = ReadLastName(scanner);
        var salary = ReadSalary(scanner);

        return new Chef(firstName, lastName, salary);
    }

    public static Deliverer CreateDeliverer(Scanner scanner) {
        System.out.println("Input Deliverer information");
        var firstName = ReadFirstName(scanner);
        var lastName = ReadLastName(scanner);
        var salary = ReadSalary(scanner);

        return new Deliverer(firstName, lastName, salary);
    }

    public static Waiter CreateWaiter(Scanner scanner) {
        System.out.println("Input Waiter information");
        var firstName = ReadFirstName(scanner);
        var lastName = ReadLastName(scanner);
        var salary = ReadSalary(scanner);

        return new Waiter(firstName, lastName, salary);
    }

    public static Address CreateAddress(Scanner scanner) {
        System.out.println("Input Address information");
        var street = ReadStreet(scanner);
        var houseNumber = ReadHouseNumber(scanner);
        var city = ReadCity(scanner);
        var postalCode = ReadPostalCode(scanner);

        return new Address(street, houseNumber, city, postalCode);
    }

    public static Category CreateCategory(Scanner scanner) {
        System.out.println("Input Category information");
        var name = ReadName(scanner);
        var description = ReadDescription(scanner);

        return new Category(name, description);
    }

    public static Ingredient CreateIngredient(Scanner scanner, Category[] existingCategories) {
        System.out.println("Input Ingredient information");
        var name = ReadName(scanner);
        var kcal = ReadKcal(scanner);
        var preparationMethod = ReadPreparationMethod(scanner);
        var category = ChooseCategory(scanner, existingCategories);

        return new Ingredient(name, category, kcal, preparationMethod);
    }

    public static Meal CreateMeal(Scanner scanner, Category[] existingCategories, Ingredient[] existingIngredients) {
        System.out.println("Input Meal information");
        var name = ReadName(scanner);
        var price = ReadPrice(scanner);
        var category = ChooseCategory(scanner, existingCategories);
        var ingredients = ChooseIngredients(scanner, existingIngredients);

        return new Meal(name, category, ingredients, price);
    }

    public static Restaurant CreateRestaurant(Scanner scanner, Meal[] existingMeals, Chef[] existingChefs, Waiter[] existingWaiters, Deliverer[] existingDeliverers) {
        System.out.println("Input Restaurant information");
        var name = ReadName(scanner);
        var address = CreateAddress(scanner);
        var meals = ChooseMeals(scanner, existingMeals);
        var chefs = ChooseChefs(scanner, existingChefs);
        var waiters = ChooseWaiters(scanner, existingWaiters);
        var deliverers = ChooseDeliverers(scanner, existingDeliverers);

        return new Restaurant(name, address, meals, chefs, waiters, deliverers);
    }

    public static Order CreateOrder(Scanner scanner, Restaurant[] existingRestaurants) {
        System.out.println("Input Order information");
        var restaurant = ChooseRestaurant(scanner, existingRestaurants);
        var meals = ChooseMeals(scanner, restaurant.getMeals());
        var deliverer = ChooseDeliverer(scanner, restaurant.getDeliverers());
        var deliveryDateAndTime = GetCurrentDateTime(); // ReadDateTime(scanner);

        return new Order(restaurant, meals, deliverer, deliveryDateAndTime);
    }
}
