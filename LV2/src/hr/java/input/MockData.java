package hr.java.input;

import hr.java.restaurant.model.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class MockData {
    public static Person[] createStaff() {
        var chef1 = new ChefBuilder().setFirstName("John").setLastName("Smith").setContract(new Contract(new BigDecimal(100000), LocalDate.now(), LocalDate.now().plusYears(1), Contract.ContractConstants.FULL_TIME)).createChef();
        var chef2 = new ChefBuilder().setFirstName("Alexandra").setLastName("Polly").setContract(new Contract(new BigDecimal(77000), LocalDate.now(), LocalDate.now().plusYears(1), Contract.ContractConstants.FULL_TIME)).createChef();
        var chef3 = new ChefBuilder().setFirstName("Pyotr").setLastName("Garabushyev").setContract(new Contract(new BigDecimal(169000), LocalDate.now(), LocalDate.now().plusYears(1), Contract.ContractConstants.FULL_TIME)).createChef();

        var waiter1 = new WaiterBuilder().setFirstName("Ali").setLastName("Mohammud").setContract(new Contract(new BigDecimal(66000), LocalDate.now(), LocalDate.now().plusYears(1), Contract.ContractConstants.PART_TIME)).createWaiter();
        var waiter2 = new WaiterBuilder().setFirstName("Ilija").setLastName("Dostojevsky").setContract(new Contract(new BigDecimal(51000), LocalDate.now(), LocalDate.now().plusYears(1), Contract.ContractConstants.PART_TIME)).createWaiter();
        var waiter3 = new WaiterBuilder().setFirstName("Marin").setLastName("Krešo").setContract(new Contract(new BigDecimal(44000), LocalDate.now(), LocalDate.now().plusYears(1), Contract.ContractConstants.FULL_TIME)).createWaiter();

        var deliverer1 = new DelivererBuilder().setFirstName("Velimir").setLastName("Kućanik").setContract(new Contract(new BigDecimal(54300), LocalDate.now(), LocalDate.now().plusYears(1), Contract.ContractConstants.FULL_TIME)).createDeliverer();
        var deliverer2 = new DelivererBuilder().setFirstName("Igor").setLastName("Smiljan").setContract(new Contract(new BigDecimal(41000), LocalDate.now(), LocalDate.now().plusYears(1), Contract.ContractConstants.FULL_TIME)).createDeliverer();
        var deliverer3 = new DelivererBuilder().setFirstName("Qiyanna").setLastName("Barbarossa").setContract(new Contract(new BigDecimal(66000), LocalDate.now(), LocalDate.now().plusYears(1), Contract.ContractConstants.FULL_TIME)).createDeliverer();

        return new Person[] { chef1, chef2, chef3, waiter1, waiter2, waiter3, deliverer1, deliverer2, deliverer3 };
    }

    public static Meal[] createMeals() {
        var category1 = new CategoryBuilder().setName("Desert").setDescription("Samo deserti").createCategory();
        var category2 = new CategoryBuilder().setName("Meso").setDescription("Samo mesnati proizvodi").createCategory();
        var category3 = new CategoryBuilder().setName("Mliječno").setDescription("Samo mliječni proizvodi").createCategory();

        var ingredient1 = new Ingredient("Tjesto", category1, new BigDecimal(220), "Spread it.");
        var ingredient2 = new Ingredient("Želatina od fante", category1, new BigDecimal(200), "Smooth it out.");
        var ingredient3 = new Ingredient("Sir", category3, new BigDecimal(190), "Dice it.");
        var ingredient4 = new Ingredient("Petit keks prekriven čokoladom", category1, new BigDecimal(600), "Sprinkle it.");
        var ingredient5 = new Ingredient("Mljeveno meso", category2, new BigDecimal(400), "Grill it.");

        var veganMeal1 = new VeganMeal("Tofu", category1, new Ingredient[] {ingredient1}, new BigDecimal(300), new BigDecimal(70));
        var veganMeal2 = new VeganMeal("Soja", category1, new Ingredient[] {ingredient1}, new BigDecimal(200), new BigDecimal(80));
        var veganMeal3 = new VeganMeal("Seitan", category1, new Ingredient[] {ingredient1}, new BigDecimal(100), new BigDecimal(90));

        var vegetarianMeal1 = new VegetarianMeal("tt1", category1, new Ingredient[] {ingredient1}, new BigDecimal(300), false);
        var vegetarianMeal2 = new VegetarianMeal("tt2", category1, new Ingredient[] {ingredient1}, new BigDecimal(200), false);
        var vegetarianMeal3 = new VegetarianMeal("tt3", category1, new Ingredient[] {ingredient1}, new BigDecimal(100), false);

        var meatMeal1 = new MeatMeal("Grill pileća prsa", category2, new Ingredient[] {ingredient5}, new BigDecimal(300), "Piletina", "Prsa");
        var meatMeal2 = new MeatMeal("Svinjski vrat u umaku od gljiva", category2, new Ingredient[] {ingredient5}, new BigDecimal(200), "Svinjetina", "Vrat");
        var meatMeal3 = new MeatMeal("Janjeći but u tartufima", category2, new Ingredient[] {ingredient5}, new BigDecimal(100), "Janjetina", "But");

        return new Meal[] { veganMeal1, veganMeal2, veganMeal3, vegetarianMeal1, vegetarianMeal2, vegetarianMeal3, meatMeal1, meatMeal2, meatMeal3 };
    }

    public static Order[] createOrders() {
        // Add 3 categories to ArrayList
        var category1 = new CategoryBuilder().setName("Desert").setDescription("Samo deserti (i suhi)").createCategory();
        var category2 = new CategoryBuilder().setName("Meso").setDescription("Samo mesnati proizvodi").createCategory();
        var category3 = new CategoryBuilder().setName("Mliječno").setDescription("Samo mliječni proizvodi").createCategory();

        var ingredient1 = new Ingredient("Tjesto", category1, new BigDecimal(220), "Spread it.");
        var ingredient2 = new Ingredient("Želatina od fante", category1, new BigDecimal(200), "Smooth it out.");
        var ingredient3 = new Ingredient("Sir", category3, new BigDecimal(190), "Dice it.");
        var ingredient4 = new Ingredient("Petit keks prekriven čokoladom", category1, new BigDecimal(600), "Sprinkle it.");
        var ingredient5 = new Ingredient("Mljeveno meso", category2, new BigDecimal(400), "Grill it.");

        var meal1 = new Meal("Lambada (torta)", category1, new Ingredient[] {ingredient2, ingredient4}, new BigDecimal(300));
        var meal2 = new Meal("Čevapi u somunu", category2, new Ingredient[] {ingredient1, ingredient5}, new BigDecimal(200));
        var meal3 = new Meal("Sirnica", category3, new Ingredient[] {ingredient1, ingredient3}, new BigDecimal(100));

        var chef1 = new ChefBuilder().setFirstName("John").setLastName("Smith").setContract(new Contract(new BigDecimal(100000), LocalDate.now(), LocalDate.now().plusYears(1), Contract.ContractConstants.FULL_TIME)).createChef();
        var chef2 = new ChefBuilder().setFirstName("Alexandra").setLastName("Polly").setContract(new Contract(new BigDecimal(77000), LocalDate.now(), LocalDate.now().plusYears(1), Contract.ContractConstants.FULL_TIME)).createChef();
        var chef3 = new ChefBuilder().setFirstName("Pyotr").setLastName("Garabushyev").setContract(new Contract(new BigDecimal(169000), LocalDate.now(), LocalDate.now().plusYears(1), Contract.ContractConstants.FULL_TIME)).createChef();

        var waiter1 = new WaiterBuilder().setFirstName("Ali").setLastName("Mohammud").setContract(new Contract(new BigDecimal(66000), LocalDate.now(), LocalDate.now().plusYears(1), Contract.ContractConstants.PART_TIME)).createWaiter();
        var waiter2 = new WaiterBuilder().setFirstName("Ilija").setLastName("Dostojevsky").setContract(new Contract(new BigDecimal(51000), LocalDate.now(), LocalDate.now().plusYears(1), Contract.ContractConstants.PART_TIME)).createWaiter();
        var waiter3 = new WaiterBuilder().setFirstName("Marin").setLastName("Krešo").setContract(new Contract(new BigDecimal(44000), LocalDate.now(), LocalDate.now().plusYears(1), Contract.ContractConstants.FULL_TIME)).createWaiter();

        var deliverer1 = new DelivererBuilder().setFirstName("Velimir").setLastName("Kućanik").setContract(new Contract(new BigDecimal(54300), LocalDate.now(), LocalDate.now().plusYears(1), Contract.ContractConstants.FULL_TIME)).createDeliverer();
        var deliverer2 = new DelivererBuilder().setFirstName("Igor").setLastName("Smiljan").setContract(new Contract(new BigDecimal(41000), LocalDate.now(), LocalDate.now().plusYears(1), Contract.ContractConstants.FULL_TIME)).createDeliverer();
        var deliverer3 = new DelivererBuilder().setFirstName("Qiyanna").setLastName("Barbarossa").setContract(new Contract(new BigDecimal(66000), LocalDate.now(), LocalDate.now().plusYears(1), Contract.ContractConstants.FULL_TIME)).createDeliverer();

        var address1 = new AddressBuilder().setStreet("Biskvit").setHouseNumber("10").setCity("Hladnjača").setPostalCode("30100").createAddress();
        var restaurant1 = new Restaurant("Umirem od slatkoće", address1,
                new Meal[] {meal1},
                new Chef[] {chef1},
                new Waiter[] {waiter1},
                new Deliverer[] {deliverer1});

        var address2 = new AddressBuilder().setStreet("Bascarsija").setHouseNumber("1").setCity("Sarajevo").setPostalCode("55000").createAddress();
        var restaurant2 = new Restaurant("Cevabdzinica broj 1", address2,
                new Meal[] {meal2},
                new Chef[] {chef2},
                new Waiter[] {waiter2},
                new Deliverer[] {deliverer2});

        var address3 = new AddressBuilder().setStreet("Bosanska ulica").setHouseNumber("91").setCity("Pitomača").setPostalCode("11000").createAddress();
        var restaurant3 = new Restaurant("Bosanske pite", address3,
                new Meal[] {meal3},
                new Chef[] {chef3},
                new Waiter[] {waiter3},
                new Deliverer[] {deliverer3});

        var order1 = new Order(restaurant1, new Meal[] {meal1, meal1}, deliverer1, LocalDateTime.now());
        var order2 = new Order(restaurant2, new Meal[] {meal2, meal2, meal2, meal2}, deliverer2, LocalDateTime.now());
        var order3 = new Order(restaurant3, new Meal[] {meal3, meal3, meal3, meal3, meal3}, deliverer3, LocalDateTime.now());

        return new Order[] { order1, order2, order3 };
    }
}
