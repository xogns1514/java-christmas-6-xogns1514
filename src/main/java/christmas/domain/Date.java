package christmas.domain;

import christmas.error.ErrorMessage;

public class Date {

    private final int day;
    private final DayType dayType;
    private final boolean isSpecialDay;

    public Date(int day, DayType dayType, boolean isSpecialDay) {
        this.dayType = dayType;
        this.isSpecialDay = isSpecialDay;
        validateDate(day);

        this.day = day;
    }

    public int getDay() {
        return day;
    }

    public DayType getDayType() {
        return dayType;
    }

    public boolean isSpecialDay() {
        return isSpecialDay;
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
