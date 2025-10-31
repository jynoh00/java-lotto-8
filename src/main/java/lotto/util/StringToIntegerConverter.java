package lotto.util;

import lotto.common.ErrorMessage;

public class StringToIntegerConverter {
    public static int convert(String inputNumber) {
        try {
            return Integer.parseInt(inputNumber.trim());
        } catch (NumberFormatException | NullPointerException e) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_NUMBER_FORMAT.getMessage());
        }
    }
}