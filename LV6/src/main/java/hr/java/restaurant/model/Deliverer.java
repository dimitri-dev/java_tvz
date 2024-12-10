package hr.java.restaurant.model;

import java.util.Objects;

public class Deliverer extends Person {
    private Contract contract;
    private Bonus bonus;

    Deliverer(String firstName, String lastName, Contract contract, Bonus bonus) {
        super(firstName, lastName);
        setBonus(bonus);
        setContract(contract);
    }

    public void print() throws Exception {
        print(0);
    }

    public void print(int tabCount) throws Exception {
        print(tabCount, true);
    }

    public void print(int tabCount, boolean newLine) throws Exception {
        if (newLine) {
            System.out.println();
        }

        if (tabCount < 0) {
            throw new Exception("Number of tabs cannot be negative. Something went wrong.");
        }

        super.print(tabCount, false);
        var bonusAmount = getBonus() == null ? 0 : getBonus().amount();
        System.out.println(("\t".repeat(tabCount)) + "Bonus Amount: " + bonusAmount);
        System.out.println(("\t".repeat(tabCount)) + "Contract: ");
        contract.print(tabCount + 1, false);
    }

    public Contract getContract() {
        return contract;
    }

    public void setContract(Contract contract) {
        this.contract = contract;
    }

    public Bonus getBonus() {
        return bonus;
    }

    public void setBonus(Bonus bonus) {
        this.bonus = bonus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Deliverer deliverer = (Deliverer) o;
        return Objects.equals(contract, deliverer.contract) && Objects.equals(bonus, deliverer.bonus);
    }

    @Override
    public int hashCode() {
        return Objects.hash(contract, bonus);
    }
}
