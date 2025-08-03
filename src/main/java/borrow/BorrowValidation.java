package borrow;

public class BorrowValidation {
    public static boolean isValidNumber(String input) {
        if (input == null || input.isEmpty())
            return false;
        return input.matches("\\d+");
    }

    public static boolean isValidDate(String input) {
        if (input == null || input.isEmpty())
            return false;
        try {
            java.time.LocalDate.parse(input);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
