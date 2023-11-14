package christmas.error;

public enum ErrorMessage {

    NOT_FOUND_MENU_ERROR("유효하지 않은 주문입니다. 다시 입력해 주세요."),
    NOT_VALID_QUANTITY_ERROR("유효하지 않은 주문입니다. 다시 입력해 주세요."),
    ONLY_BEVERAGE_ORDER_ERROR("유효하지 않은 주문입니다. 다시 입력해 주세요."),
    ORDER_COUNT_OVER_LIMIT_ERROR("유효하지 않은 주문입니다. 다시 입력해 주세요."),
    HAS_DUPLICATE_MENU_ERROR("유효하지 않은 주문입니다. 다시 입력해 주세요."),
    INVALID_DATE_ERROR("유효하지 않은 날짜입니다. 다시 입력해 주세요.");

    private final String message;
    private final String base = "[ERROR] ";

    ErrorMessage(String message) {
        this.message = base + message;
    }

    public String getMessage() {
        return message;
    }
}
