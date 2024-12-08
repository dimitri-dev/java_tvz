package hr.java.input;

import hr.java.restaurant.enumeration.ContractType;
import hr.java.restaurant.enumeration.MealType;
import hr.java.restaurant.model.*;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ModelScannerChoice {
    static Category ChooseCategory(Scanner scanner, HashSet<Category> categories) {
        var valuesMap = categories.stream()
                .collect(Collectors.toMap(
                        category -> categories.stream().toList().indexOf(category) + 1,
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

    static Deliverer ChooseDeliverer(Scanner scanner, Set<Deliverer> deliverers) {
        var valuesMap = deliverers.stream()
                .collect(Collectors.toMap(
                        entry -> List.of(deliverers).indexOf(entry) + 1,
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

    static Set<Ingredient> ChooseIngredients(Scanner scanner, HashSet<Ingredient> ingredients) {
        var valuesMap = ingredients.stream()
                .collect(Collectors.toMap(
                        ingredient -> ingredients.stream().toList().indexOf(ingredient) + 1,
                        ingredient -> ingredient
                ));

        var readerMap = valuesMap.entrySet().stream()
                .collect(Collectors.toMap(
                        entry -> entry.getKey(),
                        entry -> entry.getValue().getName()
                ));

        var values = BaseScannerHelpers.ReadInts(scanner, "Ingredient", readerMap);

        return Arrays.stream(values)
                .map(valuesMap::get)
                .collect(Collectors.toSet());
    }

    public static List<Meal> ChooseMeals(Scanner scanner, Set<Meal> meals) {
        var valuesMap = meals.stream()
                .collect(Collectors.toMap(
                        entry -> List.of(meals).indexOf(entry) + 1,
                        entry -> entry
                ));

        var readerMap = valuesMap.entrySet().stream()
                .collect(Collectors.toMap(
                        entry -> entry.getKey(),
                        entry -> entry.getValue().getName()
                ));

        var values = BaseScannerHelpers.ReadInts(scanner, "Meal", readerMap);

        return Arrays.stream(values)
                .map(valuesMap::get)
                .collect(Collectors.toList());
    }

    public static Meal ChooseMeal(Scanner scanner, Set<Meal> meals) {
        var valuesMap = IntStream.range(0, meals.size())
                .boxed()
                .collect(Collectors.toMap(
                        index -> index + 1,  // Use index+1 to get 1-based index
                        index -> new ArrayList<>(meals).get(index)  // Get the element at the index
                ));

        var readerMap = valuesMap.entrySet().stream()
                .collect(Collectors.toMap(
                        entry -> entry.getKey(),
                        entry -> entry.getValue().getName()
                ));

        var value = BaseScannerHelpers.ReadInt(scanner, "Meal", readerMap);

        return valuesMap.get(value);
    }

    static Set<Chef> ChooseChefs(Scanner scanner, Set<Person> chefs) {
        var valuesMap = chefs.stream()
                .collect(Collectors.toMap(
                        entry -> List.of(chefs).indexOf(entry) + 1,
                        entry -> (Chef)entry
                ));

        var readerMap = valuesMap.entrySet().stream()
                .collect(Collectors.toMap(
                        entry -> entry.getKey(),
                        entry -> entry.getValue().getFirstName() + " " + entry.getValue().getLastName()
                ));

        var values = BaseScannerHelpers.ReadInts(scanner, "Chef", readerMap);

        return Arrays.stream(values)
                .map(valuesMap::get)
                .collect(Collectors.toSet());
    }

    static Set<Deliverer> ChooseDeliverers(Scanner scanner, Set<Person> deliverers) {
        var valuesMap = deliverers.stream()
                .collect(Collectors.toMap(
                        entry -> List.of(deliverers).indexOf(entry) + 1,
                        entry -> (Deliverer)entry
                ));

        var readerMap = valuesMap.entrySet().stream()
                .collect(Collectors.toMap(
                        entry -> entry.getKey(),
                        entry -> entry.getValue().getFirstName() + " " + entry.getValue().getLastName()
                ));

        var values = BaseScannerHelpers.ReadInts(scanner, "Deliverer", readerMap);

        return Arrays.stream(values)
                .map(valuesMap::get)
                .collect(Collectors.toSet());
    }

    static Set<Waiter> ChooseWaiters(Scanner scanner, Set<Person> waiters) {
        var valuesMap = waiters.stream()
                .collect(Collectors.toMap(
                        entry -> List.of(waiters).indexOf(entry) + 1,
                        entry -> (Waiter)entry
                ));

        var readerMap = valuesMap.entrySet().stream()
                .collect(Collectors.toMap(
                        entry -> entry.getKey(),
                        entry -> entry.getValue().getFirstName() + " " + entry.getValue().getLastName()
                ));

        var values = BaseScannerHelpers.ReadInts(scanner, "Waiter", readerMap);

        return Arrays.stream(values)
                .map(valuesMap::get)
                .collect(Collectors.toSet());
    }

    static ContractType ChooseContractType(Scanner scanner) {
        var valuesMap = Arrays.stream(ContractType.values()).map(Enum::name)
                .collect(Collectors.toMap(
                        entry -> List.of(ContractType.values()).indexOf(entry) + 1,
                        entry -> entry
                ));

        var value = BaseScannerHelpers.ReadInt(scanner, "Contract type", valuesMap);

        return ContractType.valueOf(valuesMap.get(value));
    }

    static MealType ChooseMealType(Scanner scanner) {
        var valuesMap = Arrays.stream(MealType.values()).map(Enum::name)
                .collect(Collectors.toMap(
                        entry -> List.of(MealType.values()).indexOf(entry) + 1,
                        entry -> entry
                ));

        var value = BaseScannerHelpers.ReadInt(scanner, "Meal type", valuesMap);

        return MealType.valueOf(valuesMap.get(value));
    }
}
