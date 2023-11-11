package christmas.view;

import camp.nextstep.edu.missionutils.Console;

public class InputView {

    private enum InputMessage {

        INPUT_ORDER_MESSAGE("주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)");

        private final String message;

        InputMessage(String message) {
            this.message = message;
        }
    }

    public static String inputOrder() {
        System.out.println(InputMessage.INPUT_ORDER_MESSAGE.message);
        return Console.readLine();
    }
}

