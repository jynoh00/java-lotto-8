package lotto.common;

public enum ErrorMessage {
    INVALID_NUMBER_FORMAT("올바르지 않은 숫자 형식입니다."),
    MUST_BE_POSITIVE("입력한 숫자는 양의 정수여야 합니다."),
    MUST_BE_DIVISIBLE_BY_THOUSAND("구입금액은 1,000원으로 나누어 떨어져야 합니다."),
    EMPTY_INPUT("입력값이 비어있습니다."),
    INVALID_WINNING_NUMBERS_FORMAT("로또 번호는 숫자, 쉼표, 공백으로만 구성되어야 합니다."),
    INVALID_LOTTO_NUMBER_RANGE("로또 숫자는 1~45 내 정수여야 합니다."),
    INVALID_LOTTO_NUMBERS_LENGTH("로또 번호의 개수는 6개여야 합니다."),
    DUPLICATE_LOTTO_NUMBER("로또 숫자는 중복되지 않아야 합니다."),
    BONUS_NUMBER_REDUNDANCY("보너스 숫자는 로또 번호와 중복되지 않아야 합니다.");

    private static final String ERROR_PREFIX = "[ERROR] ";
    private final String message;

    ErrorMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return ERROR_PREFIX + message;
    }
}