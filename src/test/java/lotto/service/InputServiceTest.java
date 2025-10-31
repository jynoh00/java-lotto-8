package lotto.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class InputServiceTest {
    private InputService inputService;
    private static final String ERROR_MESSAGE = "[ERROR]";

    @BeforeEach
    void setUp() {
        inputService = new InputService();
    }

    @DisplayName("구매 금액 검증 및 변환 검사")
    @Test
    void 구매_금액_검증_및_변환_검사() {
        String userInput = "3000 ";

        int result = inputService.validateAndParsePurchasePrice(userInput);

        assertThat(result).isEqualTo(3000);
    }

    @DisplayName("구매 금액이 숫자가 아닌 경우 예외")
    @Test
    void 구매_금액이_숫자가_아닌_경우_예외() {
        assertThatThrownBy(() -> inputService.validateAndParsePurchasePrice("aaa"))
                .isInstanceOf(IllegalArgumentException.class).hasMessageContaining(ERROR_MESSAGE);
    }

    @DisplayName("구매 금액이 1000으로 나누어 떨어지지 않는 경우 예외")
    @Test
    void 구매_금액이_천으로_나누어_떨어지지_않는_경우_예외() {
        assertThatThrownBy(() -> inputService.validateAndParsePurchasePrice("1500"))
                .isInstanceOf(IllegalArgumentException.class).hasMessageContaining(ERROR_MESSAGE);
    }

    @DisplayName("당첨 번호 변환 및 검증 검사")
    @Test
    void 당첨_번호_변환_및_검증_검사() {
        String userInput = "1, 2, 3, 4, 5, 6";
        List<Integer> result = inputService.validateAndParseWinningNumbers(userInput);

        assertThat(result).containsExactly(1, 2, 3, 4, 5, 6);
    }

    @DisplayName("당첨 번호의 개수가 6개가 아닐 경우 예외")
    @Test
    void 당첨_번호의_개수가_여섯개가_아닐_경우_예외() {
        String invalidInput = "1, 2, 3, 4, 5, 6, 7";
        assertThatThrownBy(() -> inputService.validateAndParseWinningNumbers(invalidInput))
                .isInstanceOf(IllegalArgumentException.class).hasMessageContaining(ERROR_MESSAGE);
    }

    @DisplayName("당첨 번호에 중복이 있을 경우 예외")
    @Test
    void 당첨_번호에_중복이_있을_경우_예외() {
        String invalidInput = "1, 2, 2, 3, 4, 5";
        assertThatThrownBy(() -> inputService.validateAndParseWinningNumbers(invalidInput))
                .isInstanceOf(IllegalArgumentException.class).hasMessageContaining(ERROR_MESSAGE);
    }

    @DisplayName("보너스 번호 변환 및 검증 검사")
    @Test
    void 보너스_번호_변환_및_검증_검사() {
        String userInput = "7 ";
        int result = inputService.validateAndParseBonusNumber(userInput);

        assertThat(result).isEqualTo(7);
    }

    @DisplayName("보너스 번호가 숫자가 아닐 경우 예외")
    @Test
    void 보너스_번호가_숫자가_아닐_경우_예외() {
        assertThatThrownBy(() -> inputService.validateAndParseBonusNumber("a"))
                .isInstanceOf(IllegalArgumentException.class).hasMessageContaining(ERROR_MESSAGE);
    }

    @DisplayName("보너스 번호가 범위를 벗어날 경우 예외")
    @Test
    void 보너스_번호가_범위를_벗어날_경우_예외() {
        assertThatThrownBy(() -> inputService.validateAndParseBonusNumber("46"))
                .isInstanceOf(IllegalArgumentException.class).hasMessageContaining(ERROR_MESSAGE);
    }
}