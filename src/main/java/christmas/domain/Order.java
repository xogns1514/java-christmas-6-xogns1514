package christmas.domain;

import christmas.error.ErrorMessage;
import christmas.util.Constant;
import java.util.List;

public class Order {

    private Menu menus;
    private int quantity;

    public Order(Menu menus, int quantity) {
        this.menus = menus;
        this.quantity = quantity;
    }

    public Menu getMenus() {
        return menus;
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
