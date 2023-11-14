package christmas;

import static org.assertj.core.api.Assertions.*;

import christmas.domain.Day;
import christmas.domain.DayType;
import christmas.error.ErrorMessage;
import christmas.util.valid.InputValidator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class DayTest {

    @DisplayName("범위가 벗어난 날짜를 입력하면 오류가 발생한다.")
    @ValueSource(strings = {"-1", "0", "32", "40"})
    @ParameterizedTest
    void invalidRangeDay(int day) {
        assertThatThrownBy(() -> new Day(day, DayType.WEEKEND, true))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.");
    }

    @DisplayName("범위에 맞는 날짜를 입력하면 오류가 발생하지 않는다.")
    @ValueSource(strings = {"1", "11", "25", "31"})
    @ParameterizedTest
    void validRangeDay(int day) {
        assertThatCode(() -> new Day(day, DayType.WEEKEND, true))
                .doesNotThrowAnyException();
    }

    @DisplayName("형식에 맞지 않는 날짜를 입력하면 오류가 발생한다.")
    @ValueSource(strings = {"a", "", " ", "1일"})
    @ParameterizedTest
    void invalidDayInput(String day) {
        assertThatThrownBy(() -> InputValidator.validateDayInput(day, ErrorMessage.INVALID_DATE_ERROR.getMessage()))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.");
    }

    @DisplayName("형식에 맞는 날짜를 입력하면 오류가 발생하지 않는다.")
    @ValueSource(strings = {"1", "2", "25", "31"})
    @ParameterizedTest
    void validDayInput(String day) {
        assertThatCode(() -> InputValidator.validateDayInput(day, ErrorMessage.INVALID_DATE_ERROR.getMessage()))
                .doesNotThrowAnyException();
    }
}
