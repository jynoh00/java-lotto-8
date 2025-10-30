package lotto.service;

import lotto.util.StringToIntegerConverter;
import lotto.util.WinningNumbersParser;
import lotto.validator.InputValidator;

import java.util.List;

public class InputService {
    public int validateAndParsePurchasePrice(String userPurchasePrice) {
        int tmpPurchasePrice = StringToIntegerConverter.convert(userPurchasePrice); // trim 후 숫자로 변환
        return InputValidator.PURCHASE_PRICE.validatePurchasePrice(tmpPurchasePrice); // 1000으로 나누어 떨어지는 양의 정수
    }

    public List<Integer> validateAndParseWinningNumbers(String userWinningNumbers) {
        String tmpWinningNumbers = InputValidator.WINNING_NUMBER.validateWinningNumbers(userWinningNumbers);
        return WinningNumbersParser.parse(tmpWinningNumbers);
    }

    public int validateAndParseBonusNumber(String bonusNumber) {
        int tmpBonusNumber = StringToIntegerConverter.convert(bonusNumber);
        return InputValidator.BONUS_NUMBER.validateBonusNumber(tmpBonusNumber);
    }
}