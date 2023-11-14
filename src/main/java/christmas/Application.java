package christmas;

import christmas.controller.OrderController;
import christmas.repository.DateRepository;
import christmas.repository.MenuRepository;
import christmas.service.OrderService;

public class Application {
    public static void main(String[] args) {
        // TODO: 프로그램 구현
        OrderController orderController =
                new OrderController();
        orderController.run();
    }
}
