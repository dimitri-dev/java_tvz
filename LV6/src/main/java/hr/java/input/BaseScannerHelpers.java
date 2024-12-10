package hr.java.input;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;

// Internal class. Not visible outside of this package.
class BaseScannerHelpers {
    private static final Logger logger = LoggerFactory.getLogger(BaseScannerHelpers.class);

    public static String ReadString(Scanner scanner, String propName) {
        logger.info("Reading string property: {}", propName);
        System.out.print("Enter " + propName + ": ");

        var value = scanner.nextLine();
        logger.info("Value obtained: {}", value);

        return value;
    }

    public static Integer ReadInt(Scanner scanner, String propName) {
        logger.info("Reading integer property: " + propName);
        System.out.print("Enter " + propName + ": ");

        var value = scanner.nextInt();
        scanner.nextLine();

        logger.info("Value obtained: {}", value);

        return value;
    }

    public static BigDecimal ReadBigDecimal(Scanner scanner, String propName) {
        logger.info("Reading big decimal property: " + propName);
        System.out.print("Enter " + propName + ": ");

        var value = scanner.nextBigDecimal();
        scanner.nextLine();

        logger.info("Value obtained: {}", value);

        return value;
    }

    public static Integer ReadInt(Scanner scanner, String propName, Map<Integer, String> acceptedValues) {
        return Arrays.stream(ReadInts(scanner, propName, 1, acceptedValues)).findFirst().orElseThrow();
    }

    public static Integer[] ReadInts(Scanner scanner, String propName, Map<Integer, String> acceptedValues) {
        var n = ReadInt(scanner, "Amount of " + propName + "s");

        return ReadInts(scanner, propName, n, acceptedValues);
    }

    public static Integer[] ReadInts(Scanner scanner, String propName, int n, Map<Integer, String> acceptedValues) {
        Integer[] arr = new Integer[n];

        var strValues = Arrays.toString(
                acceptedValues.entrySet().stream()
                        .map(entry -> "{" + entry.getKey() + ": " + entry.getValue() + "}")
                        .toArray());

        System.out.println("Please enter one of the following values: " + strValues);

        for (int i = 0; i < n; ++i) {
            var value = ReadInt(scanner, propName);

            try {
                // I would never do it like this, but this is a requirement.
                if (!acceptedValues.containsKey(value)) {
                    throw new InputMismatchException("Value not accepted.");
                }
                arr[i] = value;
            } catch (InputMismatchException e) {
                logger.error("Invalid value [{}] for property [{}]. Valid values: [{}]", value, propName, strValues);
                System.out.println("Invalid value. Please enter one of the following values: " + strValues);
                scanner.nextLine();
                --i;
            }
        }

        return arr;
    }
}
