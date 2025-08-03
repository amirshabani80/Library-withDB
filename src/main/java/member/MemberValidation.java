package member;

public class MemberValidation {
    public static boolean isValidName(String input) {
        if (input == null || input.isEmpty())
            return false;
        return input.matches("[a-zA-Z ]+");
    }

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
