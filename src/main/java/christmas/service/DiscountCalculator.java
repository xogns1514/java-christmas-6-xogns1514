package christmas.service;

import static christmas.util.Constant.*;

import christmas.domain.Date;
import christmas.domain.DayType;
import christmas.domain.Orders;

public class DiscountCalculator {

    public static int calculateTotalDiscount(Date date, Orders orders) {
        return calculateDDayDiscount(date, orders)
                + calculateWeekDayDiscount(date, orders)
                + calculateWeekendDiscount(date, orders)
                + calculateSpecialDayDiscount(date, orders)
                + calculateFreeGift(date, orders);
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
            return THOUSAND + (date.getDay() - FIRST_DAY) * HUNDRED;
        }
        return 0;
    }

    public static int calculateWeekDayDiscount(Date date, Orders orders) {
        if (DayType.WEEKDAY == date.getDayType() && orders.isTotalPriceQualifiedForEvent()) {
            return orders.countDessertMenu() * DAY_DISCOUNT;
        }
        return 0;
    }

    public static int calculateWeekendDiscount(Date date, Orders orders) {
        if (DayType.WEEKEND == date.getDayType() && orders.isTotalPriceQualifiedForEvent()) {
            return orders.countMainMenu() * DAY_DISCOUNT;
        }
        return 0;
    }

    public static int calculateSpecialDayDiscount(Date date, Orders orders) {
        if (date.isSpecialDay() && orders.isTotalPriceQualifiedForEvent()) {
            return THOUSAND;
        }
        return 0;
    }

    public static int calculateFreeGift(Date date, Orders orders) {
        if (orders.calculateTotalPrice() >= MIN_FREE_GIFT_PRICE && orders.isTotalPriceQualifiedForEvent()) {
            return FREE_GIFT_PRICE;
        }
        return 0;
    }
}
