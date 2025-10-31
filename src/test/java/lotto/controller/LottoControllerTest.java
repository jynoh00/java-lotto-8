package lotto.controller;

import camp.nextstep.edu.missionutils.test.NsTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static camp.nextstep.edu.missionutils.test.Assertions.assertSimpleTest;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class LottoControllerTest extends NsTest {
    private static final String ERROR_MESSAGE = "[ERROR]";

    @DisplayName("정상적인_실행_프로세스")
    @Test
    void 정상적인_실행_프로세스() {
        assertSimpleTest(() -> {
            run("8000", "1,2,3,4,5,6", "7");
            assertThat(output()).contains(
                    "8개를 구매했습니다",
                    "당첨 통계",
                    "총 수익률은"
            );
        });
    }

    @DisplayName("형식에 맞지 않는 입력 예외")
    @Test
    void 형식에_맞지_않는_입력_예외() {
        assertSimpleTest(() -> {
            assertThatThrownBy(() -> runException("abc"))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining(ERROR_MESSAGE);
        });
    }

    @DisplayName("1000원 단위가 아닌 구매 입력 예외")
    @Test
    void 천원_단위가_아닌_구매_입력_예외() {
        assertSimpleTest(() -> {
            assertThatThrownBy(() -> runException("1500"))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining(ERROR_MESSAGE);
        });
    }

    @DisplayName("잘못된 당첨 번호 개수 입력 예외")
    @Test
    void 잘못된_당첨_번호_개수_입력_예외() {
        assertSimpleTest(() -> {
            assertThatThrownBy(() -> runException("8000", "1,2,3,4,5"))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining(ERROR_MESSAGE);
        });
    }

    @DisplayName("중복된 당첨 번호 입력 예외")
    @Test
    void 중복된_당첨_번호_입력_예외() {
        assertSimpleTest(() -> {
            assertThatThrownBy(() -> runException("8000", "1,2,3,4,5,5"))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining(ERROR_MESSAGE);
        });
    }

    @DisplayName("범위를 벗어난 당첨 번호 입력 예외")
    @Test
    void 범위를_벗어난_당첨_번호_입력_예외() {
        assertSimpleTest(() -> {
            assertThatThrownBy(() -> runException("8000", "1,2,3,4,5,46"))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining(ERROR_MESSAGE);
        });
    }

    @DisplayName("보너스 번호가 당첨 번호와 중복 시 예외")
    @Test
    void 보너스_번호가_당첨_번호와_중복_시_예외() {
        assertSimpleTest(() -> {
            assertThatThrownBy(() -> runException("8000", "1,2,3,4,5,6", "6"))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining(ERROR_MESSAGE);
        });
    }

    @DisplayName("보너스 번호가 범위를 벗어날 때 예외")
    @Test
    void 보너스_번호가_범위를_벗어날_때_예외() {
        assertSimpleTest(() -> {
            assertThatThrownBy(() -> runException("8000", "1,2,3,4,5,6", "46"))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining(ERROR_MESSAGE);
        });
    }

    @DisplayName("최소 구매 금액")
    @Test
    void 최소_구매_금액() {
        assertSimpleTest(() -> {
            run("1000", "1,2,3,4,5,6", "7");
            assertThat(output()).contains("1개를 구매했습니다");
        });
    }

    @DisplayName("다수의 로또 구매")
    @Test
    void 다수의_로또_구매() {
        assertSimpleTest(() -> {
            run("14000", "1,2,3,4,5,6", "7");
            assertThat(output()).contains("14개를 구매했습니다");
        });
    }

    @Override
    protected void runMain() {
        LottoController controller = new LottoController();
        controller.run();
    }
}