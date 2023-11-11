package christmas.error;

public enum ErrorMessage {

    NOT_FOUND_MENU_ERROR("메뉴판에 없는 메뉴를 입력할 수 없습니다.");

    private final String message;

    ErrorMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
