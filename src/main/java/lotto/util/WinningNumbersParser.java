package lotto.util;

import lotto.validator.LottoValidator;

import java.util.ArrayList;
import java.util.List;

public class WinningNumbersParser {
    public static List<Integer> parse(String userWinningNumbers) {
        List<Integer> splitWinningNumbers = getSplitWinningNumbers(userWinningNumbers);
        LottoValidator.validate(splitWinningNumbers);

        return splitWinningNumbers;
    }

    private static List<Integer> getSplitWinningNumbers(String userWinningNumbers) {
        String[] numbers = userWinningNumbers.split(","); // 추후 문자열 enum으로 관리
        List<Integer> splitWinningNumbers = new ArrayList<>();

        for (String number : numbers) {
            splitWinningNumbers.add(StringToIntegerConverter.convert(number));
        }

        return splitWinningNumbers;
    }
}