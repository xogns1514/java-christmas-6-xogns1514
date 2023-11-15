package christmas;

import static org.assertj.core.api.Assertions.assertThat;

import christmas.domain.Badge;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class BadgeTest {

    @DisplayName("배지가 없는 경우")
    @ValueSource(strings = {"0", "1000", "2000", "3000", "4999"})
    @ParameterizedTest
    void badgeIsNone(int discountBenefit) {
        String badge = Badge.getBadge(discountBenefit);

        assertThat(badge).isEqualTo("없음");
    }

    @DisplayName("배지가 별인 경우")
    @ValueSource(strings = {"5000", "6000", "7000", "8000", "9999"})
    @ParameterizedTest
    void badgeIsStar(int discountBenefit) {
        String badge = Badge.getBadge(discountBenefit);

        assertThat(badge).isEqualTo("별");
    }

    @DisplayName("배지가 트리인 경우")
    @ValueSource(strings = {"10000", "11000", "12000", "13000", "19999"})
    @ParameterizedTest
    void badgeIsTree(int discountBenefit) {
        String badge = Badge.getBadge(discountBenefit);

        assertThat(badge).isEqualTo("트리");
    }

    @DisplayName("배지가 산타인 경우")
    @ValueSource(strings = {"20000", "21000", "22000", "23000", "99999"})
    @ParameterizedTest
    void badgeIsSanta(int discountBenefit) {
        String badge = Badge.getBadge(discountBenefit);

        assertThat(badge).isEqualTo("산타");
    }
}
