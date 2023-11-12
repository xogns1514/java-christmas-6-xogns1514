package christmas.service;

import christmas.domain.Menu;
import christmas.domain.Order;
import christmas.domain.Orders;
import christmas.repository.MenuRepository;
import christmas.util.Converter;

import java.util.List;
import java.util.stream.Collectors;

public class OrderService {

    private final MenuRepository repository;

    public OrderService(MenuRepository repository) {
        this.repository = repository;
    }

    public Orders createOrders(String inputOrder) {
        List<String> inputOrders = Converter.splitWithComma(inputOrder);

        List<Order> orders = inputOrders.stream()
                .map(this::createOrder)
                .collect(Collectors.toList());

        return new Orders(orders);
    }

    private Order createOrder(String inputOrder) {
        List<String> menuAndQuantity = Converter.splitWithHyphen(inputOrder);

        String menuName = menuAndQuantity.get(0).trim();
        int quantity = Integer.parseInt(menuAndQuantity.get(1).trim());

        Menu menu = repository.findByMenuName(menuName);

        return new Order(menu, quantity);
    }

}
