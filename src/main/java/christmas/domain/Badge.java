package christmas.domain;

import static christmas.util.Constant.*;

import java.util.function.Predicate;
import java.util.stream.Stream;

public enum Badge {

    NONE("없음", (discountBenefit) -> discountBenefit < STAR_BADGE_PRICE),
    STAR("별", (discountBenefit) -> discountBenefit >= STAR_BADGE_PRICE && discountBenefit < TREE_BADGE_PRICE),
    TREE("트리", (discountBenefit) -> discountBenefit >= TREE_BADGE_PRICE && discountBenefit < SANTA_BADGE_PRICE),
    SANTA("산타", (discountBenefit) -> discountBenefit >= SANTA_BADGE_PRICE);

    private final String name;
    private final Predicate<Integer> isMatch;

    Badge(String name, Predicate<Integer> isMatch) {
        this.name = name;
        this.isMatch = isMatch;
    }

    public String getName() {
        return name;
    }

    public static String getBadge(int discountBenefit) {
        return Stream.of(values())
                .filter(badge -> badge.isMatch.test(discountBenefit))
                .findAny()
                .map(Badge::getName)
                .orElse(Badge.NONE.name);
    }
}
