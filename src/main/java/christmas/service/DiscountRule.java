package christmas.service;

import christmas.domain.Day;
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
    private final BiFunction<Day, Orders, Integer> calculator;


    DiscountRule(String discountName, BiFunction<Day, Orders, Integer> calculator) {
        this.discountName = discountName;
        this.calculator = calculator;
    }

    public String getDiscountName() {
        return discountName;
    }

    public int calculateDiscount(Day day, Orders orders) {
        return calculator.apply(day, orders);
    }

    public static Map<String, Integer> calculateAllDiscount(Day day, Orders orders) {
        Map<String, Integer> discountResult = new HashMap<>();

        Stream.of(values())
                .filter(rule -> hasDiscount(rule, day, orders))
                .forEach(rule -> addDiscountToMap(rule, day, orders, discountResult));
        return discountResult;
    }

    private static boolean hasDiscount(DiscountRule rule, Day day, Orders orders) {
        return rule.calculateDiscount(day, orders) != 0;
    }

    private static void addDiscountToMap(DiscountRule rule, Day day, Orders orders, Map<String, Integer> benefits) {
        benefits.put(rule.getDiscountName(), rule.calculateDiscount(day, orders));
    }
}
