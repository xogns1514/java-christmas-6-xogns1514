package christmas.util.valid;

import java.util.regex.Pattern;

public class InputValidator {

    private static final String REGEX = "^[0-9]+$";
    private static final String MENU_QUANTITY_REGEX = "^[^,]+-\\d+(?:,[^,]+-\\d+)*$";


    public static void validateDateInput(String input, String errorMessage) {
        checkInputEmpty(input, errorMessage);
        checkInputIsNumeric(input, errorMessage);
    }

    public static void validateOrderInput(String input, String errorMessage) {
        checkInputEmpty(input, errorMessage);
        checkInputIsValidFormat(input, errorMessage);
    }

    private static void checkInputEmpty(String input, String errorMessage) {
        if (isNullOrEmpty(input)) {
            throw new IllegalArgumentException(errorMessage);
        }
    }

    private static void checkInputIsNumeric(String input, String errorMessage) {
        if (!isNumeric(input)) {
            throw new IllegalArgumentException(errorMessage);
        }
    }

    private static void checkInputIsValidFormat(String input, String errorMessage) {
        if (!isValidOrderFormat(input)) {
            throw new IllegalArgumentException(errorMessage);
        }
    }

    private static boolean isNullOrEmpty(String input) {
        return input == null || input.trim().isEmpty();
    }

    private static boolean isNumeric(String input) {
        return Pattern.matches(REGEX, input);
    }

    private static boolean isValidOrderFormat(String input) {
        return Pattern.matches(MENU_QUANTITY_REGEX, input);
    }
}
