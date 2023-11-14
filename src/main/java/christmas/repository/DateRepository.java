package christmas.repository;

import christmas.domain.Date;
import christmas.domain.DayType;
import christmas.error.ErrorMessage;
import java.util.ArrayList;
import java.util.List;

public class DateRepository {

    private final List<Date> dates;

    public DateRepository() {
        this.dates = new ArrayList<>();
        initDates();
    }

    public Date findBydate(int day) {
        return dates.stream()
                .filter(date -> date.getDay() == day)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(ErrorMessage.INVALID_DATE_ERROR.getMessage()));
    }

    private void initDates() {
        for (int day = 1; day <= 31; day++) {
            DayType dayType = getDayType(day);
            boolean specialDay = isSpecialDay(day);

            dates.add(new Date(day, dayType, specialDay));
        }
    }

    private DayType getDayType(int day) {
        if (isWeekend(day)) {
            return DayType.WEEKEND;
        }
        return DayType.WEEKDAY;
    }

    private boolean isWeekend(int day) {
        return day % 7 == 2 || day % 7 == 3;
    }

    private boolean isSpecialDay(int day) {
        return day % 7 == 3 || day == 25;
    }
}
