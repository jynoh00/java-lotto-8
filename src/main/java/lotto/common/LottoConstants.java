package lotto.common;

public enum LottoConstants {
    LOTTO_PRICE(1000),
    ZERO(0),
    LOTTO_MIN_NUMBER(1),
    LOTTO_MAX_NUMBER(45),
    LOTTO_NUMBERS_LENGTH(6); // 개별 로또 리스트 크기

    private final int value;

    LottoConstants(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}