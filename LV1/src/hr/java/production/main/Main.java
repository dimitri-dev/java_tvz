package hr.java.production.main;

import hr.java.input.MockData;
import hr.java.input.RestaurantModelCreator;
import hr.java.restaurant.model.*;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        Order[] orders;

        if (!true) {
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

            var meals = new Meal[] {
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

            var restaurants = new Restaurant[] {
                    RestaurantModelCreator.CreateRestaurant(scanner, meals, chefs, waiters, deliverers),
                    RestaurantModelCreator.CreateRestaurant(scanner, meals, chefs, waiters, deliverers),
                    RestaurantModelCreator.CreateRestaurant(scanner, meals, chefs, waiters, deliverers),
            };

            orders = new Order[] {
                    RestaurantModelCreator.CreateOrder(scanner, restaurants),
                    RestaurantModelCreator.CreateOrder(scanner, restaurants),
                    RestaurantModelCreator.CreateOrder(scanner, restaurants),
            };
        }

        /*  Pronaći restoran kojem je poslana najskuplja narudžba. Ako ima više restorana s istom
najvišom cijenom narudžbe, potrebno je ispisati sve podatke o svim takvim restoranima. */
        Map<Restaurant, BigDecimal> restaurantEarnings = Arrays.stream(orders)
                .collect(Collectors.groupingBy(order -> order.getRestaurant(),
                        Collectors.reducing(BigDecimal.ZERO,
                                order -> Arrays.stream(order.getMeals())
                                        .map(Meal::getPrice)
                                        .reduce(BigDecimal.ZERO, BigDecimal::add),
                                BigDecimal::add)
                        ));

        BigDecimal maxEarnings = restaurantEarnings.values().stream()
                .sorted(Collections.reverseOrder())
                .findFirst()
                .orElse(BigDecimal.ZERO);

        System.out.print("Restoran(i) sa najvišim prihodom (" + maxEarnings + "):");
        restaurantEarnings.entrySet().stream()
                .filter(entry -> entry.getValue().compareTo(maxEarnings) == 0)
                .map(Map.Entry::getKey)
                .forEach(restaurant -> {
                    try {
                        restaurant.print(0, true);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });

        /*  Pronaći dostavljača koji je dostavio najviše dostava. Ako ima više dostavljača s najviše
dostava, potrebno je ispisati sve podatke o svim dostavljačima. */

        Map<Deliverer, Long> deliveryScoreboard = Arrays.stream(orders)
                .collect(Collectors.groupingBy(order -> order.getDeliverer(), Collectors.counting()));

        Long maxDeliveries = deliveryScoreboard.values().stream()
                .sorted(Collections.reverseOrder())
                .findFirst()
                .orElse(0L);

        System.out.print("\nDostavljač(i) sa najviše dostava (" + maxDeliveries + "):");
        deliveryScoreboard.entrySet().stream()
                .filter(entry -> entry.getValue().compareTo(maxDeliveries) == 0)
                .map(Map.Entry::getKey)
                .forEach(deliverer -> {
                    try {
                        deliverer.print(0, true);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });
    }
}
