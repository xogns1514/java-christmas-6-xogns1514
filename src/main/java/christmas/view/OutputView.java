package christmas.view;

import christmas.domain.Day;
import christmas.domain.Orders;
import java.text.DecimalFormat;
import java.util.Map;

public class OutputView {

    private enum OutputMessage {

        OUTPUT_INTRO_MESSAGE("안녕하세요! 우테코 식당 12월 이벤트 플래너입니다."),
        OUTPUT_EVENT_INTRO_MESSAGE("12월 %d일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!\n"),
        OUTPUT_ORDERS_MESSAGE("<주문 메뉴>"),
        OUTPUT_TOTAL_PRICE_BEFORE_DISCOUNT_MESSAGE("<할인 전 총주문 금액>"),
        OUTPUT_FREE_GIFT_MESSAGE("<증정 메뉴>"),
        OUTPUT_FREE_GIFT_MENU_MESSAGE("샴페인 1개"),
        OUTPUT_DISCOUNT_BENEFITS_MESSAGE("<혜택 내역>"),
        OUTPUT_TOTAL_BENEFIT_AMOUNT_MESSAGE("<총혜택 금액>"),
        OUTPUT_TOTAL_PRICE_AFTER_DISCOUNT_MESSAGE("<할인 후 예상 결제 금액>"),
        OUTPUT_BADGE_MESSAGE("<12월 이벤트 배지>"),
        OUTPUT_DECIMAL_FORMAT("###,###"),
        OUTPUT_PRICE("%s원\n"),
        OUTPUT_MINUS_PRICE("-%s원\n"),

        OUTPUT_DISCOUNT_MESSAGE("%s: -%s원\n"),
        OUTPUT_EMPTY_MESSAGE("없음");

        private final String message;

        OutputMessage(String message) {
            this.message = message;
        }
    }

    private static final DecimalFormat formatter = new DecimalFormat(OutputMessage.OUTPUT_DECIMAL_FORMAT.message);

    public static void outputEventIntro(Day day) {
        System.out.printf(OutputMessage.OUTPUT_EVENT_INTRO_MESSAGE.message, day.getDay());
        System.out.println();
    }

    public static void outputOrders(Orders orders) {
        System.out.println(OutputMessage.OUTPUT_ORDERS_MESSAGE.message);
        orders.getOrders()
                .forEach(System.out::println);
        System.out.println();
    }

    public static void outputTotalPriceBeforeDiscount(Orders orders) {
        System.out.println(OutputMessage.OUTPUT_TOTAL_PRICE_BEFORE_DISCOUNT_MESSAGE.message);
        outputMoney(orders.calculateTotalPrice());
        System.out.println();
    }

    public static void outputFreeGift() {
        System.out.println(OutputMessage.OUTPUT_FREE_GIFT_MESSAGE.message);
    }

    public static void outputFreeGiftMenu() {
        System.out.println(OutputMessage.OUTPUT_FREE_GIFT_MENU_MESSAGE.message);
        System.out.println();
    }

    public static void outputDiscountBenefitIntro() {
        System.out.println(OutputMessage.OUTPUT_DISCOUNT_BENEFITS_MESSAGE.message);
    }

    public static void outputDiscountBenefit(Map<String, Integer> benefitResult) {
        benefitResult.forEach((discountName, discountAmount) ->
                System.out.printf(OutputMessage.OUTPUT_DISCOUNT_MESSAGE.message, discountName, formatter.format(discountAmount)));
        System.out.println();
    }

    public static void outputTotalBenefitAmount(int totalBenefitAmount) {
        System.out.println(OutputMessage.OUTPUT_TOTAL_BENEFIT_AMOUNT_MESSAGE.message);
        outputMoney(totalBenefitAmount * -1);
        System.out.println();
    }

    public static void outputTotalPriceAfterDiscount(int totalPriceAfterDiscount) {
        System.out.println(OutputMessage.OUTPUT_TOTAL_PRICE_AFTER_DISCOUNT_MESSAGE.message);
        outputMoney(totalPriceAfterDiscount);
        System.out.println();
    }

    public static void outputBadge(String badge) {
        System.out.println(OutputMessage.OUTPUT_BADGE_MESSAGE.message);
        System.out.println(badge);
    }

    public static void outputEmpty() {
        System.out.println(OutputMessage.OUTPUT_EMPTY_MESSAGE.message);
        System.out.println();
    }

    private static void outputMoney(int money) {
        System.out.printf(OutputMessage.OUTPUT_PRICE.message, formatter.format(money));
    }
}
