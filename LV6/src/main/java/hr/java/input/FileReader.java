package hr.java.input;

import hr.java.restaurant.enumeration.ContractType;
import hr.java.restaurant.enumeration.MealType;
import hr.java.restaurant.model.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.function.Function;

public class FileReader {
    public static class Result<T extends Entity> {
        public T entity;
        public int additionalLines;

        public Result(T entity) {
            this.entity = entity;
            this.additionalLines = 0;
        }

        public Result(T entity, int additionalLines) {
            this.entity = entity;
            this.additionalLines = additionalLines;
        }
    }

    public static List<Category> readCategories() {
        return readData("dat/categories.txt", 3, (values) -> {
            var category = new CategoryBuilder().setName(values.get(1)).setDescription(values.get(2)).createCategory();
            category.setId(Long.parseLong(values.get(0)));
            return new Result<>(category);
        });
    }

    public static List<Ingredient> readIngredients(List<Category> categories) {
        return readData("dat/ingredients.txt", 5, (values) -> {
            var category = categories.stream().filter(c -> c.getId() == Long.parseLong(values.get(2))).findFirst().orElse(null);

            var ingredient = new Ingredient(values.get(1), category, new BigDecimal(values.get(3)), values.get(4));
            ingredient.setId(Long.parseLong(values.get(0)));
            return new Result<>(ingredient);
        });
    }

    public static List<Contract> readContracts() {
        return readData("dat/contracts.txt", 5, (values) -> {
            var contract = new Contract(new BigDecimal(values.get(1)), LocalDate.parse(values.get(2)), LocalDate.parse(values.get(3)), ContractType.valueOf(values.get(4)));
            contract.setId(Long.parseLong(values.get(0)));
            return new Result<>(contract);
        });
    }

    public static List<Chef> readChefs(List<Contract> contracts) {
        return readData("dat/chefs.txt", 4, (values) -> {
            var contract = contracts.stream().filter(c -> c.getId() == Long.parseLong(values.get(3))).findFirst().orElse(null);

            var chef = new ChefBuilder().setFirstName(values.get(1)).setLastName(values.get(2)).setContract(contract).createChef();
            chef.setId(Long.parseLong(values.get(0)));
            return new Result<>(chef);
        });
    }

    public static List<Waiter> readWaiters(List<Contract> contracts) {
        return readData("dat/waiters.txt", 4, (values) -> {
            var contract = contracts.stream().filter(c -> c.getId() == Long.parseLong(values.get(3))).findFirst().orElse(null);

            var waiter = new WaiterBuilder().setFirstName(values.get(1)).setLastName(values.get(2)).setContract(contract).createWaiter();
            waiter.setId(Long.parseLong(values.get(0)));
            return new Result<>(waiter);
        });
    }

    public static List<Deliverer> readDeliverers(List<Contract> contracts) {
        return readData("dat/deliverers.txt", 4, (values) -> {
            var contract = contracts.stream().filter(c -> c.getId() == Long.parseLong(values.get(3))).findFirst().orElse(null);

            var deliverer = new DelivererBuilder().setFirstName(values.get(1)).setLastName(values.get(2)).setContract(contract).createDeliverer();
            deliverer.setId(Long.parseLong(values.get(0)));
            return new Result<>(deliverer);
        });
    }

    public static List<Address> readAddresses() {
        return readData("dat/addresses.txt", 5, (values) -> {
            var address = new AddressBuilder().setStreet(values.get(1)).setHouseNumber(values.get(2)).setCity(values.get(3)).setHouseNumber(values.get(4)).createAddress();
            address.setId(Long.parseLong(values.get(0)));
            return new Result<>(address);
        });
    }

