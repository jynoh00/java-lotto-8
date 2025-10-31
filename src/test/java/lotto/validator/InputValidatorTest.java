package lotto.validator;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

class InputValidatorTest {
    private static final String ERROR_MESSAGE = "[ERROR]";

    @DisplayName("구매 금액 정상 작동 검사")
    @Test
    void 구매_금액_정상_작동_검사() {
        int result = InputValidator.PURCHASE_PRICE.validatePurchasePrice(5000);
        assertThat(result).isEqualTo(5000);
    }

    @DisplayName("구매 금액이 0 이하면 예외")
    @Test
    void 구매_금액이_영_이하면_예외() {
        assertThatThrownBy(() -> InputValidator.PURCHASE_PRICE.validatePurchasePrice(0))
                .isInstanceOf(IllegalArgumentException.class).hasMessageContaining(ERROR_MESSAGE);

        assertThatThrownBy(() -> InputValidator.PURCHASE_PRICE.validatePurchasePrice(-1000))
                .isInstanceOf(IllegalArgumentException.class).hasMessageContaining(ERROR_MESSAGE);
    }

    @DisplayName("구매 금액이 1000으로 나누어 떨어지지 않으면 예외")
    @Test
    void 구매_금액이_천으로_나누어_떨어지지_않으면_예외() {
        assertThatThrownBy(() -> InputValidator.PURCHASE_PRICE.validatePurchasePrice(1500))
                .isInstanceOf(IllegalArgumentException.class).hasMessageContaining(ERROR_MESSAGE);
    }

    @DisplayName("당첨 번호 정상 작동 검사")
    @Test
    void 당첨_번호_정상_작동_검사() {
        String input = "1, 2, 3, 4 , 5, 6";
        String result = InputValidator.WINNING_NUMBER.validateWinningNumbers(input);
        assertThat(result).isEqualTo(input);
    }

    @DisplayName("당첨 번호 입력이 null이거나 공백이면 예외")
    @Test
    void 당첨_번호_입력이_널이거나_공백이면_예외() {
        assertThatThrownBy(() -> InputValidator.WINNING_NUMBER.validateWinningNumbers(null))
                .isInstanceOf(IllegalArgumentException.class).hasMessageContaining(ERROR_MESSAGE);

        assertThatThrownBy(() -> InputValidator.WINNING_NUMBER.validateWinningNumbers("  "))
                .isInstanceOf(IllegalArgumentException.class).hasMessageContaining(ERROR_MESSAGE);
    }

    @DisplayName("당첨 번호 입력 형식이 잘못된 경우 예외")
    @Test
    void 당첨_번호_입력_형식이_잘못된_경우_예외() {
        String input = "1,2,a,4,5,6";
        assertThatThrownBy(() -> InputValidator.WINNING_NUMBER.validateWinningNumbers(input))
                .isInstanceOf(IllegalArgumentException.class).hasMessageContaining(ERROR_MESSAGE);
    }

    @DisplayName("보너스 번호 정상 작동 검사")
    @Test
    void 보너스_번호_정상_작동_검사() {
        int result = InputValidator.BONUS_NUMBER.validateBonusNumber(7);
        assertThat(result).isEqualTo(7);
    }

    @DisplayName("보너스 번호가 범위를 벗어나면 예외")
    @Test
    void 보너스_번호가_범위를_벗어나면_예외() {
        assertThatThrownBy(() -> InputValidator.BONUS_NUMBER.validateBonusNumber(0))
                .isInstanceOf(IllegalArgumentException.class).hasMessageContaining(ERROR_MESSAGE);

        assertThatThrownBy(() -> InputValidator.BONUS_NUMBER.validateBonusNumber(46))
                .isInstanceOf(IllegalArgumentException.class).hasMessageContaining(ERROR_MESSAGE);
    }

    @DisplayName("보너스 번호가 당첨 번호와 중복인 경우 예외")
    @Test
    void 보너스_번호가_당첨_번호와_중복인_경우_예외() {
        List<Integer> winningNumbers = List.of(1, 2, 3, 4, 5, 6);

        assertThatThrownBy(() -> InputValidator.BONUS_NUMBER.validateRedundancy(3, winningNumbers))
                .isInstanceOf(IllegalArgumentException.class).hasMessageContaining(ERROR_MESSAGE);
    }

    @DisplayName("보너스 번호가 당첨 번호와 중복이 아닌 경우 정상 작동")
    @Test
    void 보너스_번호가_당첨_번호와_중복이_아닌_경우_정상_작동() {
        List<Integer> winningNumbers = List.of(1, 2, 3, 4, 5, 6);

        InputValidator.BONUS_NUMBER.validateRedundancy(7, winningNumbers);
    }
}