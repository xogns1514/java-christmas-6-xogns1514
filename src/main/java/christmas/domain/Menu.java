package christmas.domain;

import java.util.Objects;

public class Menu {

    private final String name;
    private final int price;
    private final MenuCategory category;

    public Menu(String name, int price, MenuCategory category) {
        this.name = name;
        this.price = price;
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public MenuCategory getCategory() {
        return category;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Menu menu = (Menu) o;
        return price == menu.price && Objects.equals(name, menu.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, price);
    }
}
