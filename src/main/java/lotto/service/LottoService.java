package lotto.service;

import lotto.model.LottoSimulator;
import lotto.model.Statistics;

import java.util.List;

public class LottoService {
    public LottoSimulator createSimulator(int purchasePrice, List<Integer> winningNumbers, int bonusNumber) {
        return new LottoSimulator(); // 미구현
    }

    public Statistics calculateStatistics(LottoSimulator simulator) {
        return new Statistics(); // 미구현
    }
}
