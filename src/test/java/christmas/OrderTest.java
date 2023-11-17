package christmas;

import static org.assertj.core.api.Assertions.*;

import christmas.domain.Menu;
import christmas.domain.MenuCategory;
import christmas.domain.Order;
import christmas.domain.Orders;
import christmas.error.ErrorMessage;
import christmas.repository.MenuRepository;
import christmas.util.valid.InputValidator;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class OrderTest {

    private final MenuRepository menuRepository = new MenuRepository();

    @DisplayName("메뉴 갯수가 1이상이 아닐 경우 오류가 발생한다.")
    @ValueSource(strings = {"0", "-1", "-2", "-3", "-4", "-5"})
    @ParameterizedTest
    void invalidQuantity(int quantity) {
        assertThatThrownBy(() -> new Order(new Menu("스테이크", 20000, MenuCategory.MAIN), quantity))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
    }

    @DisplayName("메뉴 갯수가 20개 초과일 경우 오류가 발생한다.")
    @ValueSource(strings = {"6", "7", "8", "9"})
    @ParameterizedTest
    void invalidQuantityExceedLimit(int quantity) {
        Order testOrder1 = new Order(new Menu("스테이크", 20000, MenuCategory.MAIN), 15);
        Order testOrder2 = new Order(new Menu("스테이크", 20000, MenuCategory.MAIN), quantity);

        assertThatThrownBy(() -> new Orders(List.of(testOrder1, testOrder2)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
    }

    @DisplayName("메뉴판에 없는 메뉴를 입력할 경우 오류가 발생한다.")
    @ValueSource(strings = {"돈까스", "떡볶이", "닭가슴살", "김치찌개"})
    @ParameterizedTest
    void invalidMenu(String menu) {
        assertThatThrownBy(() -> menuRepository.findByMenuName(menu))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
    }

    @DisplayName("음료만 주문 시 오류가 발생한다.")
    @Test
    void onlyBeverageMenu() {
        Order beverage1 = new Order(new Menu("제로콜라", 3000, MenuCategory.BEVERAGE), 2);
        Order beverage2 = new Order(new Menu("레드와인", 60000, MenuCategory.BEVERAGE), 2);

        assertThatThrownBy(() -> new Orders(List.of(beverage1, beverage2)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
    }

    @DisplayName("중복된 메뉴 주문시 오류가 발생한다.")
    @Test
    void duplicateMenu() {
        Order testOrder1 = new Order(new Menu("스테이크", 20000, MenuCategory.MAIN), 2);
        Order testOrder2 = new Order(new Menu("스테이크", 20000, MenuCategory.MAIN), 10);

        assertThatThrownBy(() -> new Orders(List.of(testOrder1, testOrder2)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
    }

    @DisplayName("메뉴 형식이 예시와 다른 경우 오류가 발생한다.")
    @ValueSource(strings = {"양송이스프-1, 타파스-1", "양송이스프 - 1", "양송이스프-one,타파스-2", "제로콜라-1..타파스-2", " ", "양송이스프-1  ,제로콜라-1"})
    @ParameterizedTest
    void invalidOrderInput(String orderInput) {
        assertThatThrownBy(() -> InputValidator.validateOrderInput(orderInput,
                ErrorMessage.INVALID_ORDER_INPUT_ERROR.getMessage()))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
    }
}
