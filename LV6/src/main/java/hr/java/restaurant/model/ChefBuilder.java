package hr.java.restaurant.model;

public class ChefBuilder {
    private String firstName;
    private String lastName;
    private Contract contract;
    private Bonus bonus;

    public ChefBuilder setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public ChefBuilder setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public ChefBuilder setContract(Contract contract) {
        this.contract = contract;
        return this;
    }

    public ChefBuilder setBonus(Bonus bonus) {
        this.bonus = bonus;
        return this;
    }

    public Chef createChef() {
        return new Chef(firstName, lastName, contract, bonus);
    }
}