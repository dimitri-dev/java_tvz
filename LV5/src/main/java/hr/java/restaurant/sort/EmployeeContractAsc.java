package hr.java.restaurant.sort;

import hr.java.restaurant.model.*;

import java.time.temporal.ChronoUnit;
import java.util.Comparator;

public class EmployeeContractAsc implements Comparator<Person> {
    @Override
    public int compare(Person o1, Person o2) {
        var contract1 = contract(o1);
        var contract2 = contract(o2);

        return Long.compare(contract2, contract1);
    }

    private long contract(Person o1) {
        if (o1 instanceof Chef c) return ChronoUnit.DAYS.between(c.getContract().getEndDate(), c.getContract().getStartDate());
        if (o1 instanceof Deliverer d) return ChronoUnit.DAYS.between(d.getContract().getEndDate(), d.getContract().getStartDate());
        if (o1 instanceof Waiter w) return ChronoUnit.DAYS.between(w.getContract().getEndDate(), w.getContract().getStartDate());

        throw new UnsupportedOperationException("Unknown person type");
    }
}
