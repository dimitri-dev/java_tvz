package hr.java.restaurant.model;

public class DelivererBuilder {
    private String firstName;
    private String lastName;
    private Contract contract;
    private Bonus bonus;

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

    public Deliverer createDeliverer() {
        return new Deliverer(firstName, lastName, contract, bonus);
    }
}