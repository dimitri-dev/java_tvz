package hr.java.restaurant.model;

import java.math.BigDecimal;

public class GlovoDeliverer extends Deliverer {
    public static final String delivererType = "Glovo";

    GlovoDeliverer(String firstName, String lastName, Contract contract, Bonus bonus) {
        super(firstName, lastName, contract, bonus);
    }

    @Override
    public String getDelivererType() {
        return delivererType;
    }

    @Override
    public void increaseSalary(int numberOfOrders) {
        if (numberOfOrders < 0) {
            throw new IllegalArgumentException("Number of orders cannot be negative. Something went wrong.");
        }

        for (int i = 0; i < numberOfOrders; i++) {
            earnings = earnings.add(pricePerDelivery);
            pricePerDelivery = pricePerDelivery.add(BigDecimal.valueOf(0.05));
            deliveredCount++;
        }
    }
}
