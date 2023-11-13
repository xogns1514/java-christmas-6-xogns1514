package christmas.domain;

public class DiscountCalculator {

    public static int calculateDDayDiscount(Date date) {
        if (date.isDDay()) {
            return 1000 + (date.getDay() - 1) * 100;
        }
        return 0;
    }

    public static int calculateWeekDayDiscount(Date date, Orders orders) {
        if (DayType.WEEKDAY == date.getDayType()) {
            return orders.countDessertMenu() * 2023;
        }
        return 0;
    }

    public static int calculateWeekendDiscount(Date date, Orders orders) {
        if (DayType.WEEKEND == date.getDayType()) {
            return orders.countMainMenu() * 2023;
        }
        return 0;
    }
}
