package hr.java.production.main;

import hr.java.input.MockData;
import hr.java.input.RestaurantModelCreator;
import hr.java.restaurant.model.*;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    static Person[] staff;
    static Meal[] meals;
    static Order[] orders;

    public static void main(String[] args) {
        askForInput();

        var statistics = Stream.of(orders)
                .map(order -> order.getDeliverer())
                .collect(Collectors.groupingBy(
                        Deliverer::getDelivererType,
                        Collectors.collectingAndThen(
                                Collectors.toList(),
                                list -> {
                                    var _averageSalary = list.stream()
                                            .map(deliverer -> deliverer.getSalary())
                                            .reduce(BigDecimal.ZERO, BigDecimal::add)
                                            .divide(BigDecimal.valueOf(list.size()));

                                    var _numberOfDeliveredOrder = list.stream()
                                            .map(deliverer -> deliverer.getDeliveredCount())
                                            .reduce(0, Integer::sum);

                                    var _numberOfOrders = Arrays.stream(orders)
                                            .map(order -> order.getDeliverer().getDelivererType())
                                            .filter(type -> type.equals(list.getFirst().getDelivererType()))
                                            .count();

                                    var _percentageOfDeliveredOrders = _numberOfDeliveredOrder / (double) _numberOfOrders;

                                    return new Object() {
                                        final Integer numberOfDeliverers = list.size();
                                        final BigDecimal averageSalary = _averageSalary;
                                        final Integer numberOfDeliveredOrders = _numberOfDeliveredOrder;
                                        final Long numberOfTotalOrders = _numberOfOrders;
                                        final Double percentageOfDeliveredOrders = _percentageOfDeliveredOrders;
                                    };
                                }
                        )
                ));

        // foreach statistics
        for (var entry : statistics.entrySet()) {
            System.out.println("Deliverer type: " + entry.getKey());
            System.out.println("Number of deliverers: " + entry.getValue().numberOfDeliverers);
            System.out.println("Average salary: " + String.format("%.2f", entry.getValue().averageSalary));
            System.out.println("Number of delivered orders: " + entry.getValue().numberOfDeliveredOrders);
            System.out.println("Number of total orders: " + entry.getValue().numberOfTotalOrders);
            System.out.println("Percentage of delivered orders: " + String.format("%.2f", entry.getValue().percentageOfDeliveredOrders * 100) + "%");
            System.out.println("-----------------------------------");
        }
    }

    public static void askForInput() {
        if (true) {
            staff = MockData.createStaff();
            meals = MockData.createMeals();
            orders = MockData.createOrders();
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
