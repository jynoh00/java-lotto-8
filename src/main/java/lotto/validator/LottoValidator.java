package lotto.validator;

import lotto.common.ErrorMessage;
import lotto.common.LottoConstants;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class LottoValidator {
    public static void validate(List<Integer> LottoNumbers) {
        validateSize(LottoNumbers);
        validateRange(LottoNumbers);
        validateRedundancy(LottoNumbers);
    }

    private static void validateSize(List<Integer> LottoNumbers) {
        if (LottoNumbers.size() != LottoConstants.LOTTO_NUMBERS_LENGTH.getValue()) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_LOTTO_NUMBERS_LENGTH.getMessage());
        }
    }

    private static void validateRange(List<Integer> LottoNumbers) {
        for (int number : LottoNumbers) {
            if (number < LottoConstants.LOTTO_MIN_NUMBER.getValue()
                    || number > LottoConstants.LOTTO_MAX_NUMBER.getValue()) {
                throw new IllegalArgumentException(ErrorMessage.INVALID_LOTTO_NUMBER_RANGE.getMessage());
            }
        }
    }

    private static void validateRedundancy(List<Integer> LottoNumbers) {
        Set<Integer> uniqueNumbers = new HashSet<>(LottoNumbers);
        if (uniqueNumbers.size() != LottoConstants.LOTTO_NUMBERS_LENGTH.getValue()) {
            throw new IllegalArgumentException(ErrorMessage.DUPLICATE_LOTTO_NUMBER.getMessage());
        }
    }
}