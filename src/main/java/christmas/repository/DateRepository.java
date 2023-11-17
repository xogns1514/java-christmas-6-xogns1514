package christmas.repository;

import static christmas.util.Constant.*;

import christmas.domain.Day;
import christmas.domain.DayType;
import christmas.error.ErrorMessage;
import java.util.ArrayList;
import java.util.List;

public class DateRepository {

    private final List<Day> days;

    public DateRepository() {
        this.days = new ArrayList<>();
        initDates();
    }

    public Day findBydate(int day) {
        return days.stream()
                .filter(date -> date.getDay() == day)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(ErrorMessage.INVALID_DATE_ERROR.getMessage()));
    }

    private void initDates() {
        for (int day = FIRST_DAY; day <= LAST_DAY; day++) {
            DayType dayType = getDayType(day);
            boolean specialDay = isSpecialDay(day);

            days.add(new Day(day, dayType, specialDay));
        }
    }

    private DayType getDayType(int day) {
        if (isWeekend(day)) {
            return DayType.WEEKEND;
        }
        return DayType.WEEKDAY;
    }

    private boolean isWeekend(int day) {
        return day % DAYS_IN_WEEK == ONE || day % DAYS_IN_WEEK == TWO;
    }

    private boolean isSpecialDay(int day) {
        return day % DAYS_IN_WEEK == THREE || day == CHRISTMAS_DAY;
    }
}
