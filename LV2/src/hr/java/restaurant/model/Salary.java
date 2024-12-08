package hr.java.restaurant.model;

import java.math.BigDecimal;

public interface Salary {
    BigDecimal getSalary();

    void increaseSalary(int numberOfOrders);
}
