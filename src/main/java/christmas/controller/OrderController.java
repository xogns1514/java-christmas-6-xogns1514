package christmas.controller;

import christmas.domain.Orders;
import christmas.service.OrderService;
import christmas.view.InputView;
import christmas.view.OutputView;

public class OrderController {

    private final OrderService orderService;


    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    public Orders inputOrders() {
        try {
            String inputOrders = InputView.inputOrder();
            return orderService.createOrders(inputOrders);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return inputOrders();
        }
    }
}
