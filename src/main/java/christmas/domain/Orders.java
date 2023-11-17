package christmas.domain;

import static christmas.util.Constant.MIN_EVENT_QUALIFIED_PRICE;

import christmas.error.ErrorMessage;
import christmas.util.Constant;

import java.util.List;

public class Orders {

    private final List<Order> orders;

    public Orders(List<Order> orders) {
        validateOrders(orders);
        this.orders = orders;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public int calculateTotalPrice() {
        return orders.stream()
                .mapToInt(Order::getTotalPrice)
                .sum();
    }

    public int countDessertMenu() {
        return orders.stream()
                .filter(order -> MenuCategory.DESSERT == order.getMenu().getCategory())
                .mapToInt(Order::getQuantity)
                .sum();
    }

    public int countMainMenu() {
        return orders.stream()
                .filter(order -> MenuCategory.MAIN == order.getMenu().getCategory())
                .mapToInt(Order::getQuantity)
                .sum();
    }

    public boolean isTotalPriceQualifiedForEvent() {
        return calculateTotalPrice() >= MIN_EVENT_QUALIFIED_PRICE;
    }

    private void validateOrders(List<Order> orders) {
        checkOnlyBeverage(orders);
        checkOrderCountOverLimit(orders);
        checkOrdersHasDuplicateMenu(orders);
    }

    private void checkOnlyBeverage(List<Order> orders) {
        if (hasOnlyBeverage(orders)) {
            throw new IllegalArgumentException(ErrorMessage.ONLY_BEVERAGE_ORDER_ERROR.getMessage());
        }
    }

    private void checkOrderCountOverLimit(List<Order> orders) {
        if (isOrderCountOverLimit(orders)) {
            throw new IllegalArgumentException(ErrorMessage.ORDER_COUNT_OVER_LIMIT_ERROR.getMessage());
        }
    }

    private void checkOrdersHasDuplicateMenu(List<Order> orders) {
        if (hasDuplicateMenu(orders)) {
            throw new IllegalArgumentException(ErrorMessage.HAS_DUPLICATE_MENU_ERROR.getMessage());
        }
    }

    private boolean hasOnlyBeverage(List<Order> orders) {
        return orders.stream()
                .allMatch(order -> MenuCategory.BEVERAGE == order.getMenu().getCategory());
    }

    private boolean isOrderCountOverLimit(List<Order> orders) {
        int totalOrderCount = orders.stream()
                .mapToInt(Order::getQuantity)
                .sum();
        return totalOrderCount > Constant.MAX_ORDER;
    }

    private boolean hasDuplicateMenu(List<Order> orders) {
        return orders.stream()
                .map(Order::getMenu)
                .distinct()
                .count() != orders.size();
    }
}
