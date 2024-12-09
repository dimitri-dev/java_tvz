package hr.java.input;

import hr.java.restaurant.exception.*;
import hr.java.restaurant.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

import static hr.java.input.ModelScannerChoice.*;
import static hr.java.input.ModelScannerHelpers.*;

public class RestaurantModelCreator {
    private static final Logger logger = LoggerFactory.getLogger(RestaurantModelCreator.class);

    public static Address CreateAddress(Scanner scanner) {
        logger.info("Started creating an address");

        System.out.println("Input Address information");
        var street = ReadStreet(scanner);
        var houseNumber = ReadHouseNumber(scanner);
        var city = ReadCity(scanner);
        var postalCode = ReadPostalCode(scanner);

        logger.info("Address created successfully");
        return new AddressBuilder().setStreet(street).setHouseNumber(houseNumber).setCity(city).setPostalCode(postalCode).createAddress();
    }

    public static Category CreateCategory(Scanner scanner, HashSet<Category> existingCategories) {
        logger.info("Started creating a category");
        try {
            System.out.println("Input Category information");
            var name = ReadName(scanner);

            if (existingCategories.stream().anyMatch(category -> category.getName().equals(name))) {
                throw new DuplicateEntryException("A category with the same name already exists.");
            }

            var description = ReadDescription(scanner);

            logger.info("Category created successfully");
            return new CategoryBuilder().setName(name).setDescription(description).createCategory();
        } catch (DuplicateEntryException e) {
            logger.error(e.getMessage());
            System.out.println(e.getMessage());
            System.out.println("Please try again.");

            return CreateCategory(scanner, existingCategories);
        }
    }

    public static Ingredient CreateIngredient(Scanner scanner, HashSet<Category> existingCategories, HashSet<Ingredient> existingIngredients) {
        logger.info("Started creating an ingredient");
        try {
            System.out.println("Input Ingredient information");
            var name = ReadName(scanner);

            if (existingIngredients.stream().anyMatch(ingredient -> ingredient.getName().equals(name))) {
                throw new DuplicateEntryException("An ingredient with the same name already exists.");
            }

            var kcal = ReadKcal(scanner);

            if (kcal.intValue() < 0 || kcal.intValue() > 1000) {
                throw new BogusEntryException("Kcal must be a non-negative number smaller than or equal to 1000.");
            }

            var category = ChooseCategory(scanner, existingCategories);
            var preparationMethod = ReadPreparationMethod(scanner);

            logger.info("Ingredient created successfully");
            return new Ingredient(name, category, kcal, preparationMethod);
        } catch (DuplicateEntryException | BogusEntryException e) {
            logger.error(e.getMessage());
            System.out.println(e.getMessage());
            System.out.println("Please try again.");

            return CreateIngredient(scanner, existingCategories, existingIngredients);
        }
    }

    public static Meal CreateMeal(Scanner scanner, HashSet<Category> existingCategories, HashSet<Ingredient> existingIngredients, HashSet<Meal> existingMeals) {
        logger.info("Started creating a meal");
        try {
            System.out.println("Input Meal information");
            var name = ReadName(scanner);

            if (existingMeals.stream().anyMatch(meal -> meal.getName().equals(name))) {
                throw new DuplicateEntryException("A meal with the same name already exists.");
            }

            System.out.println("Input Meal information");
            var price = ReadPrice(scanner);

            if (price.intValue() < 0 || price.intValue() > 1000) {
                throw new BogusEntryException("Price must be a non-negative number smaller than or equal to 1000.");
            }

            var category = ChooseCategory(scanner, existingCategories);
            var ingredients = ChooseIngredients(scanner, existingIngredients);
            var mealType = ChooseMealType(scanner);

            switch (mealType) {
                case Vegan -> {
                    try {
                        var fiberContentPercentage = ReadFiberContentPercentage(scanner);
                        if (fiberContentPercentage.intValue() < 0 || fiberContentPercentage.intValue() > 100) {
                            throw new BogusEntryException("Fiber content percentage must be a non-negative number between 0 and 100.");
                        }

                        logger.info("Meal created successfully");
                        return new VeganMeal(name, category, ingredients, price, fiberContentPercentage);
                    }
                    catch (BogusEntryException e) {
                        logger.error(e.getMessage());
                        System.out.println(e.getMessage());
                        System.out.println("Please try again.");

                        return CreateMeal(scanner, existingCategories, existingIngredients, existingMeals);
                    }
                }
                case Vegetarian -> {
                    var containsDairy = ReadContainsDairy(scanner);

                    logger.info("Meal created successfully");
                    return new VegetarianMeal(name, category, ingredients, price, containsDairy);
                }
                case Meat -> {
                    var meatType = ReadMeatType(scanner);
                    var meatCut = ReadMeatCut(scanner);

                    logger.info("Meal created successfully");
                    return new MeatMeal(name, category, ingredients, price, meatType, meatCut);
                }
                // This should never happen
                default -> throw new IllegalArgumentException("Invalid meal type");
            }
        } catch (DuplicateEntryException | BogusEntryException e) {
            logger.error(e.getMessage());
            System.out.println(e.getMessage());
            System.out.println("Please try again.");

            return CreateMeal(scanner, existingCategories, existingIngredients, existingMeals);
        }
    }

    public static Restaurant CreateRestaurant(Scanner scanner, Set<Meal> existingMeals, Set<Person> existingChefs, Set<Person> existingWaiters, Set<Person> existingDeliverers, HashSet<Restaurant> existingRestaurants) {
        logger.info("Started creating a restaurant");
        try {
            System.out.println("Input Restaurant information");
            var name = ReadName(scanner);

            if (existingRestaurants.stream().anyMatch(restaurant -> restaurant.getName().equals(name))) {
                throw new DuplicateEntryException("A restaurant with the same name already exists.");
            }

            var address = CreateAddress(scanner);
            var meals = new HashSet<>(ChooseMeals(scanner, existingMeals));
            var chefs = ChooseChefs(scanner, existingChefs);
            var waiters = ChooseWaiters(scanner, existingWaiters);
            var deliverers = ChooseDeliverers(scanner, existingDeliverers);

            logger.info("Restaurant created successfully");

            return new Restaurant(name, address, meals, chefs, waiters, deliverers);
        } catch (DuplicateEntryException e) {
            logger.error(e.getMessage());
            System.out.println(e.getMessage());
            System.out.println("Please try again.");

            return CreateRestaurant(scanner, existingMeals, existingChefs, existingWaiters, existingDeliverers, existingRestaurants);
        }
    }

    public static Order CreateOrder(Scanner scanner, Restaurant[] existingRestaurants) {
        logger.info("Started creating an order");
        System.out.println("Input Order information");
        var restaurant = ChooseRestaurant(scanner, existingRestaurants);
        var meals = ChooseMeals(scanner, restaurant.getMeals());
        var deliverer = ChooseDeliverer(scanner, restaurant.getDeliverers());
        var deliveryDateAndTime = GetCurrentDateTime(); // ReadDateTime(scanner);

        logger.info("Order created successfully");
        return new Order(restaurant, meals, deliverer, deliveryDateAndTime);
    }
}
