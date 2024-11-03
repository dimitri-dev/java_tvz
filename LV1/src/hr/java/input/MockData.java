package hr.java.input;

import hr.java.restaurant.model.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class MockData {
    public static Order[] createOrders() {
        // Add 3 categories to ArrayList
        var category1 = new Category("Desert", "Samo deserti (i suhi)");
        var category2 = new Category("Meso", "Samo mesnati proizvodi");
        var category3 = new Category("Mliječno", "Samo mliječni proizvodi");

        var ingredient1 = new Ingredient("Tjesto", category1, new BigDecimal(220), "Spread it.");
        var ingredient2 = new Ingredient("Želatina od fante", category1, new BigDecimal(200), "Smooth it out.");
        var ingredient3 = new Ingredient("Sir", category3, new BigDecimal(190), "Dice it.");
        var ingredient4 = new Ingredient("Petit keks prekriven čokoladom", category1, new BigDecimal(600), "Sprinkle it.");
        var ingredient5 = new Ingredient("Mljeveno meso", category2, new BigDecimal(400), "Grill it.");

        var meal1 = new Meal("Lambada (torta)", category1, new Ingredient[] {ingredient2, ingredient4}, new BigDecimal(300));
        var meal2 = new Meal("Čevapi u somunu", category2, new Ingredient[] {ingredient1, ingredient5}, new BigDecimal(200));
        var meal3 = new Meal("Sirnica", category3, new Ingredient[] {ingredient1, ingredient3}, new BigDecimal(100));

        var chef1 = new Chef("John", "Smith", new BigDecimal(100000));
        var chef2 = new Chef("Alexandra", "Polly", new BigDecimal(77000));
        var chef3 = new Chef("Pyotr", "Garabushyev", new BigDecimal(169000));

        var waiter1 = new Waiter("Ali", "Mohammud", new BigDecimal(66000));
        var waiter2 = new Waiter("Ilija", "Dostojevsky", new BigDecimal(51100));
        var waiter3 = new Waiter("Marin", "Krešo", new BigDecimal(44100));

        var deliverer1 = new Deliverer("Velimir", "Kućanik", new BigDecimal(54300));
        var deliverer2 = new Deliverer("Igor", "Smiljan", new BigDecimal(41000));
        var deliverer3 = new Deliverer("Qiyanna", "Barbarossa", new BigDecimal(66000));

        var address1 = new Address("Biskvit", "10", "Hladnjača", "30100");
        var restaurant1 = new Restaurant("Umirem od slatkoće", address1,
                new Meal[] {meal1},
                new Chef[] {chef1},
                new Waiter[] {waiter1},
                new Deliverer[] {deliverer1});

        var address2 = new Address("Bascarsija", "1", "Sarajevo", "55000");
        var restaurant2 = new Restaurant("Cevabdzinica broj 1", address2,
                new Meal[] {meal2},
                new Chef[] {chef2},
                new Waiter[] {waiter2},
                new Deliverer[] {deliverer2});

        var address3 = new Address("Bosanska ulica", "91", "Pitomača", "11000");
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
