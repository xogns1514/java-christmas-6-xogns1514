package christmas.service;

import christmas.domain.Date;
import christmas.domain.Orders;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.stream.Stream;

public enum DiscountRule {

    FREE_GIFT("증정 이벤트", DiscountCalculator::calculateFreeGift),
    SPECIAL_DAY("특별 할인", DiscountCalculator::calculateSpecialDayDiscount),
    WEEKEND("주말 할인", DiscountCalculator::calculateWeekendDiscount),
    WEEKDAY("평일 할인", DiscountCalculator::calculateWeekDayDiscount),
    D_DAY("크리스마스 디데이 할인", DiscountCalculator::calculateDDayDiscount);


    private final String discountName;
    private final BiFunction<Date, Orders, Integer> calculator;


    DiscountRule(String discountName, BiFunction<Date, Orders, Integer> calculator) {
        this.discountName = discountName;
        this.calculator = calculator;
    }

    public String getDiscountName() {
        return discountName;
    }

    public int calculateDiscount(Date date, Orders orders) {
        return calculator.apply(date, orders);
    }

    public static Map<String, Integer> calculateAllDiscount(Date date, Orders orders) {
        Map<String, Integer> discountResult = new HashMap<>();

        Stream.of(values())
                .filter(rule -> hasDiscount(rule, date, orders))
                .forEach(rule -> addDiscountToMap(rule, date, orders, discountResult));
        return discountResult;
    }

    private static boolean hasDiscount(DiscountRule rule, Date date, Orders orders) {
        return rule.calculateDiscount(date, orders) != 0;
    }

    private static void addDiscountToMap(DiscountRule rule, Date date, Orders orders, Map<String, Integer> benefits) {
        benefits.put(rule.getDiscountName(), rule.calculateDiscount(date, orders));
    }
}
