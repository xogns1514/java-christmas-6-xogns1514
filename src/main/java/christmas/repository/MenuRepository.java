package christmas.repository;

import christmas.domain.Menu;
import christmas.domain.MenuCategory;
import christmas.error.ErrorMessage;
import java.util.ArrayList;
import java.util.List;

public class MenuRepository {

    private final List<Menu> menus;

    public MenuRepository() {
        this.menus = new ArrayList<>();
        initMenus();
    }

    public List<Menu> getMenus() {
        return menus;
    }

    public Menu findByMenuName(String menuName) {
        return menus.stream()
                .filter(menu -> menu.getName().equals(menuName))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(ErrorMessage.NOT_FOUND_MENU_ERROR.getMessage()));
    }

    private void initMenus() {
        menus.add(new Menu("양송이수프", 6000, MenuCategory.APPETIZER));
        menus.add(new Menu("타파스", 5500, MenuCategory.APPETIZER));
        menus.add(new Menu("시저샐러드", 8000, MenuCategory.APPETIZER));

        menus.add(new Menu("티본스테이크", 55000, MenuCategory.MAIN));
        menus.add(new Menu("바비큐립", 54000, MenuCategory.MAIN));
        menus.add(new Menu("해산물파스타", 35000, MenuCategory.MAIN));
        menus.add(new Menu("크리스마스파스타", 25000, MenuCategory.MAIN));

        menus.add(new Menu("초코케이크", 15000, MenuCategory.DESSERT));
        menus.add(new Menu("아이스크림", 5000, MenuCategory.DESSERT));

        menus.add(new Menu("제로콜라", 3000, MenuCategory.BEVERAGE));
        menus.add(new Menu("레드와인", 60000, MenuCategory.BEVERAGE));
        menus.add(new Menu("샴페인", 25000, MenuCategory.BEVERAGE));
    }
}
