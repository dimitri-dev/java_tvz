package hr.java.restaurant.model;

public class DelivererBuilder {
    private String firstName;
    private String lastName;
    private Contract contract;
    private Bonus bonus;
    private String type;

    public DelivererBuilder setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public DelivererBuilder setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public DelivererBuilder setContract(Contract contract) {
        this.contract = contract;
        return this;
    }

    public DelivererBuilder setBonus(Bonus bonus) {
        this.bonus = bonus;
        return this;
    }

    public DelivererBuilder setType(String type) {
        this.type = type;
        return this;
    }

    public Deliverer createDeliverer() {
        switch (type) {
            case GlovoDeliverer.delivererType -> {
                return new GlovoDeliverer(firstName, lastName, contract, bonus);
            }
            case WoltDeliverer.delivererType -> {
                return new WoltDeliverer(firstName, lastName, contract, bonus);
            }
            case TvzDeliverer.delivererType -> {
                return new TvzDeliverer(firstName, lastName, contract, bonus);
            }
            default -> throw new IllegalArgumentException("Invalid deliverer type: " + type);
        }
    }
}