package christmas.view;

import christmas.domain.Orders;
import java.text.DecimalFormat;

public class OutputView {

    private enum OutputMessage {

        OUTPUT_ORDERS_MESSAGE("<주문 메뉴>"),
        OUTPUT_TOTAL_PRICE_BEFORE_DISCOUNT_MESSAGE("<할인 전 총주문 금액>"),
        OUTPUT_PRICE("%s원\n"),
        OUTPUT_DECIMAL_FORMAT("###,###");

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

}
