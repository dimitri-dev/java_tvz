package hr.java.input;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Map;
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

    static BigDecimal ReadFiberContentPercentage(Scanner scanner) {
        return BaseScannerHelpers.ReadBigDecimal(scanner, "Fiber Content Percentage (%)");
    }

    static String ReadMeatType(Scanner scanner) {
        return BaseScannerHelpers.ReadString(scanner, "Meat Type");
    }

    static String ReadMeatCut(Scanner scanner) {
        return BaseScannerHelpers.ReadString(scanner, "Meat Cut");
    }

    static BigDecimal ReadAmount(Scanner scanner) {
        return BaseScannerHelpers.ReadBigDecimal(scanner, "Amount");
    }

    static Boolean ReadContainsDairy(Scanner scanner) {
        return BaseScannerHelpers.ReadInt(scanner, "Contains Dairy", Map.of(1, "Yes",2, "No")) == 1;
    }

    static Boolean ReadTrackable(Scanner scanner) {
        return BaseScannerHelpers.ReadInt(scanner, "Is Trackable", Map.of(1, "Yes",2, "No")) == 1;
    }

    static LocalDate GetCurrentDate() {
        return LocalDate.now();
    }

    static LocalDateTime GetCurrentDateTime() {
        return LocalDateTime.now();
    }

    static LocalDate ReadDate(Scanner scanner) {
        int year = -1;
        while (year < 0) {
            year = BaseScannerHelpers.ReadInt(scanner, "Year (>= 0)");
        }

        int month = -1;
        while (month < 1 || month > 12) {
            month = BaseScannerHelpers.ReadInt(scanner, "Month (1-12)");
        }

        int day = -1;
        while (day < 1 || day > 31) {
            day = BaseScannerHelpers.ReadInt(scanner, "Day (1-31)");
        }

        return LocalDate.of(year, month, day);
    }

    static LocalDateTime ReadDateTime(Scanner scanner) {
        var localDate = ReadDate(scanner);

        int hour = -1;
        while (hour < 0 || hour > 23) {
            hour = BaseScannerHelpers.ReadInt(scanner, "Hour (0-23)");
        }

        int minute = -1;
        while (minute < 0 || minute > 59) {
            minute = BaseScannerHelpers.ReadInt(scanner, "Minute (0-59)");
        }

        return localDate.atTime(hour, minute);
    }
}
