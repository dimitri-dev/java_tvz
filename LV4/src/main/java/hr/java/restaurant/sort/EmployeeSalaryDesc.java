package hr.java.restaurant.sort;

import hr.java.restaurant.model.*;

import java.math.BigDecimal;
import java.util.Comparator;

public class EmployeeSalaryDesc implements Comparator<Person> {
    @Override
    public int compare(Person o1, Person o2) {
        var salary1 = salary(o1);
        var salary2 = salary(o2);

        return salary2.compareTo(salary1);
    }

    private BigDecimal salary(Person o1) {
        if (o1 instanceof Chef c) return c.getContract().getSalary();
        if (o1 instanceof Deliverer d) return d.getContract().getSalary();
        if (o1 instanceof Waiter w) return w.getContract().getSalary();

        throw new UnsupportedOperationException("Unknown person type");
    }
}
