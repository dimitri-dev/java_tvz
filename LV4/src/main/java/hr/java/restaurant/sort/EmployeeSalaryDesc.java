package hr.java.restaurant.sort;

import hr.java.restaurant.model.Person;

import java.util.Comparator;

public class EmployeeSalaryDesc<T extends Person> implements Comparator<T> {
    @Override
    public int compare(T o1, T o2) {
        // Person can be Chef, Waiter or Deliverer
        switch(o1.getClass()) {

        }
    }
}
