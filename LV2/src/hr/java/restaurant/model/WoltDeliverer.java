package hr.java.restaurant.model;

public class WoltDeliverer extends Deliverer {
    public static final String delivererType = "Wolt";

    WoltDeliverer(String firstName, String lastName, Contract contract, Bonus bonus) {
        super(firstName, lastName, contract, bonus);
    }

    @Override
    public String getDelivererType() {
        return delivererType;
    }
}
