package lotto.validator;

import lotto.common.ErrorMessage;
import lotto.common.LottoConstants;

public enum InputValidator {
    PURCHASE_PRICE {
        @Override
        public int validatePurchasePrice(String inputPurchasePrice) {
            int purchasePrice = parseInteger(inputPurchasePrice);
            validatePositive(purchasePrice);
            validateDivisibleByThousand(purchasePrice);

            return purchasePrice;
        }
    },

    WINNING_NUMBER {
        @Override
        public String validateWinningNumbers(String inputWinningNumbers) {
            validateNotEmpty(inputWinningNumbers);
            validateFormat(inputWinningNumbers);

            return inputWinningNumbers.trim();
        }
    },

    BONUS_NUMBER {
        @Override
        public int validateBonusNumber(String inputBonusNumber) {
            int bonusNumber = parseInteger(inputBonusNumber);
            validateNumberRange(bonusNumber);

            return bonusNumber;
        }
    };

    public int validatePurchasePrice(String inputPurchasePrice) {
        throw new UnsupportedOperationException();
    }

    public String validateWinningNumbers(String inputWinningNumbers) {
        throw new UnsupportedOperationException();
    }

    public int validateBonusNumber(String inputBonusNumber) {
        throw new UnsupportedOperationException();
    }

    protected static int parseInteger(String inputNumber) {
        try {
            return Integer.parseInt(inputNumber.trim());
        }catch (NumberFormatException e) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_NUMBER_FORMAT.getMessage());
        }
    }

    protected static void validatePositive(int inputNumber) {
        if (inputNumber <= 0) {
            throw new IllegalArgumentException(ErrorMessage.MUST_BE_POSITIVE.getMessage());
        }
    }

    protected static void validateDivisibleByThousand(int inputNumber) {
        if (inputNumber % LottoConstants.LOTTO_PRICE.getValue() != LottoConstants.ZERO.getValue()) {
            throw new IllegalArgumentException(ErrorMessage.MUST_BE_DIVISIBLE_BY_THOUSEND.getMessage());
        }
    }

    protected static void validateNotEmpty(String inputWinningNumbers) {
        if (inputWinningNumbers == null || inputWinningNumbers.trim().isEmpty()) {
            throw new IllegalArgumentException(ErrorMessage.EMPTY_INPUT.getMessage());
        }
    }

    protected static void validateFormat(String inputWinningNumbers) {
        // trim()하고 숫자와 쉼표로만 구성되어있는지 확인
        if (!inputWinningNumbers.trim().matches("[0-9,\\s]+")) { // 추후 문자열 Enum으로 처리
            throw new IllegalArgumentException(ErrorMessage.INVALID_WINNING_NUMBERS_FORMAT.getMessage());
        }
    }

    protected static void validateNumberRange(int inputNumber) {
        if (inputNumber < LottoConstants.LOTTO_MIN_NUMBER.getValue() || inputNumber > LottoConstants.LOTTO_MAX_NUMBER.getValue()) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_LOTTO_NUMBER_RANGE.getMessage());
        }
    }


    // 다른 Validator로 검증할 것들
    // 쉼표 기준 스플릿한 값을 받아온 리스트에서 개별 숫자가 1~45이며 중복이 없는 지 확인 (인자로 리스트)
    // 보너스 숫자가 기존 WinningNumbers와 중복이 없는 지 확인 (인자로 리스트랑, 인티저)

}
