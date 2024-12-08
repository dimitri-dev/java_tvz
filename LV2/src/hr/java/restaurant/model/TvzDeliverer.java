package hr.java.restaurant.model;

public class TvzDeliverer extends Deliverer {
    public static final String delivererType = "TVZ";

    TvzDeliverer(String firstName, String lastName, Contract contract, Bonus bonus) {
        super(firstName, lastName, contract, bonus);
    }

    @Override
    public String getDelivererType() {
        return delivererType;
    }
}
