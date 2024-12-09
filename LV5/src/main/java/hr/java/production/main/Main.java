package hr.java.production.main;

import hr.java.input.MockData;
import hr.java.input.PersonModelCreator;
import hr.java.input.RestaurantModelCreator;
import hr.java.restaurant.generics.RestaurantLabourExchangeOffice;
import hr.java.restaurant.model.*;

import java.math.BigDecimal;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Main {
    static List<Person> staff;
    static List<Order> orders;
    static RestaurantLabourExchangeOffice<Restaurant> _restaurantLabourExchangeOffice;

    public static void main(String[] args) {
        askForInput();

        Function<Person, BigDecimal> salary = (Person o) -> {
            if (o instanceof Chef c) return c.getContract().getSalary();
            if (o instanceof Deliverer d) return d.getContract().getSalary();
            if (o instanceof Waiter w) return w.getContract().getSalary();

            throw new UnsupportedOperationException("Unknown person type");
        };

        staff.sort((o1, o2) -> {
            var salary1 = salary.apply(o1);
            var salary2 = salary.apply(o2);

            return salary2.compareTo(salary1);
        });

        System.out.println("Staff sorted by salary in descending order: ");
        for (var employee : staff) {
            try {
                employee.print();
            }
            catch (Exception _) {
                // Ignore
            }
        }

        Function<Person, Long> contractLength = (Person o) -> {
            if (o instanceof Chef c) return ChronoUnit.DAYS.between(c.getContract().getEndDate(), c.getContract().getStartDate());
            if (o instanceof Deliverer d) return ChronoUnit.DAYS.between(d.getContract().getEndDate(), d.getContract().getStartDate());
            if (o instanceof Waiter w) return ChronoUnit.DAYS.between(w.getContract().getEndDate(), w.getContract().getStartDate());

            throw new UnsupportedOperationException("Unknown person type");
        };

        staff.sort((o1, o2) -> {
            var contract1 = contractLength.apply(o1);
            var contract2 = contractLength.apply(o2);

            return Long.compare(contract2, contract1);
        });

        System.out.println("Staff sorted by contract length in ascending order: ");
        for (var employee : staff) {
            try {
                employee.print();
            }
            catch (Exception _) {
                // Ignore
            }
        }

        // Napisati lambda izraz koji pronalazi restoran u kojem je zaposleno najviše radnika te
        // ispisati njegove podatke na kraju programa.
        _restaurantLabourExchangeOffice.getRestaurants().stream()
                .max(Comparator.comparingInt((Restaurant r) -> r.getChefs().size() + r.getDeliverers().size() + r.getWaiters().size()))
                .ifPresentOrElse((r) -> {
                    try {
                        System.out.println("Restaurant with most employees: ");
                        r.print();
                    }
                    catch (Exception _) {
                        // Ignore
                    }
                }, () -> System.out.println("No restaurants found"));

        // Napisati lambda izraz koji pronalazi jelo koje je najčešće naručivano te ispisati podatke o
        // njemu na kraju programa.
        orders.stream()
                .flatMap((Order o) -> o.getMeals().stream())
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet().stream()
                .max(Comparator.comparingLong(Map.Entry::getValue))
                .map(Map.Entry::getKey)
                .ifPresentOrElse((m) -> System.out.println("Most ordered meal was: " + m.getName()),() -> System.out.println("No orders found"));


        // Napisati lambda izraz koji za svaku narudžbu ispisuje sve namirnice koje su korištene u
        // naručenim jelima. Rezultate ispisati na kraju programa.

        orders.forEach((Order o) -> {
            System.out.println("Order " + o.getId() + " ingredients:");
            o.getMeals().stream()
                    .flatMap((Meal m) -> m.getIngredients().stream())
                    .distinct() // Makes sense to me? If we have multiples of the same meal, why would we print the same ingredient multiple times?
                    .forEach((Ingredient i) -> {
                        System.out.println(i.getName());
                    });
        });

        // Napisati lambda izraz koji za listu narudžbi određuje ukupnu cijenu narudžbe. Rezultat
        // ispisati na kraju programa.
        var totalOrderAmount = orders.stream()
                .map((Order o) -> o.getMeals().stream()
                        .map(Meal::getPrice)
                        .reduce(BigDecimal.ZERO, BigDecimal::add))
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        System.out.println("Total order amount: " + totalOrderAmount);

        // Napisati lambda izraz koji za svaki grad iz adrese ispisuje restorane koji se nalaze u tom
        // gradu. Rezultat ispisati na kraju programa.
        _restaurantLabourExchangeOffice.getRestaurants().stream()
                .collect(Collectors.groupingBy((Restaurant r) -> r.getAddress().getCity()))
                .forEach((city, restaurants) -> {
                    System.out.println("Restaurants in " + city + ":");
                    for (var restaurant : restaurants) {
                        try {
                            restaurant.print();
                        }
                        catch (Exception _) {
                            // Ignore
                        }
                    }
                });

    }

    public static void askForInput() {
        if (true) {
            staff = new ArrayList<>(MockData.createStaff());
            _restaurantLabourExchangeOffice = new RestaurantLabourExchangeOffice<Restaurant>(new ArrayList<>(MockData.createRestaurants()));
            orders = MockData.createOrders(_restaurantLabourExchangeOffice.getRestaurants());
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

            _restaurantLabourExchangeOffice = new RestaurantLabourExchangeOffice<Restaurant>(new ArrayList<>());
            var restaurant = RestaurantModelCreator.CreateRestaurant(scanner, meals, chefs, waiters, deliverers, new HashSet<>(_restaurantLabourExchangeOffice.getRestaurants()));
        }
    }
}
