package christmas.domain;

import java.util.function.Predicate;
import java.util.stream.Stream;

public enum Badge {

    NONE("없음", (discountBenefit) -> discountBenefit < 5000),
    STAR("별", (discountBenefit) -> discountBenefit >= 5000 && discountBenefit < 10000),
    TREE("트리", (discountBenefit) -> discountBenefit >= 10000 && discountBenefit < 20000),
    SANTA("산타", (discountBenefit) -> discountBenefit >= 20000);

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
