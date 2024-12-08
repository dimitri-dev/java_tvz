package hr.java.restaurant.model;

import java.math.BigDecimal;

public abstract class Deliverer extends Person implements Salary {
    private Contract contract;
    private Bonus bonus;
    protected int deliveredCount;
    protected BigDecimal pricePerDelivery;
    protected BigDecimal earnings;

    Deliverer(String firstName, String lastName, Contract contract, Bonus bonus) {
        super(firstName, lastName);
        setBonus(bonus);
        setContract(contract);
        deliveredCount = 0;
        earnings = BigDecimal.ZERO;
        pricePerDelivery = BigDecimal.ONE; // every deliverer will get 1 unit of currency per delivery
    }

    public int getDeliveredCount() {
        return deliveredCount;
    }

    public BigDecimal getSalary() {
        // Let's be honest - this doesn't make sense since this work isn't on a contractual/monthly salary basis
        // but rather on a per-delivery basis. But I guess this makes sense enough to fulfill the requirements?
        var bonusAmount = getBonus() == null ? BigDecimal.ZERO : getBonus().amount();
        return contract.getSalary().add(bonusAmount).add(earnings);
    }

    public void increaseSalary(int numberOfOrders) {
        if (numberOfOrders < 0) {
            throw new IllegalArgumentException("Number of orders cannot be negative. Something went wrong.");
        }

        for (int i = 0; i < numberOfOrders; i++) {
            earnings = earnings.add(pricePerDelivery);
            pricePerDelivery = pricePerDelivery.add(BigDecimal.valueOf(0.01));
            deliveredCount++;
        }
    }

    public abstract String getDelivererType();

    public void increaseDeliveredCount() {
        increaseSalary(1);
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
}
