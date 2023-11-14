package christmas.service;

import static christmas.util.Constant.*;

import christmas.domain.Day;
import christmas.domain.DayType;
import christmas.domain.Orders;

public class DiscountCalculator {

    public static int calculateTotalDiscount(Day day, Orders orders) {
        return calculateDDayDiscount(day, orders)
                + calculateWeekDayDiscount(day, orders)
                + calculateWeekendDiscount(day, orders)
                + calculateSpecialDayDiscount(day, orders)
                + calculateFreeGift(day, orders);
    }

    public static int calculateTotalPriceAfterDiscount(Day day, Orders orders) {
        int totalPriceBeforeDiscount = orders.calculateTotalPrice();

        return totalPriceBeforeDiscount
                - calculateDDayDiscount(day, orders)
                - calculateWeekendDiscount(day, orders)
                - calculateWeekDayDiscount(day, orders)
                - calculateSpecialDayDiscount(day, orders);
    }

    public static int calculateDDayDiscount(Day day, Orders orders) {
        if (day.isDDay() && orders.isTotalPriceQualifiedForEvent()) {
            return THOUSAND + (day.getDay() - FIRST_DAY) * HUNDRED;
        }
        return ZERO;
    }

    public static int calculateWeekDayDiscount(Day day, Orders orders) {
        if (DayType.WEEKDAY == day.getDayType() && orders.isTotalPriceQualifiedForEvent()) {
            return orders.countDessertMenu() * DAY_DISCOUNT;
        }
        return ZERO;
    }

    public static int calculateWeekendDiscount(Day day, Orders orders) {
        if (DayType.WEEKEND == day.getDayType() && orders.isTotalPriceQualifiedForEvent()) {
            return orders.countMainMenu() * DAY_DISCOUNT;
        }
        return ZERO;
    }

    public static int calculateSpecialDayDiscount(Day day, Orders orders) {
        if (day.isSpecialDay() && orders.isTotalPriceQualifiedForEvent()) {
            return THOUSAND;
        }
        return ZERO;
    }

    public static int calculateFreeGift(Day day, Orders orders) {
        if (orders.calculateTotalPrice() >= MIN_FREE_GIFT_PRICE && orders.isTotalPriceQualifiedForEvent()) {
            return FREE_GIFT_PRICE;
        }
        return ZERO;
    }
}
