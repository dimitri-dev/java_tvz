package hr.java.input;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Map;
import java.util.Scanner;

// Internal class. Not visible outside of this package.
class BaseScannerHelpers {
    public static String ReadString(Scanner scanner, String propName) {
        System.out.print("Enter " + propName + ": ");

        return scanner.nextLine();
    }

    public static Integer ReadInt(Scanner scanner, String propName) {
        System.out.print("Enter " + propName + ": ");

        var value = scanner.nextInt();
        scanner.nextLine();

        return value;
    }

    public static BigDecimal ReadBigDecimal(Scanner scanner, String propName) {
        System.out.print("Enter " + propName + ": ");

        var value = scanner.nextBigDecimal();
        scanner.nextLine();

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

            if (acceptedValues.containsKey(value)) {
                arr[i] = value;
                continue;
            }

            System.out.println("Invalid value. Please enter one of the following values: " + strValues);
            --i;
        }

        return arr;
    }
}
