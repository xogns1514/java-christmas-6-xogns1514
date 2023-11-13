package christmas.domain;

public class DiscountCalculator {

    public static int calculateDDayDiscount(Date date) {
        if (date.isDDay()) {
            return 1000 + (date.getDay() - 1) * 100;
        }
        return 0;
    }
}
