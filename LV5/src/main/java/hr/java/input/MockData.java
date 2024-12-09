package hr.java.input;

import hr.java.restaurant.enumeration.ContractType;
import hr.java.restaurant.model.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MockData {
    public static List<Person> createStaff() {
        var chef1 = new ChefBuilder().setFirstName("John").setLastName("Smith").setContract(new Contract(new BigDecimal(100000), LocalDate.now(), LocalDate.now().plusYears(1), ContractType.FULL_TIME)).createChef();
        var chef2 = new ChefBuilder().setFirstName("Alexandra").setLastName("Polly").setContract(new Contract(new BigDecimal(77000), LocalDate.now(), LocalDate.now().plusYears(1), ContractType.FULL_TIME)).createChef();
        var chef3 = new ChefBuilder().setFirstName("Pyotr").setLastName("Garabushyev").setContract(new Contract(new BigDecimal(169000), LocalDate.now(), LocalDate.now().plusYears(1), ContractType.FULL_TIME)).createChef();

        var waiter1 = new WaiterBuilder().setFirstName("Ali").setLastName("Mohammud").setContract(new Contract(new BigDecimal(66000), LocalDate.now().minusDays(17), LocalDate.now().plusYears(1), ContractType.PART_TIME)).createWaiter();
        var waiter2 = new WaiterBuilder().setFirstName("Ilija").setLastName("Dostojevsky").setContract(new Contract(new BigDecimal(51000), LocalDate.now().minusMonths(2), LocalDate.now().plusYears(1), ContractType.PART_TIME)).createWaiter();
        var waiter3 = new WaiterBuilder().setFirstName("Marin").setLastName("Krešo").setContract(new Contract(new BigDecimal(44000), LocalDate.now().minusDays(11), LocalDate.now().plusYears(1), ContractType.FULL_TIME)).createWaiter();

        var deliverer1 = new DelivererBuilder().setFirstName("Velimir").setLastName("Kućanik").setContract(new Contract(new BigDecimal(54300), LocalDate.now(), LocalDate.now().plusYears(1), ContractType.FULL_TIME)).createDeliverer();
        var deliverer2 = new DelivererBuilder().setFirstName("Igor").setLastName("Smiljan").setContract(new Contract(new BigDecimal(41000), LocalDate.now(), LocalDate.now().plusYears(1), ContractType.FULL_TIME)).createDeliverer();
        var deliverer3 = new DelivererBuilder().setFirstName("Qiyanna").setLastName("Barbarossa").setContract(new Contract(new BigDecimal(66000), LocalDate.now(), LocalDate.now().plusYears(1), ContractType.FULL_TIME)).createDeliverer();

        return List.of(chef1, chef2, chef3, waiter1, waiter2, waiter3, deliverer1, deliverer2, deliverer3);
    }

    public static List<Meal> createMeals() {
        var category1 = new CategoryBuilder().setName("Desert").setDescription("Samo deserti").createCategory();
        var category2 = new CategoryBuilder().setName("Meso").setDescription("Samo mesnati proizvodi").createCategory();

        var ingredient1 = new Ingredient("Tjesto", category1, new BigDecimal(220), "Spread it.");
        var ingredient5 = new Ingredient("Mljeveno meso", category2, new BigDecimal(400), "Grill it.");

        var veganMeal1 = new VeganMeal("Tofu", category1, Set.of(ingredient1), new BigDecimal(300), new BigDecimal(70));
        var veganMeal2 = new VeganMeal("Soja", category1, Set.of(ingredient1), new BigDecimal(200), new BigDecimal(80));
        var veganMeal3 = new VeganMeal("Seitan", category1, Set.of(ingredient1), new BigDecimal(100), new BigDecimal(90));

        var vegetarianMeal1 = new VegetarianMeal("tt1", category1, Set.of(ingredient1), new BigDecimal(300), false);
        var vegetarianMeal2 = new VegetarianMeal("tt2", category1, Set.of(ingredient1), new BigDecimal(200), false);
        var vegetarianMeal3 = new VegetarianMeal("tt3", category1, Set.of(ingredient1), new BigDecimal(100), false);

        var meatMeal1 = new MeatMeal("Grill pileća prsa", category2, Set.of(ingredient5), new BigDecimal(300), "Piletina", "Prsa");
        var meatMeal2 = new MeatMeal("Svinjski vrat u umaku od gljiva", category2, Set.of(ingredient5), new BigDecimal(200), "Svinjetina", "Vrat");
        var meatMeal3 = new MeatMeal("Janjeći but u tartufima", category2, Set.of(ingredient5), new BigDecimal(100), "Janjetina", "But");

        return List.of(veganMeal1, veganMeal2, veganMeal3, vegetarianMeal1, vegetarianMeal2, vegetarianMeal3, meatMeal1, meatMeal2, meatMeal3);
    }

    public static List<Restaurant> createRestaurants() {
        var meals = createMeals();

        var chef1 = new ChefBuilder().setFirstName("John").setLastName("Smith").setContract(new Contract(new BigDecimal(100000), LocalDate.now(), LocalDate.now().plusYears(1), ContractType.FULL_TIME)).createChef();
        var chef2 = new ChefBuilder().setFirstName("Alexandra").setLastName("Polly").setContract(new Contract(new BigDecimal(77000), LocalDate.now(), LocalDate.now().plusYears(1), ContractType.FULL_TIME)).createChef();
        var chef3 = new ChefBuilder().setFirstName("Pyotr").setLastName("Garabushyev").setContract(new Contract(new BigDecimal(169000), LocalDate.now(), LocalDate.now().plusYears(1), ContractType.FULL_TIME)).createChef();

        var waiter1 = new WaiterBuilder().setFirstName("Ali").setLastName("Mohammud").setContract(new Contract(new BigDecimal(66000), LocalDate.now(), LocalDate.now().plusYears(1), ContractType.PART_TIME)).createWaiter();
        var waiter2 = new WaiterBuilder().setFirstName("Ilija").setLastName("Dostojevsky").setContract(new Contract(new BigDecimal(51000), LocalDate.now(), LocalDate.now().plusYears(1), ContractType.PART_TIME)).createWaiter();
        var waiter3 = new WaiterBuilder().setFirstName("Marin").setLastName("Krešo").setContract(new Contract(new BigDecimal(44000), LocalDate.now(), LocalDate.now().plusYears(1), ContractType.FULL_TIME)).createWaiter();

        var deliverer1 = new DelivererBuilder().setFirstName("Velimir").setLastName("Kućanik").setContract(new Contract(new BigDecimal(54300), LocalDate.now(), LocalDate.now().plusYears(1), ContractType.FULL_TIME)).createDeliverer();
        var deliverer2 = new DelivererBuilder().setFirstName("Igor").setLastName("Smiljan").setContract(new Contract(new BigDecimal(41000), LocalDate.now(), LocalDate.now().plusYears(1), ContractType.FULL_TIME)).createDeliverer();
        var deliverer3 = new DelivererBuilder().setFirstName("Qiyanna").setLastName("Barbarossa").setContract(new Contract(new BigDecimal(66000), LocalDate.now(), LocalDate.now().plusYears(1), ContractType.FULL_TIME)).createDeliverer();

        var address1 = new AddressBuilder().setStreet("Biskvit").setHouseNumber("10").setCity("Hladnjača").setPostalCode("30100").createAddress();
        var restaurant1 = new Restaurant("Umirem od slatkoće", address1,
                new HashSet<>(meals.subList(0, 6)),
                Set.of(chef1),
                Set.of(waiter1),
                Set.of(deliverer1));

        var address2 = new AddressBuilder().setStreet("Bascarsija").setHouseNumber("1").setCity("Sarajevo").setPostalCode("55000").createAddress();
        var restaurant2 = new Restaurant("Cevabdzinica broj 1", address2,
                new HashSet<>(meals.subList(1, 7)),
                Set.of(chef2),
                Set.of(waiter2),
                Set.of(deliverer2));

        var address3 = new AddressBuilder().setStreet("Bosanska ulica").setHouseNumber("91").setCity("Pitomača").setPostalCode("11000").createAddress();
        var restaurant3 = new Restaurant("Bosanske pite", address3,
                new HashSet<>(meals.subList(2, 8)),
                Set.of(chef3),
                Set.of(waiter3),
                Set.of(deliverer3));

        return List.of(restaurant1, restaurant2, restaurant3);
    }

    public static List<Order> createOrders(List<Restaurant> restaurants) {
        var order1 = new Order(restaurants.get(0), new ArrayList<>(restaurants.get(0).getMeals()), restaurants.get(0).getDeliverers().stream().toList().getFirst(), LocalDateTime.now());
        var order2 = new Order(restaurants.get(1), new ArrayList<>(restaurants.get(1).getMeals()), restaurants.get(1).getDeliverers().stream().toList().getFirst(), LocalDateTime.now());
        var order3 = new Order(restaurants.get(2), new ArrayList<>(restaurants.get(2).getMeals()), restaurants.get(2).getDeliverers().stream().toList().getFirst(), LocalDateTime.now());

        return List.of(order1, order2, order3);
    }
}
