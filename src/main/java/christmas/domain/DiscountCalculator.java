package christmas.domain;

public class DiscountCalculator {

    public static int calculateTotalDiscount(Date date, Orders orders) {
        return calculateDDayDiscount(date, orders)
                + calculateWeekDayDiscount(date, orders)
                + calculateWeekendDiscount(date, orders)
                + calculateSpecialDayDiscount(date, orders)
                + calculateFreeGift(orders);
    }

    public static int calculateTotalPriceAfterDiscount(Date date, Orders orders) {
        int totalPriceBeforeDiscount = orders.calculateTotalPrice();

        return totalPriceBeforeDiscount
                - calculateDDayDiscount(date, orders)
                - calculateWeekendDiscount(date, orders)
                - calculateWeekDayDiscount(date, orders)
                - calculateSpecialDayDiscount(date, orders);
    }

    public static int calculateDDayDiscount(Date date, Orders orders) {
        if (date.isDDay() && orders.isTotalPriceQualifiedForEvent()) {
            return 1000 + (date.getDay() - 1) * 100;
        }
        return 0;
    }

    public static int calculateWeekDayDiscount(Date date, Orders orders) {
        if (DayType.WEEKDAY == date.getDayType() && orders.isTotalPriceQualifiedForEvent()) {
            return orders.countDessertMenu() * 2023;
        }
        return 0;
    }

    public static int calculateWeekendDiscount(Date date, Orders orders) {
        if (DayType.WEEKEND == date.getDayType() && orders.isTotalPriceQualifiedForEvent()) {
            return orders.countMainMenu() * 2023;
        }
        return 0;
    }

    public static int calculateSpecialDayDiscount(Date date, Orders orders) {
        if (date.isSpecialDay() && orders.isTotalPriceQualifiedForEvent()) {
            return 1000;
        }
        return 0;
    }

    public static int calculateFreeGift(Orders orders) {
        if (orders.calculateTotalPrice() >= 120000 && orders.isTotalPriceQualifiedForEvent()) {
            return 25000;
        }
        return 0;
    }
}
