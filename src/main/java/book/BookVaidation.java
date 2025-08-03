package book;

public class BookVaidation {
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
}
