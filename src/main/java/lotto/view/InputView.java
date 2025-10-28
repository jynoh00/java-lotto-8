package lotto.view;

import camp.nextstep.edu.missionutils.Console;
import lotto.common.InputMessage;

public class InputView {
    public String getUserPurchasePrice() {
        System.out.print(InputMessage.INPUT_PURCHASE_PRICE_PROMPT.getMessage());
        return Console.readLine();
    }

    public String getWinningNumbers() {
        System.out.print(InputMessage.INPUT_WINNING_NUMBERS_PROMPT.getMessage());
        return Console.readLine();
    }

    public String getBonusNumber() {
        System.out.print(InputMessage.INPUT_BONUS_NUMBER_PROMPT.getMessage());
        return Console.readLine();
    }
}

/*
입력 기능만 수행하게 구현 - 단일 책임
 */