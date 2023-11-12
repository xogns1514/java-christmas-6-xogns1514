package christmas.domain;

import christmas.error.ErrorMessage;
import christmas.util.Constant;

public class Order {

    private Menu menu;
    private int quantity;

    public Order(Menu menu, int quantity) {
        this.menu = menu;
        this.quantity = quantity;
    }

    public Menu getMenu() {
        return menu;
    }

    public int getQuantity() {
        return quantity;
    }

    private void checkQuantity(int quantity) {
        if (isNotValidQuantity(quantity)) {
            throw new IllegalArgumentException(ErrorMessage.NOT_VALID_QUANTITY_ERROR.getMessage());
        }
    }

    private boolean isNotValidQuantity(int quantity) {
        return Constant.MIN_QUANTITY > quantity;
    }
}
