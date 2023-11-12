package christmas.controller;

import christmas.service.OrderService;
import christmas.view.InputView;
import christmas.view.OutputView;

public class OrderController {

    private final OrderService orderService;


    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    public void inputOrders() {
        String inputOrders = InputView.inputOrder();
        OutputView.outputOrders(orderService.createOrders(inputOrders));
    }
}
