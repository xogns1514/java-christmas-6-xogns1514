package christmas.domain;

import static christmas.util.Constant.*;

import christmas.error.ErrorMessage;

public class Day {

    private final int day;
    private final DayType dayType;
    private final boolean isSpecialDay;

    public Day(int day, DayType dayType, boolean isSpecialDay) {
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

    public boolean isDDay() {
        return FIRST_DAY <= day && day <= CHRISTMAS_DAY;
    }

    private void validateDate(int date) {
        checkValidDay(date);
    }

    private void checkValidDay(int date) {
        if (isNotValidDay(date)) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_DATE_ERROR.getMessage());
        }
    }

    private boolean isNotValidDay(int date) {
        return date < FIRST_DAY || date > LAST_DAY;
    }
}
