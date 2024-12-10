package hr.java.production.main;

import hr.java.input.FileReader;
import hr.java.restaurant.generics.NarudzbaDatotekaDatasource;
import hr.java.restaurant.generics.RestaurantLabourExchangeOffice;
import hr.java.restaurant.model.*;

import java.math.BigDecimal;
import java.nio.file.Path;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Main {
    static List<Category> categories;
    static List<Ingredient> ingredients;
    static List<Contract> contracts;
    static List<Person> staff;
    static List<Address> addresses;
    static List<Meal> meals;
    static RestaurantLabourExchangeOffice<Restaurant> _restaurantLabourExchangeOffice;
    static NarudzbaDatotekaDatasource narudzbaDatotekaDatasource;

    public static void main(String[] args) {
        loadFromFiles();

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
        narudzbaDatotekaDatasource.getData().stream()
                .flatMap((Order o) -> o.getMeals().stream())
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet().stream()
                .max(Comparator.comparingLong(Map.Entry::getValue))
                .map(Map.Entry::getKey)
                .ifPresentOrElse((m) -> System.out.println("Most ordered meal was: " + m.getName()),() -> System.out.println("No orders found"));

        // Napisati lambda izraz koji za listu narudžbi određuje ukupnu cijenu narudžbe. Rezultat
        // ispisati na kraju programa.
        var totalOrderAmount = narudzbaDatotekaDatasource.getData().stream()
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

        ArrayList<Integer> years = new ArrayList<>();

        do {
            // Get input from user
            System.out.println("Dohvati iz arhive prema godini izrade narudzbe (22 ili range godina prema formatu 20-23: ");
            Scanner scanner = new Scanner(System.in);
            var line = scanner.nextLine();

            var yrs = line.contains("-") ? Arrays.stream(line.split("-")).mapToInt(Integer::parseInt).toArray() : new int[] { Integer.parseInt(line) };

            if (yrs.length == 1 && yrs[0] >= 0 && yrs[0] <= 99) {
                yrs[0] += 2000;
                years.add(yrs[0]);
                break;
            }

            if (yrs.length == 2 && yrs[0] >= 0 && yrs[0] <= 99 && yrs[1] >= 0 && yrs[1] <= 99 && yrs[0] <= yrs[1]) {
                yrs[0] += 2000;
                yrs[1] += 2000;
                years.add(yrs[0]);
                years.add(yrs[1]);
                break;
            }

            System.out.println("Invalid input, please try again.");
        } while (true);

        // filter orders
        var filteredOrders = narudzbaDatotekaDatasource.getData().stream()
                .filter((Order o) -> {
                    var year = o.getDeliveryDateAndTime().getYear();
                    if (years.size() == 1) {
                        return year == years.getFirst();
                    } else {
                        return year >= years.get(0) && year <= years.get(1);
                    }
                })
                .toList();

        narudzbaDatotekaDatasource.saveData();
    }

    public static void loadFromFiles() {
        categories = FileReader.readCategories();
        ingredients = FileReader.readIngredients(categories);
        contracts = FileReader.readContracts();

        staff = new ArrayList<>();

        var chefs = FileReader.readChefs(contracts);
        staff.addAll(chefs);

        var waiters = FileReader.readWaiters(contracts);
        staff.addAll(waiters);

        var deliverers = FileReader.readDeliverers(contracts);
        staff.addAll(deliverers);

        meals = FileReader.readMeals(categories, ingredients);
        addresses = FileReader.readAddresses();
        _restaurantLabourExchangeOffice = new RestaurantLabourExchangeOffice<Restaurant>(FileReader.readRestaurants(addresses, meals, waiters, chefs, deliverers));

        narudzbaDatotekaDatasource = new NarudzbaDatotekaDatasource(Path.of("dat"), "orders.txt");
        narudzbaDatotekaDatasource.loadData(FileReader.readOrders(_restaurantLabourExchangeOffice.getRestaurants()));
    }
}
