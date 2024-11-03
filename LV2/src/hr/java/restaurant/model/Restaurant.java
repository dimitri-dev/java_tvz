package hr.java.restaurant.model;

public class Restaurant extends Entity {
    private String name;
    private Address address;
    private Meal[] meals;
    private Chef[] chefs;
    private Waiter[] waiters;
    private Deliverer[] deliverers;

    public Restaurant(String name, Address address, Meal[] meals, Chef[] chefs, Waiter[] waiters, Deliverer[] deliverers) {
        setName(name);
        setAddress(address);
        setMeals(meals);
        setChefs(chefs);
        setWaiters(waiters);
        setDeliverers(deliverers);
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

        System.out.println(("\t".repeat(tabCount)) + "Name: " + getName());

        System.out.println(("\t".repeat(tabCount)) + "Address: ");
        getAddress().print(tabCount + 1 , false);

        System.out.println(("\t".repeat(tabCount)) + "Meals: ");
        for (Meal meal : meals) {
            meal.print(tabCount + 1, false);
        }

        System.out.println(("\t".repeat(tabCount)) + "Chefs: ");
        for (Chef chef : chefs) {
            chef.print(tabCount + 1, false);
        }

        System.out.println(("\t".repeat(tabCount)) + "Waiters: ");
        for (Waiter waiter : waiters) {
            waiter.print(tabCount + 1, false);
        }

        System.out.println(("\t".repeat(tabCount)) + "Deliverers: ");
        for (Deliverer deliverer : deliverers) {
            deliverer.print(tabCount + 1, false);
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Meal[] getMeals() {
        return meals;
    }

    public void setMeals(Meal[] meals) {
        this.meals = meals;
    }

    public Chef[] getChefs() {
        return chefs;
    }

    public void setChefs(Chef[] chefs) {
        this.chefs = chefs;
    }

    public Waiter[] getWaiters() {
        return waiters;
    }

    public void setWaiters(Waiter[] waiters) {
        this.waiters = waiters;
    }

    public Deliverer[] getDeliverers() {
        return deliverers;
    }

    public void setDeliverers(Deliverer[] deliverers) {
        this.deliverers = deliverers;
    }
}
