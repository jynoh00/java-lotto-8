package lotto.service;

import lotto.common.LottoConstants;
import lotto.model.Lotto;
import lotto.model.LottoSimulator;
import lotto.model.Rank;
import lotto.model.Statistics;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class LottoServiceTest {
    private LottoService lottoService;

    @BeforeEach
    void setUp() {
        lottoService = new LottoService();
    }

    @DisplayName("구입 금액에 따른 올바른 로또 개수 생성")
    @Test
    void 구입_금액에_따른_올바른_로또_개수_생성() {
        int purchasePrice = 5000;

        LottoSimulator simulator = lottoService.createSimulator(purchasePrice);

        int expectedCount = purchasePrice / LottoConstants.LOTTO_PRICE.getValue();

        assertThat(simulator.getPurchaseCount()).isEqualTo(expectedCount);
        assertThat(simulator.getPurchasedLottos()).hasSize(expectedCount);
    }

    @DisplayName("올바른 통계 계산 테스트")
    @Test
    void 올바른_통계_계산_테스트() {
        LottoSimulator simulator = new LottoSimulator(
                2,
                List.of(
                        new Lotto(List.of(1, 2, 3, 4, 5, 6)),
                        new Lotto(List.of(1, 2, 3, 4, 5, 7))
                )
        );
        simulator.setWinningInfo(List.of(1, 2, 3, 4, 5, 6), 7);

        Statistics statistics = lottoService.calculateStatistics(simulator);

        assertThat(statistics.getCountByRank(Rank.FIRST)).isEqualTo(1);
        assertThat(statistics.getCountByRank(Rank.SECOND)).isEqualTo(1);
        assertThat(statistics.getCountByRank(Rank.NONE)).isZero();

        double outputRate = statistics.getOutputRate();
        assertThat(outputRate).isEqualTo(
                (double) (Rank.FIRST.getPrize() + Rank.SECOND.getPrize())
                        / (2 * LottoConstants.LOTTO_PRICE.getValue()) * 100
        );
    }

    @DisplayName("구매 금액에 따른 올바른 구매 횟수 검사")
    @Test
    void 구매_금액에_따른_올바른_구매_횟수_검사() {
        int purchasePrice = 16000;

        LottoSimulator simulator = lottoService.createSimulator(purchasePrice);

        assertThat(simulator.getPurchaseCount())
                .as("16000원 -> 16장 구매")
                .isEqualTo(16);
    }

    @DisplayName("생성된 로또 번호 개수 및 고유성 검사")
    @Test
    void 생성된_로또_번호_개수_및_고유성_검사() {
        int purchasePrice = 1000;

        LottoSimulator simulator = lottoService.createSimulator(purchasePrice);
        Lotto lotto = simulator.getPurchasedLottos().get(0);

        List<Integer> numbers = lotto.getNumbers();
        assertThat(numbers).hasSize(LottoConstants.LOTTO_NUMBERS_LENGTH.getValue());
        assertThat(numbers).doesNotHaveDuplicates();
        assertThat(numbers).allMatch(n ->
                n >= LottoConstants.LOTTO_MIN_NUMBER.getValue() &&
                        n <= LottoConstants.LOTTO_MAX_NUMBER.getValue()
        );
    }
}