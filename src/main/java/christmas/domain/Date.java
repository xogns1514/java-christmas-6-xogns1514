package christmas.domain;

import christmas.error.ErrorMessage;

public class Date {

    private final int date;

    public Date(int date) {
        validateDate(date);

        this.date = date;
    }

    private void validateDate(int date) {
        checkValidDate(date);
    }

    private void checkValidDate(int date) {
        if (isNotValidDate(date)) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_DATE_ERROR.getMessage());
        }
    }

    private boolean isNotValidDate(int date) {
        return date < 1 || date > 31;
    }
}
