package lotto.util;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;


class WinningNumbersParserTest {
    private static final String ERROR_MESSAGE = "[ERROR]";

    @DisplayName("콤마로 구분된 숫자 문자열 파싱 검사")
    @Test
    void 콤마로_구분된_숫자_문자열_파싱_검사() {
        String input = "1,2,3,4,5,6";
        List<Integer> result = WinningNumbersParser.parse(input);

        assertThat(result).containsExactly(1, 2, 3, 4, 5, 6);
    }

    @DisplayName("입력 문자열에 공백이 포함된 파싱 검사")
    @Test
    void 입력_문자열에_공백이_포함된_파싱_검사() {
        String input = "1, 2, 3 , 4,5 , 6";
        List<Integer> result = WinningNumbersParser.parse(input);

        assertThat(result).containsExactly(1, 2, 3, 4, 5, 6);
    }

    @DisplayName("숫자가 아닌 값 포함 시 예외")
    @Test
    void 숫자가_아닌_값_포함_시_예외() {
        String input = "1,2,3,4,a,6";

        assertThatThrownBy(() -> WinningNumbersParser.parse(input))
                .isInstanceOf(IllegalArgumentException.class).hasMessageContaining(ERROR_MESSAGE);
    }

    @DisplayName("6개 미만 입력 시 예외")
    @Test
    void 여섯개_미만_입력_시_예외() {
        String input = "1,2,3,4,5";

        assertThatThrownBy(() -> WinningNumbersParser.parse(input))
                .isInstanceOf(IllegalArgumentException.class).hasMessageContaining(ERROR_MESSAGE);
    }

    @DisplayName("중복된 숫자 예외")
    @Test
    void 중복된_숫자_예외() {
        String input = "1,2,3,4,5,5";

        assertThatThrownBy(() -> WinningNumbersParser.parse(input))
                .isInstanceOf(IllegalArgumentException.class).hasMessageContaining(ERROR_MESSAGE);
    }

    @DisplayName("범위를 벗어난 숫자 예외")
    @Test
    void 범위를_벗어난_숫자_예외() {
        String input = "1,2,3,4,5,46";

        assertThatThrownBy(() -> WinningNumbersParser.parse(input))
                .isInstanceOf(IllegalArgumentException.class).hasMessageContaining(ERROR_MESSAGE);
    }
}