package hr.java.input;

import hr.java.restaurant.exception.BogusEntryException;
import hr.java.restaurant.exception.DuplicateEntryException;
import hr.java.restaurant.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

import static hr.java.input.ModelScannerChoice.ChooseContractType;
import static hr.java.input.ModelScannerHelpers.*;

public class PersonModelCreator {
    private static final Logger logger = LoggerFactory.getLogger(PersonModelCreator.class);

    public static Contract CreateContract(Scanner scanner) {
        logger.info("Started creating a contract");
        try {
            System.out.println("Input Contract information");
            var salary = ReadSalary(scanner);

            if (salary.intValue() <= 10000 || salary.intValue() > 1000000) {
                throw new BogusEntryException("Salary must be a positive number bigger than 10,000 and smaller than or equal to 1,000,000.");
            }

            var startDate = GetCurrentDate(); // ReadDate(scanner);
            var endDate = GetCurrentDate().plusYears(1); // ReadDate(scanner);
            var contractType = ChooseContractType(scanner);

            logger.info("Contract created successfully");
            return new Contract(salary, startDate, endDate, contractType);
        } catch (BogusEntryException e) {
            logger.error(e.getMessage());
            System.out.println(e.getMessage());
            System.out.println("Please try again.");

            return CreateContract(scanner);
        }
    }

    public static Bonus CreateBonus(Scanner scanner) {
        logger.info("Started creating a bonus");

        try {
            System.out.println("Input Bonus information");
            System.out.println("Set amount to 0 if no bonus.");
            var bonus = ReadAmount(scanner);

            if (bonus.intValue() < 0) {
                throw new BogusEntryException("Bonus must be a positive number.");
            }

            logger.info("Bonus created successfully");
            return new Bonus(bonus);
        } catch (BogusEntryException e) {
            logger.error(e.getMessage());
            System.out.println(e.getMessage());
            System.out.println("Please try again.");

            return CreateBonus(scanner);
        }
    }

    public static Chef CreateChef(Scanner scanner, HashSet<Person> existingPeople) {
        logger.info("Started creating a chef");
        try
        {
            System.out.println("Input Chef information");

            var firstName = ReadFirstName(scanner);
            var lastName = ReadLastName(scanner);

            if (existingPeople.stream().anyMatch(person ->
                    person.getFirstName().equals(firstName) &&
                            person.getLastName().equals(lastName)))
            {
                throw new DuplicateEntryException("A person with the same first and last name already exists.");
            }

            var contract = CreateContract(scanner);
            var bonus = CreateBonus(scanner);

            logger.info("Chef created successfully");
            return new ChefBuilder().setFirstName(firstName).setLastName(lastName).setContract(contract).setBonus(bonus).createChef();
        }
        catch (DuplicateEntryException e)
        {
            logger.error(e.getMessage());
            System.out.println(e.getMessage());
            System.out.println("Please try again.");
            return CreateChef(scanner, existingPeople);
        }
    }

    public static Deliverer CreateDeliverer(Scanner scanner, HashSet<Person> existingPeople) {
        logger.info("Started creating a deliverer");
        try
        {
            System.out.println("Input Deliverer information");
            var firstName = ReadFirstName(scanner);
            var lastName = ReadLastName(scanner);

            if (existingPeople.stream().anyMatch(person ->
                    person.getFirstName().equals(firstName) &&
                            person.getLastName().equals(lastName)))
            {
                throw new DuplicateEntryException("A person with the same first and last name already exists.");
            }

            var contract = CreateContract(scanner);
            var bonus = CreateBonus(scanner);

            logger.info("Deliverer created successfully");
            return new DelivererBuilder().setFirstName(firstName).setLastName(lastName).setContract(contract).setBonus(bonus).createDeliverer();
        }
        catch (DuplicateEntryException e)
        {
            logger.error(e.getMessage());
            System.out.println(e.getMessage());
            System.out.println("Please try again.");
            return CreateDeliverer(scanner, existingPeople);
        }
    }

    public static Waiter CreateWaiter(Scanner scanner, HashSet<Person> existingPeople) {
        logger.info("Started creating a waiter");
        try
        {
            System.out.println("Input Waiter information");
            var firstName = ReadFirstName(scanner);
            var lastName = ReadLastName(scanner);

            if (existingPeople.stream().anyMatch(person ->
                    person.getFirstName().equals(firstName) &&
                            person.getLastName().equals(lastName)))
            {
                throw new DuplicateEntryException("A person with the same first and last name already exists.");
            }

            var contract = CreateContract(scanner);
            var bonus = CreateBonus(scanner);

            logger.info("Waiter created successfully");
            return new WaiterBuilder().setFirstName(firstName).setLastName(lastName).setContract(contract).setBonus(bonus).createWaiter();
        }
        catch (DuplicateEntryException e)
        {
            logger.error(e.getMessage());
            System.out.println(e.getMessage());
            System.out.println("Please try again.");
            return CreateWaiter(scanner, existingPeople);
        }
    }
}
