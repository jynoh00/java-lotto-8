package lotto.validator;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Assertions.assertThatCode;

class LottoValidatorTest {
    private static final String ERROR_MESSAGE = "[ERROR]";

    @DisplayName("정상 기능 검사")
    @Test
    void 정상_기능_검사() {
        List<Integer> lottoNumbers = List.of(1, 2, 3, 4, 5, 6);

        assertThatCode(() -> LottoValidator.validate(lottoNumbers))
                .doesNotThrowAnyException();
    }

    @DisplayName("로또 개수가 6개가 아닌 경우 예외")
    @Test
    void 로또_개수가_여섯개가_아닌_경우_예외() {
        List<Integer> lottoNumbers = List.of(1, 2, 3, 4, 5, 6, 7);

        assertThatThrownBy(() -> LottoValidator.validate(lottoNumbers))
                .isInstanceOf(IllegalArgumentException.class).hasMessageContaining(ERROR_MESSAGE);
    }

    @DisplayName("개별 로또 번호가 범위를 벗어난 경우 예외")
    @Test
    void 개별_로또_번호가_범위를_벗어난_경우_예외() {
        List<Integer> lottoNumbersMin = List.of(0, 2, 3, 4, 5, 6);
        List<Integer> lottoNumbersMax = List.of(1, 2, 3, 4, 5, 46);

        assertThatThrownBy(() -> LottoValidator.validate(lottoNumbersMin))
                .isInstanceOf(IllegalArgumentException.class).hasMessageContaining(ERROR_MESSAGE);

        assertThatThrownBy(() -> LottoValidator.validate(lottoNumbersMax))
                .isInstanceOf(IllegalArgumentException.class).hasMessageContaining(ERROR_MESSAGE);
    }

    @DisplayName("중복된 숫자가 존재하는 경우 예외")
    @Test
    void 중복된_숫자가_존재하는_경우_예외() {
        List<Integer> lottoNumbers = List.of(1, 2, 3, 3, 5, 6);

        assertThatThrownBy(() -> LottoValidator.validate(lottoNumbers))
                .isInstanceOf(IllegalArgumentException.class).hasMessageContaining(ERROR_MESSAGE);
    }
}