    public static List<Meal> readMeals(List<Category> categories, List<Ingredient> ingredients) {
        return readData("dat/meals.txt", 6, (values) -> {
            var name = values.get(1);
            var category = categories.stream().filter(c -> c.getId() == Long.parseLong(values.get(2))).findFirst().orElse(null);
            var pickedIngredients = new HashSet<>(Arrays.stream(values.get(3).split(", ")).map(Long::parseLong).map(id -> ingredients.stream().filter(i -> i.getId() == id).findFirst().orElse(null)).toList());
            var price = new BigDecimal(values.get(4));
            var mealType = MealType.valueOf(values.get(5));

            if (values.size() == 6) {
                return new Result<>(null, switch (mealType) {
                    case Vegan -> 1;
                    case Vegetarian -> 1;
                    case Meat -> 2;
                });
            }

            Meal meal;

            switch (mealType) {
                case Vegan -> {
                    var fiberContentPercentage = new BigDecimal(values.get(6));
                    meal = new VeganMeal(name, category, pickedIngredients, price, fiberContentPercentage);
                }
                case Vegetarian -> {
                    // parse bool
                    var containsDairy = Boolean.parseBoolean(values.get(6));
                    meal = new VegetarianMeal(name, category, pickedIngredients, price, containsDairy);
                }
                case Meat -> {
                    var meatType = values.get(6);
                    var meatCut = values.get(7);
                    meal = new MeatMeal(name, category, pickedIngredients, price, meatType, meatCut);
                }
                // This should never happen
                default -> throw new IllegalArgumentException("Invalid meal type");
            }

            meal.setId(Long.parseLong(values.get(0)));
            return new Result<>(meal);
        });
    }

    public static List<Restaurant> readRestaurants(List<Address> addresses, List<Meal> meals, List<Waiter> waiters, List<Chef> chefs, List<Deliverer> deliverers) {
        return readData("dat/restaurants.txt", 7, (values) -> {
            var address = addresses.stream().filter(a -> a.getId() == Long.parseLong(values.get(2))).findFirst().orElse(null);
            var restMeals = new HashSet<>(Arrays.stream(values.get(3).split(", ")).map(Long::parseLong).map(id -> meals.stream().filter(m -> m.getId() == id).findFirst().orElse(null)).toList());
            var restChefs = new HashSet<>(Arrays.stream(values.get(4).split(", ")).map(Long::parseLong).map(id -> chefs.stream().filter(c -> c.getId() == id).findFirst().orElse(null)).toList());
            var restWaiters = new HashSet<>(Arrays.stream(values.get(5).split(", ")).map(Long::parseLong).map(id -> waiters.stream().filter(w -> w.getId() == id).findFirst().orElse(null)).toList());
            var restDeliverers = new HashSet<>(Arrays.stream(values.get(6).split(", ")).map(Long::parseLong).map(id -> deliverers.stream().filter(d -> d.getId() == id).findFirst().orElse(null)).toList());

            var restaurant = new Restaurant(values.get(1), address, restMeals, restChefs, restWaiters, restDeliverers);
            restaurant.setId(Long.parseLong(values.get(0)));
            return new Result<>(restaurant);
        });
    }

    public static List<Order> readOrders(List<Restaurant> restaurants) {
        return readData("dat/orders.txt", 5, (values) -> {
            var restaurant = restaurants.stream().filter(r -> r.getId() == Long.parseLong(values.get(1))).findFirst().orElse(null);
            var meals = Arrays.stream(values.get(2).split(", ")).map(Long::parseLong).map(id -> restaurant.getMeals().stream().filter(m -> m.getId() == id).findFirst().orElse(null)).toList();
            var deliverer = restaurant.getDeliverers().stream().filter(d -> d.getId() == Long.parseLong(values.get(3))).findFirst().orElse(null);
            var date = LocalDateTime.parse(values.get(4));

            var order = new Order(restaurant, meals, deliverer, date);
            order.setId(Long.parseLong(values.get(0)));
            return new Result<>(order);
        });
    }

    static <T extends Entity> List<T> readData(String path, int propertyCount, Function<ArrayList<String>, Result<T>> createEntity) {
        List<T> entities = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new java.io.FileReader(path))) {
            String line;
            ArrayList<String> values = new ArrayList<>();
            while ((line = br.readLine()) != null) {
                values.clear();
                values.add(line);

                for (int i = 1; i < propertyCount && (line = br.readLine()) != null; i++) {
                    values.add(line);
                }

                if (values.size() != propertyCount) {
                    throw new IOException("Invalid data format");
                }

                var result = createEntity.apply(values);

                if (result.additionalLines != 0) {
                    for (int i = 0; i < result.additionalLines && (line = br.readLine()) != null; i++) {
                        values.add(line);
                    }

                    result = createEntity.apply(values);
                }

                if (result.entity != null) {
                    entities.add(result.entity);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return entities;
    }
}
