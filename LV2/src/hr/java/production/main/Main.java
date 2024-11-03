package hr.java.production.main;

import hr.java.input.MockData;
import hr.java.input.RestaurantModelCreator;
import hr.java.restaurant.model.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    static Person[] staff;
    static Meal[] meals;

    public static void main(String[] args) {
        askForInput();

        /*  Zaposlenika s najvećom plaćom */
        var personContractMap = Stream.of(staff)
                .collect(Collectors.toMap(
                        entry -> entry,
                        entry -> switch (entry) {
                            case Chef chef -> chef.getContract();
                            case Waiter waiter -> waiter.getContract();
                            case Deliverer deliverer -> deliverer.getContract();
                            default -> throw new IllegalArgumentException("Unknown person type");
                        }
                ));

        var highestSalary = personContractMap.values().stream()
                .map(entry -> entry.getSalary())
                .max(BigDecimal::compareTo)
                .orElseThrow();

        var highestPaidEmployees = personContractMap.entrySet().stream()
                .filter(entry -> entry.getValue().getSalary().compareTo(highestSalary) == 0)
                .map(entry -> entry.getKey())
                .toList();

        System.out.println("Zaposlenic(i) s najvećom plaćom (" + highestSalary + "):");
        highestPaidEmployees.forEach(employee -> {
            try {
                switch (employee) {
                    case Chef chef -> chef.print(0, true);
                    case Waiter waiter -> waiter.print(0, true);
                    case Deliverer deliverer -> deliverer.print(0, true);
                    default -> throw new IllegalArgumentException("Unknown person type");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        /* Zaposlenika s najdužim ugovorom (koji je najranije započeo) */

        // TODO (imo): trebalo bi gledati endDate - startDate po contractu, ali to nije zahtjev zadatka
        var earliestStartDate = personContractMap.values().stream()
                .map(entry -> entry.getStartDate())
                .min(LocalDate::compareTo)
                .orElseThrow();

        var longestContractEmployees = personContractMap.entrySet().stream()
                .filter(entry -> entry.getValue().getStartDate().isEqual(earliestStartDate))
                .map(entry -> entry.getKey())
                .toList();

        System.out.println("Zaposlenic(i) s najdužim ugovorom (najranije započetim):");
        longestContractEmployees.forEach(employee -> {
            try {
                switch (employee) {
                    case Chef chef -> chef.print(0, true);
                    case Waiter waiter -> waiter.print(0, true);
                    case Deliverer deliverer -> deliverer.print(0, true);
                    default -> throw new IllegalArgumentException("Unknown person type");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        /* U tom polju potrebno je pronaći i ispisati podatke o jelima koja imaju najveći i najmanji broj kilokalorija. */
        var mealPerKcal = Arrays.stream(meals)
                .collect(Collectors.groupingBy(
                        entry -> switch (entry) {
                            case VeganMeal veganMeal -> Arrays.stream(veganMeal.getIngredients()).map(Ingredient::getKcal).reduce(BigDecimal.ZERO, BigDecimal::add);
                            case VegetarianMeal vegetarianMeal -> Arrays.stream(vegetarianMeal.getIngredients()).map(Ingredient::getKcal).reduce(BigDecimal.ZERO, BigDecimal::add);
                            case MeatMeal meatMeal -> Arrays.stream(meatMeal.getIngredients()).map(Ingredient::getKcal).reduce(BigDecimal.ZERO, BigDecimal::add);
                            default -> throw new IllegalArgumentException("Unknown meal type");
                        },
                        Collectors.toList()
                ));

        var highestKcalMeals = mealPerKcal.entrySet().stream()
                .max(Map.Entry.comparingByKey())
                .orElseThrow();

        System.out.println(((long) highestKcalMeals.getValue().size() == 1 ? "Jelo" : "Jela") + " s najviše kcal (" + highestKcalMeals.getKey() + "):");
        highestKcalMeals.getValue().forEach(meal -> {
            try {
                switch (meal) {
                    case VeganMeal veganMeal -> veganMeal.print(0, true);
                    case VegetarianMeal vegetarianMeal -> vegetarianMeal.print(0, true);
                    case MeatMeal meatMeal -> meatMeal.print(0, true);
                    default -> throw new IllegalArgumentException("Unknown meal type");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        var lowestKcalMeals = mealPerKcal.entrySet().stream()
                .min(Map.Entry.comparingByKey())
                .orElseThrow();

        System.out.println(((long) lowestKcalMeals.getValue().size() == 1 ? "Jelo" : "Jela") + " s najmanje kcal (" + lowestKcalMeals.getKey() + "):");
        lowestKcalMeals.getValue().forEach(meal -> {
            try {
                switch (meal) {
                    case VeganMeal veganMeal -> veganMeal.print(0, true);
                    case VegetarianMeal vegetarianMeal -> vegetarianMeal.print(0, true);
                    case MeatMeal meatMeal -> meatMeal.print(0, true);
                    default -> throw new IllegalArgumentException("Unknown meal type");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public static void askForInput() {
        if (true) {
            staff = MockData.createStaff();
            meals = MockData.createMeals();
        }
        else {
            var scanner = new Scanner(System.in);

            var categories = new Category[] {
                    RestaurantModelCreator.CreateCategory(scanner),
                    RestaurantModelCreator.CreateCategory(scanner),
                    RestaurantModelCreator.CreateCategory(scanner)
            };

            var ingredients = new Ingredient[] {
                    RestaurantModelCreator.CreateIngredient(scanner, categories),
                    RestaurantModelCreator.CreateIngredient(scanner, categories),
                    RestaurantModelCreator.CreateIngredient(scanner, categories),
                    RestaurantModelCreator.CreateIngredient(scanner, categories),
                    RestaurantModelCreator.CreateIngredient(scanner, categories),
            };

            meals = new Meal[] {
                    RestaurantModelCreator.CreateMeal(scanner, categories, ingredients),
                    RestaurantModelCreator.CreateMeal(scanner, categories, ingredients),
                    RestaurantModelCreator.CreateMeal(scanner, categories, ingredients),
            };

            var chefs = new Chef[] {
                    RestaurantModelCreator.CreateChef(scanner),
                    RestaurantModelCreator.CreateChef(scanner),
                    RestaurantModelCreator.CreateChef(scanner),
            };

            var waiters = new Waiter[] {
                    RestaurantModelCreator.CreateWaiter(scanner),
                    RestaurantModelCreator.CreateWaiter(scanner),
                    RestaurantModelCreator.CreateWaiter(scanner),
            };

            var deliverers = new Deliverer[] {
                    RestaurantModelCreator.CreateDeliverer(scanner),
                    RestaurantModelCreator.CreateDeliverer(scanner),
                    RestaurantModelCreator.CreateDeliverer(scanner),
            };

            staff = new Person[] {
                    chefs[0],
                    chefs[1],
                    chefs[2],
                    waiters[0],
                    waiters[1],
                    waiters[2],
                    deliverers[0],
                    deliverers[1],
                    deliverers[2],
            };
        }
    }
}
