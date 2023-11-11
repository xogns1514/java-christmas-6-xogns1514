package christmas.error;

public enum ErrorMessage {

    NOT_FOUND_MENU_ERROR("메뉴판에 없는 메뉴를 입력할 수 없습니다."),
    NOT_VALID_QUANTITY_ERROR("메뉴 갯수가 1이상이어야 합니다.");

    private final String message;

    ErrorMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
