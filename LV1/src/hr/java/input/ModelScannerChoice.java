package hr.java.input;

import hr.java.restaurant.model.*;

import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Collectors;

public class ModelScannerChoice {
    static Category ChooseCategory(Scanner scanner, Category[] categories) {
        var valuesMap = Arrays.stream(categories)
                .collect(Collectors.toMap(
                        category -> Arrays.asList(categories).indexOf(category) + 1,
                        category -> category
                ));

        var readerMap = valuesMap.entrySet().stream()
                .collect(Collectors.toMap(
                        entry -> entry.getKey(),
                        entry -> entry.getValue().getName()
                ));

        var value = BaseScannerHelpers.ReadInt(scanner, "Category", readerMap);

        return valuesMap.get(value);
    }

    static Deliverer ChooseDeliverer(Scanner scanner, Deliverer[] deliverers) {
        var valuesMap = Arrays.stream(deliverers)
                .collect(Collectors.toMap(
                        entry -> Arrays.asList(deliverers).indexOf(entry) + 1,
                        entry -> entry
                ));

        var readerMap = valuesMap.entrySet().stream()
                .collect(Collectors.toMap(
                        entry -> entry.getKey(),
                        entry -> entry.getValue().getFirstName() + " " + entry.getValue().getLastName()
                ));

        var value = BaseScannerHelpers.ReadInt(scanner, "Deliverer", readerMap);

        return valuesMap.get(value);
    }

    static Restaurant ChooseRestaurant(Scanner scanner, Restaurant[] restaurants) {
        var valuesMap = Arrays.stream(restaurants)
                .collect(Collectors.toMap(
                        entry -> Arrays.asList(restaurants).indexOf(entry) + 1,
                        entry -> entry
                ));

        var readerMap = valuesMap.entrySet().stream()
                .collect(Collectors.toMap(
                        entry -> entry.getKey(),
                        entry -> entry.getValue().getName()
                ));

        var value = BaseScannerHelpers.ReadInt(scanner, "Restaurant", readerMap);

        return valuesMap.get(value);
    }

    static Ingredient[] ChooseIngredients(Scanner scanner, Ingredient[] ingredients) {
        var valuesMap = Arrays.stream(ingredients)
                .collect(Collectors.toMap(
                        ingredient -> Arrays.asList(ingredients).indexOf(ingredient) + 1,
                        ingredient -> ingredient
                ));

        var readerMap = valuesMap.entrySet().stream()
                .collect(Collectors.toMap(
                        entry -> entry.getKey(),
                        entry -> entry.getValue().getName()
                ));

        var values = BaseScannerHelpers.ReadInts(scanner, "Ingredient", readerMap);

        return Arrays.stream(values)
                .map(idx -> valuesMap.get(idx))
                .toArray(Ingredient[]::new);
    }

    static Meal[] ChooseMeals(Scanner scanner, Meal[] meals) {
        var valuesMap = Arrays.stream(meals)
                .collect(Collectors.toMap(
                        entry -> Arrays.asList(meals).indexOf(entry) + 1,
                        entry -> entry
                ));

        var readerMap = valuesMap.entrySet().stream()
                .collect(Collectors.toMap(
                        entry -> entry.getKey(),
                        entry -> entry.getValue().getName()
                ));

        var values = BaseScannerHelpers.ReadInts(scanner, "Meal", readerMap);

        return Arrays.stream(values)
                .map(idx -> valuesMap.get(idx))
                .toArray(Meal[]::new);
    }

    static Chef[] ChooseChefs(Scanner scanner, Chef[] chefs) {
        var valuesMap = Arrays.stream(chefs)
                .collect(Collectors.toMap(
                        entry -> Arrays.asList(chefs).indexOf(entry) + 1,
                        entry -> entry
                ));

        var readerMap = valuesMap.entrySet().stream()
                .collect(Collectors.toMap(
                        entry -> entry.getKey(),
                        entry -> entry.getValue().getFirstName() + " " + entry.getValue().getLastName()
                ));

        var values = BaseScannerHelpers.ReadInts(scanner, "Chef", readerMap);

        return Arrays.stream(values)
                .map(idx -> valuesMap.get(idx))
                .toArray(Chef[]::new);
    }

    static Deliverer[] ChooseDeliverers(Scanner scanner, Deliverer[] deliverers) {
        var valuesMap = Arrays.stream(deliverers)
                .collect(Collectors.toMap(
                        entry -> Arrays.asList(deliverers).indexOf(entry) + 1,
                        entry -> entry
                ));

        var readerMap = valuesMap.entrySet().stream()
                .collect(Collectors.toMap(
                        entry -> entry.getKey(),
                        entry -> entry.getValue().getFirstName() + " " + entry.getValue().getLastName()
                ));

        var values = BaseScannerHelpers.ReadInts(scanner, "Deliverer", readerMap);

        return Arrays.stream(values)
                .map(idx -> valuesMap.get(idx))
                .toArray(Deliverer[]::new);
    }

    static Waiter[] ChooseWaiters(Scanner scanner, Waiter[] waiters) {
        var valuesMap = Arrays.stream(waiters)
                .collect(Collectors.toMap(
                        entry -> Arrays.asList(waiters).indexOf(entry) + 1,
                        entry -> entry
                ));

        var readerMap = valuesMap.entrySet().stream()
                .collect(Collectors.toMap(
                        entry -> entry.getKey(),
                        entry -> entry.getValue().getFirstName() + " " + entry.getValue().getLastName()
                ));

        var values = BaseScannerHelpers.ReadInts(scanner, "Waiter", readerMap);

        return Arrays.stream(values)
                .map(idx -> valuesMap.get(idx))
                .toArray(Waiter[]::new);
    }
}
