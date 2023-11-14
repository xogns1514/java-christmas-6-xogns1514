package christmas.controller;

import christmas.domain.Badge;
import christmas.domain.Date;
import christmas.domain.DiscountCalculator;
import christmas.domain.DiscountRule;
import christmas.domain.Orders;
import christmas.repository.DateRepository;
import christmas.service.OrderService;
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
        Date date = inputDate();

        // 주문 입력
        Orders orders = inputOrders();
        OutputView.outputEventIntro(date);

        // 주문 메뉴 출력
        OutputView.outputOrders(orders);

        // 할인 전 총 주문 금액 출력
        OutputView.outputTotalPriceBeforeDiscount(orders);

        // 증정 메뉴 출력
        outputFreeGift(orders.calculateTotalPrice());

        // 이벤트 내용 출력
        outputEventBenefits(date, orders);
    }



    private Orders inputOrders() {
        try {
            String inputOrders = InputView.inputOrder();
            return orderService.createOrders(inputOrders);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return inputOrders();
        }
    }

    private christmas.domain.Date inputDate() {
        try {
            int day = InputView.inputDate();
            return dateRepository.findBydate(day);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return inputDate();
        }
    }

    private void outputFreeGift(int totalPriceBeforeDiscount) {
        OutputView.outputFreeGift();
        if (totalPriceBeforeDiscount < 120_000) {
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

    private void outputEventBenefits(Date date, Orders orders) {
        // 혜택 내역 출력
        outputDiscountBenefit(DiscountRule.calculateAllDiscount(date, orders));

        // 총혜택 금액 출력
        OutputView.outputTotalBenefitAmount(DiscountCalculator.calculateTotalDiscount(date, orders));

        // 할인 후 예상 결제 금액 출력
        OutputView.outputTotalPriceAfterDiscount(DiscountCalculator.calculateTotalPriceAfterDiscount(date, orders));

        // 12월 이벤트 배지 출력
        OutputView.outputBadge(Badge.getBadge(DiscountCalculator.calculateTotalDiscount(date, orders)));
    }

}
