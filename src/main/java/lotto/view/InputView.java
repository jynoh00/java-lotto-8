package lotto.view;

import camp.nextstep.edu.missionutils.Console;
import lotto.common.InputMessage;

public class InputView {
    public String readUserPurchasePrice() {
        System.out.println(InputMessage.INPUT_PURCHASE_PRICE_PROMPT.getMessage());
        return Console.readLine();
    }

    public String readWinningNumbers() {
        System.out.println(InputMessage.INPUT_WINNING_NUMBERS_PROMPT.getMessage());
        return Console.readLine();
    }

    public String readBonusNumber() {
        System.out.println(InputMessage.INPUT_BONUS_NUMBER_PROMPT.getMessage());
        return Console.readLine();
    }
}

/*
입력 기능만 수행하게 구현 - 단일 책임
 */