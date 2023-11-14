package christmas.view;

import christmas.domain.Orders;
import java.text.DecimalFormat;
import java.util.Map;

public class OutputView {

    private enum OutputMessage {

        OUTPUT_ORDERS_MESSAGE("<주문 메뉴>"),
        OUTPUT_TOTAL_PRICE_BEFORE_DISCOUNT_MESSAGE("<할인 전 총주문 금액>"),
        OUTPUT_PRICE("%s원\n"),
        OUTPUT_DECIMAL_FORMAT("###,###"),
        OUTPUT_DISCOUNT_BENEFITS("<혜택 내역>"),
        OUTPUT_DISCOUNT("%s: -%d원\n"),
        OUTPUT_EMPTY_MESSAGE("없음");

        private final String message;

        OutputMessage(String message) {
            this.message = message;
        }
    }

    private static final DecimalFormat formatter = new DecimalFormat(OutputMessage.OUTPUT_DECIMAL_FORMAT.message);

    public static void outputOrders(Orders orders) {
        System.out.println(OutputMessage.OUTPUT_ORDERS_MESSAGE.message);
        orders.getOrders()
                .forEach(System.out::println);
    }

    public static void outputTotalPriceBeforeDiscount(Orders orders) {
        System.out.println(OutputMessage.OUTPUT_TOTAL_PRICE_BEFORE_DISCOUNT_MESSAGE.message);
        System.out.printf(OutputMessage.OUTPUT_PRICE.message, formatter.format(orders.calculateTotalPrice()));
    }

    public static void outputDiscountBenefit(Map<String, Integer> benefitResult) {
        System.out.println(OutputMessage.OUTPUT_DISCOUNT_BENEFITS.message);
        if (benefitResult.isEmpty()) {
            outputEmpty();
            return;
        }
        benefitResult.forEach((discountName, discountAmount) ->
                System.out.printf(OutputMessage.OUTPUT_DISCOUNT.message, discountName, discountAmount));
    }

    public static void outputEmpty() {
        System.out.println(OutputMessage.OUTPUT_EMPTY_MESSAGE);
    }
}
