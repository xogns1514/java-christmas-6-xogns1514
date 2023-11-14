package christmas.controller;

import static christmas.util.Constant.*;

import christmas.domain.Badge;
import christmas.domain.Day;
import christmas.service.DiscountCalculator;
import christmas.service.DiscountRule;
import christmas.domain.Orders;
import christmas.error.ErrorMessage;
import christmas.repository.DateRepository;
import christmas.service.OrderService;
import christmas.util.valid.InputValidator;
import christmas.view.InputView;
import christmas.view.OutputView;
import java.util.Map;

public class OrderController {

    private final OrderService orderService;
    private final DateRepository dateRepository;


    public OrderController() {
        this.orderService = new OrderService();
        this.dateRepository = new DateRepository();
    }

    public void run() {
        // Intro 및 날짜 입력
        Day day = inputDate();

        // 주문 입력
        Orders orders = inputOrders();
        OutputView.outputEventIntro(day);

        // 주문 메뉴 출력
        OutputView.outputOrders(orders);

        // 할인 전 총 주문 금액 출력
        OutputView.outputTotalPriceBeforeDiscount(orders);

        // 증정 메뉴 출력
        outputFreeGift(orders.calculateTotalPrice());

        // 이벤트 내용 출력
        outputEventBenefits(day, orders);
    }



    private Orders inputOrders() {
        try {
            String inputOrders = InputView.inputOrder();
            InputValidator.validateOrderInput(inputOrders, ErrorMessage.INVALID_ORDER_INPUT_ERROR.getMessage());
            return orderService.createOrders(inputOrders);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return inputOrders();
        }
    }

    private Day inputDate() {
        try {
            String day = InputView.inputDay();
            InputValidator.validateDayInput(day, ErrorMessage.INVALID_DATE_ERROR.getMessage());
            return dateRepository.findBydate(Integer.parseInt(day));
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return inputDate();
        }
    }

    private void outputFreeGift(int totalPriceBeforeDiscount) {
        OutputView.outputFreeGift();
        if (totalPriceBeforeDiscount < MIN_FREE_GIFT_PRICE) {
            OutputView.outputEmpty();
            return;
        }
        OutputView.outputFreeGiftMenu();
    }

    private void outputDiscountBenefit(Map<String, Integer> benefitResult) {
        OutputView.outputDiscountBenefitIntro();
        if (benefitResult.isEmpty()) {
            OutputView.outputEmpty();
            System.out.println();
            return;
        }
        OutputView.outputDiscountBenefit(benefitResult);
    }

    private void outputEventBenefits(Day day, Orders orders) {
        // 혜택 내역 출력
        outputDiscountBenefit(DiscountRule.calculateAllDiscount(day, orders));

        // 총혜택 금액 출력
        OutputView.outputTotalBenefitAmount(DiscountCalculator.calculateTotalDiscount(day, orders));

        // 할인 후 예상 결제 금액 출력
        OutputView.outputTotalPriceAfterDiscount(DiscountCalculator.calculateTotalPriceAfterDiscount(day, orders));

        // 12월 이벤트 배지 출력
        OutputView.outputBadge(Badge.getBadge(DiscountCalculator.calculateTotalDiscount(day, orders)));
    }

}
