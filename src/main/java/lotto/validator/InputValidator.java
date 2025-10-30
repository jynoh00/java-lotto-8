package lotto.validator;

import lotto.common.ErrorMessage;
import lotto.common.LottoConstants;

import java.util.List;

public enum InputValidator {
    PURCHASE_PRICE {
        @Override
        public int validatePurchasePrice(int purchasePrice) {
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
        public int validateBonusNumber(int bonusNumber) {
            validateNumberRange(bonusNumber);

            return bonusNumber;
        }

        @Override
        public void validateRedundancy(int bonusNumber, List<Integer> winningNumbers) {
            if (winningNumbers.contains(bonusNumber)) {
                throw new IllegalArgumentException(ErrorMessage.BONUS_NUMBER_REDUNDANCY.getMessage());
            }
        }
    };

    public int validatePurchasePrice(int purchasePrice) {
        throw new UnsupportedOperationException();
    }

    public String validateWinningNumbers(String inputWinningNumbers) {
        throw new UnsupportedOperationException();
    }

    public int validateBonusNumber(int bonusNumber) {
        throw new UnsupportedOperationException();
    }

    public void validateRedundancy(int bonusNumber, List<Integer> winningNumbers) {
        throw new UnsupportedOperationException();
    }

    protected static void validatePositive(int inputNumber) {
        if (inputNumber <= 0) {
            throw new IllegalArgumentException(ErrorMessage.MUST_BE_POSITIVE.getMessage());
        }
    }

    protected static void validateDivisibleByThousand(int inputNumber) {
        if (inputNumber % LottoConstants.LOTTO_PRICE.getValue() != LottoConstants.ZERO.getValue()) {
            throw new IllegalArgumentException(ErrorMessage.MUST_BE_DIVISIBLE_BY_THOUSAND.getMessage());
        }
    }

    protected static void validateNotEmpty(String inputWinningNumbers) {
        if (inputWinningNumbers == null || inputWinningNumbers.trim().isEmpty()) {
            throw new IllegalArgumentException(ErrorMessage.EMPTY_INPUT.getMessage());
        }
    }

    protected static void validateFormat(String inputWinningNumbers) {
        if (!inputWinningNumbers.trim().matches("[0-9,\\s]+")) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_WINNING_NUMBERS_FORMAT.getMessage());
        }
    }

    protected static void validateNumberRange(int inputNumber) {
        if (inputNumber < LottoConstants.LOTTO_MIN_NUMBER.getValue()
                || inputNumber > LottoConstants.LOTTO_MAX_NUMBER.getValue()) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_LOTTO_NUMBER_RANGE.getMessage());
        }
    }
}