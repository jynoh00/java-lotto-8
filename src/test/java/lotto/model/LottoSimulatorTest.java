package lotto.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;

class LottoSimulatorTest {
    private List<Lotto> purchasedLottos;
    private int purchaseCount;

    @BeforeEach
    void setUp() {
        purchaseCount = 8;
        purchasedLottos = List.of(
                new Lotto(List.of(1, 2, 3, 4, 5, 6)), // 전체 일치
                new Lotto(List.of(1, 2, 3, 4, 5, 7)), // 1개 불일치 + 보너스 일치
                new Lotto(List.of(1, 2, 3, 4, 5, 8)), // 1개 불일치 + 보너스 불일치
                new Lotto(List.of(1, 2, 3, 4, 8, 9)), // 2개 불일치
                new Lotto(List.of(1, 2, 3, 8, 9, 10)), // 3개 불일치
                new Lotto(List.of(1, 2, 8, 9, 10, 11)), // 4개 불일치
                new Lotto(List.of(1, 8, 9, 10, 11, 12)), // 5개 불일치
                new Lotto(List.of(8, 9, 10, 11, 12, 13)) // 전체 불일치
        );
    }

    @DisplayName("로또 시뮬레이터 정상 생성")
    @Test
    void 로또_시뮬레이터_정상_생성() {
        LottoSimulator simulator = new LottoSimulator(purchaseCount, purchasedLottos);

        assertThat(simulator).isNotNull();
        assertThat(simulator.getPurchaseCount()).isEqualTo(purchaseCount);
        assertThat(simulator.getPurchasedLottos()).isEqualTo(purchasedLottos);
    }

    @DisplayName("구매 로또들 정상 반환")
    @Test
    void 구매_로또들_정상_반환() {
        LottoSimulator simulator = new LottoSimulator(purchaseCount, purchasedLottos);
        List<Lotto> lottos = simulator.getPurchasedLottos();

        assertThat(lottos).hasSize(purchaseCount);
        assertThat(lottos).containsExactlyElementsOf(purchasedLottos);
    }

    @DisplayName("당첨 정보 설정 정상적 실행")
    @Test
    void 당첨_정보_설정_정상적_실행() {
        LottoSimulator simulator = new LottoSimulator(purchaseCount, purchasedLottos);
        List<Integer> winningNumbers = List.of(1, 2, 3, 4, 5, 6);
        int bonusNumber = 7;

        simulator.setWinningInfo(winningNumbers, bonusNumber);

        assertThat(simulator.getWinningNumbers()).isEqualTo(winningNumbers);
        assertThat(simulator.getBonusNumber()).isEqualTo(bonusNumber);
    }

    @DisplayName("당첨 번호 정상 반환")
    @Test
    void 당첨_번호_정상_반환() {
        LottoSimulator simulator = new LottoSimulator(purchaseCount, purchasedLottos);
        List<Integer> winningNumbers = List.of(1, 2, 3, 4, 5, 6);
        simulator.setWinningInfo(winningNumbers, 7);

        List<Integer> numbers = simulator.getWinningNumbers();

        assertThat(numbers).containsExactlyElementsOf(winningNumbers);
    }

    @DisplayName("보너스 번호 정상 반환")
    @Test
    void 보너스_번호_정상_반환() {
        LottoSimulator simulator = new LottoSimulator(purchaseCount, purchasedLottos);
        List<Integer> winningNumbers = List.of(1, 2, 3, 4, 5, 6);
        int bonusNumber =  7;
        simulator.setWinningInfo(winningNumbers, bonusNumber);

        int number =  simulator.getBonusNumber();

        assertThat(number).isEqualTo(bonusNumber);
    }

    @DisplayName("보너스 번호가 당첨 번호와 중복될 경우 예외 처리")
    @Test
    void 보너스_번호가_당첨_번호와_중복될_경우_예외_처리() {
        LottoSimulator simulator = new LottoSimulator(purchaseCount, purchasedLottos);
        List<Integer> winningNumbers = List.of(1, 2, 3, 4, 5, 6);
        int bonusNumber = 6;

        assertThatThrownBy(() -> simulator.setWinningInfo(winningNumbers, bonusNumber))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("당첨 번호 설정 전 값 조회 시 null")
    @Test
    void 당첨_번호_설정_전_값_조회_시_널() {
        LottoSimulator simulator = new LottoSimulator(purchaseCount, purchasedLottos);
        List<Integer> winningNumbers = simulator.getWinningNumbers();
        int bonusNumber = simulator.getBonusNumber();

        assertThat(winningNumbers).isNull();
        assertThat(bonusNumber).isEqualTo(0);
    }

    @DisplayName("구매 개수와 로또 목록 크기 일치 확인")
    @Test
    void 구매_개수와_로또_목록_크기_일치_확인() {
        int count = 3;
        List<Lotto> lottos = List.of(
                new Lotto(List.of(1, 2, 3, 4 ,5, 6)),
                new Lotto(List.of(7, 8, 9, 10, 11, 12)),
                new Lotto(List.of(13, 14, 15, 16, 17, 18))
        );
        LottoSimulator simulator = new LottoSimulator(count, lottos);

        assertThat(simulator.getPurchaseCount()).isEqualTo(lottos.size());
    }
}
