package lotto.util;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class StringToIntegerConverterTest {
    private static final String ERROR_MESSAGE = "[ERROR]";

    @DisplayName("공백 포함 숫자 문자열 정상 변환 검사")
    @Test
    void 공백_포함_숫자_문자열_정상_변환_검사() {
        String input = " 12345 ";
        int result = StringToIntegerConverter.convert(input);

        assertThat(result).isEqualTo(12345);
    }

    @DisplayName("숫자가 아닌 문자열이 입력될 경우 예외")
    @Test
    void 숫자가_아닌_문자열이_입력될_경우_예외() {
        String input = "abc123";

        assertThatThrownBy(() -> StringToIntegerConverter.convert(input))
                .isInstanceOf(IllegalArgumentException.class).hasMessageContaining(ERROR_MESSAGE);
    }

    @DisplayName("공백만 입력된 경우 예외")
    @Test
    void 공백만_입력된_경우_예외() {
        String input = "  ";

        assertThatThrownBy(() -> StringToIntegerConverter.convert(input))
                .isInstanceOf(IllegalArgumentException.class).hasMessageContaining(ERROR_MESSAGE);
    }

    @DisplayName("빈 문자열이 입력된 경우 예외")
    @Test
    void 빈_문자열이_입력된_경우_예외() {
        String input = "";

        assertThatThrownBy(() -> StringToIntegerConverter.convert(input))
                .isInstanceOf(IllegalArgumentException.class).hasMessageContaining(ERROR_MESSAGE);
    }

    @DisplayName("null 값이 입력된 경우 IllegalArgumentException 발생")
    @Test
    void 널_값이_입력된_경우_IllegalArgumentException_발생() {
        assertThatThrownBy(() -> StringToIntegerConverter.convert(null))
                .isInstanceOf(IllegalArgumentException.class).hasMessageContaining(ERROR_MESSAGE);
    }
}