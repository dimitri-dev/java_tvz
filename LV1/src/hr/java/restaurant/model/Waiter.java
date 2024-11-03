package hr.java.restaurant.model;

import java.math.BigDecimal;

public class Waiter {
    private String firstName;
    private String lastName;
    private BigDecimal salary;

    public Waiter(String firstName, String lastName, BigDecimal salary) {
        setFirstName(firstName);
        setLastName(lastName);
        setSalary(salary);
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
            throw new Exception("sta ne valja sa tobom");
        }

        System.out.println(("\t".repeat(tabCount)) + "First Name: " + getFirstName());
        System.out.println(("\t".repeat(tabCount)) + "Last Name: " + getLastName());
        System.out.println(("\t".repeat(tabCount)) + "Salary: " + getSalary());
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }
}
