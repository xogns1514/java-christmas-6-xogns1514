package christmas;

import static org.assertj.core.api.Assertions.*;

import christmas.domain.Day;
import christmas.domain.DayType;
import christmas.domain.Menu;
import christmas.domain.MenuCategory;
import christmas.domain.Order;
import christmas.domain.Orders;
import christmas.repository.MenuRepository;
import christmas.service.DiscountCalculator;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class DiscountTest {

    private final MenuRepository menuRepository = new MenuRepository();

    @DisplayName("DDay 할인이 적용되는 경우")
    @Test
    void calculateDDayDiscount() {
        Day dDay = new Day(25, DayType.WEEKDAY, false);
        Orders orders = new Orders(List.of(new Order(new Menu("TestMenu", 10000, MenuCategory.MAIN), 1)));

        int discount = DiscountCalculator.calculateDDayDiscount(dDay, orders);

        assertThat(discount).isEqualTo(3400);
    }

    @DisplayName("DDay 할인이 적용되지 않는 경우")
    @Test
    void calculateDDayDiscountIsNotQualified() {
        Day day = new Day(26, DayType.WEEKDAY, false);
        Orders orders = new Orders(List.of(new Order(new Menu("TestMenu", 10000, MenuCategory.MAIN), 1)));

        int discount = DiscountCalculator.calculateDDayDiscount(day, orders);

        assertThat(discount).isEqualTo(0);
    }

    @DisplayName("평일 할인이 적용되는 경우")
    @Test
    void calculateWeekdayDiscount() {
        Day day = new Day(4, DayType.WEEKDAY, false);
        Orders orders = new Orders(List.of(
                new Order(new Menu("TestMenu1", 10000, MenuCategory.MAIN), 1),
                new Order(new Menu("TestMenu2", 10000, MenuCategory.DESSERT), 1),
                new Order(new Menu("TestMenu3", 10000, MenuCategory.DESSERT), 2)
        ));

        int discount = DiscountCalculator.calculateWeekDayDiscount(day, orders);

        assertThat(discount).isEqualTo(2023 * 3);
    }

    @DisplayName("주말 할인이 적용되는 경우")
    @Test
    void calculateWeekendDiscount() {
        Day day = new Day(4, DayType.WEEKEND, false);
        Orders orders = new Orders(List.of(
                new Order(new Menu("TestMenu1", 10000, MenuCategory.MAIN), 2),
                new Order(new Menu("TestMenu2", 10000, MenuCategory.DESSERT), 1),
                new Order(new Menu("TestMenu3", 10000, MenuCategory.DESSERT), 2)
        ));

        int discount = DiscountCalculator.calculateWeekendDiscount(day, orders);

        assertThat(discount).isEqualTo(2023 * 2);
    }

    @DisplayName("특별 할인이 적용되는 경우")
    @Test
    void calculateSpecialDayDiscount() {
        Day day = new Day(4, DayType.WEEKEND, true);
        Orders orders = new Orders(List.of(new Order(new Menu("TestMenu1", 10000, MenuCategory.MAIN), 2)));

        int discount = DiscountCalculator.calculateSpecialDayDiscount(day, orders);

        assertThat(discount).isEqualTo(1000);
    }

    @DisplayName("증정 할인이 적용되는 경우")
    @Test
    void calculateFreeGiftDiscount() {
        Day day = new Day(4, DayType.WEEKEND, true);
        Orders orders = new Orders(List.of(
                new Order(new Menu("TestMenu1", 10000, MenuCategory.MAIN), 1),
                new Order(new Menu("TestMenu2", 50000, MenuCategory.MAIN), 1),
                new Order(new Menu("TestMenu1", 60000, MenuCategory.MAIN), 1)
                ));

        int discount = DiscountCalculator.calculateFreeGift(day, orders);

        assertThat(discount).isEqualTo(menuRepository.findByMenuName("샴페인").getPrice());
    }

    @DisplayName("증정 할인이 적용되지 않는 경우")
    @Test
    void calculateFreeGiftDiscountIsNotQualified() {
        Day day = new Day(4, DayType.WEEKEND, true);
        Orders orders = new Orders(List.of(
                new Order(new Menu("TestMenu1", 5000, MenuCategory.MAIN), 1),
                new Order(new Menu("TestMenu2", 50000, MenuCategory.MAIN), 1),
                new Order(new Menu("TestMenu1", 60000, MenuCategory.MAIN), 1)
        ));

        int discount = DiscountCalculator.calculateFreeGift(day, orders);

        assertThat(discount).isEqualTo(0);
    }
}
