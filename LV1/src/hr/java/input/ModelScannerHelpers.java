package hr.java.input;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Scanner;

class ModelScannerHelpers {
    static String ReadFirstName(Scanner scanner) {
        return BaseScannerHelpers.ReadString(scanner, "First Name");
    }

    static String ReadLastName(Scanner scanner) {
        return BaseScannerHelpers.ReadString(scanner, "Last Name");
    }

    static BigDecimal ReadSalary(Scanner scanner) {
        return BaseScannerHelpers.ReadBigDecimal(scanner,"Salary");
    }

    static String ReadStreet(Scanner scanner) {
        return BaseScannerHelpers.ReadString(scanner, "Street");
    }

    static String ReadHouseNumber(Scanner scanner) {
        return BaseScannerHelpers.ReadString(scanner, "House Number");
    }

    static String ReadCity(Scanner scanner) {
        return BaseScannerHelpers.ReadString(scanner, "City");
    }

    static String ReadPostalCode(Scanner scanner) {
        return BaseScannerHelpers.ReadString(scanner, "Postal Code");
    }

    static String ReadName(Scanner scanner) {
        return BaseScannerHelpers.ReadString(scanner, "Name");
    }

    static BigDecimal ReadKcal(Scanner scanner) {
        return BaseScannerHelpers.ReadBigDecimal(scanner, "Kcal");
    }

    static String ReadDescription(Scanner scanner) {
        return BaseScannerHelpers.ReadString(scanner, "Description");
    }

    static String ReadPreparationMethod(Scanner scanner) {
        return BaseScannerHelpers.ReadString(scanner, "Preparation Method");
    }

    static BigDecimal ReadPrice(Scanner scanner) {
        return BaseScannerHelpers.ReadBigDecimal(scanner, "Price");
    }

    static LocalDateTime GetCurrentDateTime() {
        return LocalDateTime.now();
    }

    static LocalDateTime ReadDateTime(Scanner scanner) {
        int year = -1;
        while (year < 0) {
            year = BaseScannerHelpers.ReadInt(scanner, "Year");
        }

        int month = -1;
        while (month < 1 || month > 12) {
            month = BaseScannerHelpers.ReadInt(scanner, "Month");
        }

        int day = -1;
        while (day < 1 || day > 31) {
            day = BaseScannerHelpers.ReadInt(scanner, "Day");
        }

        int hour = -1;
        while (hour < 0 || hour > 23) {
            hour = BaseScannerHelpers.ReadInt(scanner, "Hour");
        }

        int minute = -1;
        while (minute < 0 || minute > 59) {
            minute = BaseScannerHelpers.ReadInt(scanner, "Minute");
        }

        return LocalDateTime.of(year, month, day, hour, minute);
    }
}
