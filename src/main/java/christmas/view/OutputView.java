package christmas.view;

import christmas.domain.Orders;

public class OutputView {

    private enum OutputMessage {

        OUTPUT_ORDERS_MESSAGE("<주문 메뉴>");

        private final String message;

        OutputMessage(String message) {
            this.message = message;
        }
    }

    public static void outputOrders(Orders orders) {
        System.out.println(OutputMessage.OUTPUT_ORDERS_MESSAGE.message);
        orders.getOrders()
                .forEach(System.out::println);
    }

}
