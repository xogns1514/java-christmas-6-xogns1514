package christmas.view;

import camp.nextstep.edu.missionutils.Console;

public class InputView {

    private enum InputMessage {

        INPUT_ORDER_MESSAGE("주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)"),
        INPUT_DATE_MESSAGE("안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.\n12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)");

        private final String message;

        InputMessage(String message) {
            this.message = message;
        }
    }

    public static String inputOrder() {
        System.out.println(InputMessage.INPUT_ORDER_MESSAGE.message);
        return Console.readLine();
    }

    public static int inputDate() {
        System.out.println(InputMessage.INPUT_DATE_MESSAGE.message);
        return Integer.parseInt(Console.readLine());
    }
}

