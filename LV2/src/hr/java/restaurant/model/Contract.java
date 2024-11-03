package hr.java.restaurant.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Map;

public class Contract extends Entity {
    private BigDecimal salary;
    private LocalDate startDate;
    private LocalDate endDate;
    private int contractType;

    public static class ContractConstants {
        public static final Map<Integer, String> values = Map.of(
                1, "Full time",
                2, "Part time"
        );

        public static final Integer FULL_TIME = 1;
        public static final Integer PART_TIME = 2;
    }

    public Contract(BigDecimal salary, LocalDate startDate, LocalDate endDate, int contractType) {
        setSalary(salary);
        setStartDate(startDate);
        setEndDate(endDate);
        setContractType(contractType);
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

        System.out.println(("\t".repeat(tabCount)) + "Salary: " + getSalary());
        System.out.println(("\t".repeat(tabCount)) + "Start date: " + getStartDate());
        System.out.println(("\t".repeat(tabCount)) + "End date: " + getEndDate());
        System.out.println(("\t".repeat(tabCount)) + "Contract type: " + getContractType());
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    // Requires that start date is set before setting end date
    public void setEndDate(LocalDate endDate) {
        if (endDate.isBefore(startDate)) {
            throw new IllegalArgumentException("End date cannot be before start date. Something went wrong.");
        }

        this.endDate = endDate;
    }

    public String getContractType() {
        return ContractConstants.values.get(contractType);
    }

    public void setContractType(int contractType) {
        if (!ContractConstants.values.containsKey(contractType)) {
            throw new IllegalArgumentException("Invalid contract type. Something went wrong.");
        }

        this.contractType = contractType;
    }

}

