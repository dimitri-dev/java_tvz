package hr.java.input;

import hr.java.restaurant.model.*;
import java.util.Scanner;

import static hr.java.input.ModelScannerChoice.*;
import static hr.java.input.ModelScannerHelpers.*;

public class RestaurantModelCreator {
    public static Contract CreateContract(Scanner scanner) {
        System.out.println("Input Contract information");
        var salary = ReadSalary(scanner);
        var startDate = GetCurrentDate(); // ReadDate(scanner);
        var endDate = GetCurrentDate().plusYears(1); // ReadDate(scanner);
        var contractType = ChooseContractType(scanner);
        return new Contract(salary, startDate, endDate, contractType);
    }

    public static Bonus CreateBonus(Scanner scanner) {
        System.out.println("Input Bonus information");
        var bonus = ReadAmount(scanner);
        return new Bonus(bonus);
    }

    public static Chef CreateChef(Scanner scanner) {
        System.out.println("Input Chef information");
        var firstName = ReadFirstName(scanner);
        var lastName = ReadLastName(scanner);
        var contract = CreateContract(scanner);
        var bonus = CreateBonus(scanner);

        return new ChefBuilder().setFirstName(firstName).setLastName(lastName).setContract(contract).setBonus(bonus).createChef();
    }

    public static Deliverer CreateDeliverer(Scanner scanner) {
        System.out.println("Input Deliverer information");
        var firstName = ReadFirstName(scanner);
        var lastName = ReadLastName(scanner);
        var contract = CreateContract(scanner);
        var bonus = CreateBonus(scanner);
        var delivererType = ChooseDelivererType(scanner);

        return new DelivererBuilder().setFirstName(firstName).setLastName(lastName).setContract(contract).setBonus(bonus).setType(delivererType).createDeliverer();
    }

    public static Waiter CreateWaiter(Scanner scanner) {
        System.out.println("Input Waiter information");
        var firstName = ReadFirstName(scanner);
        var lastName = ReadLastName(scanner);
        var contract = CreateContract(scanner);
        var bonus = CreateBonus(scanner);

        return new WaiterBuilder().setFirstName(firstName).setLastName(lastName).setContract(contract).setBonus(bonus).createWaiter();
    }

    public static Address CreateAddress(Scanner scanner) {
        System.out.println("Input Address information");
        var street = ReadStreet(scanner);
        var houseNumber = ReadHouseNumber(scanner);
        var city = ReadCity(scanner);
        var postalCode = ReadPostalCode(scanner);

        return new AddressBuilder().setStreet(street).setHouseNumber(houseNumber).setCity(city).setPostalCode(postalCode).createAddress();
    }

    public static Category CreateCategory(Scanner scanner) {
        System.out.println("Input Category information");
        var name = ReadName(scanner);
        var description = ReadDescription(scanner);

        return new CategoryBuilder().setName(name).setDescription(description).createCategory();
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
        var mealType = ChooseMealType(scanner);

        switch (mealType) {
            case Meal.MealConstants.VEGAN -> {
                var fiberContentPercentage = ReadFiberContentPercentage(scanner);
                return new VeganMeal(name, category, ingredients, price, fiberContentPercentage);
            }
            case Meal.MealConstants.VEGETARIAN -> {
                var containsDairy = ReadContainsDairy(scanner);
                return new VegetarianMeal(name, category, ingredients, price, containsDairy);
            }
            case Meal.MealConstants.MEAT -> {
                var meatType = ReadMeatType(scanner);
                var meatCut = ReadMeatCut(scanner);
                return new MeatMeal(name, category, ingredients, price, meatType, meatCut);
            }
            default -> throw new IllegalArgumentException("Invalid meal type");
        }
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
