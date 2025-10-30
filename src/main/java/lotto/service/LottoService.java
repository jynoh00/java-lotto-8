package lotto.service;

import camp.nextstep.edu.missionutils.Randoms;

import lotto.common.LottoConstants;
import lotto.model.LottoSimulator;
import lotto.model.Statistics;

import java.util.Collections;
import java.util.List;

public class LottoService {
    public LottoSimulator createSimulator(int purchasePrice, List<Integer> winningNumbers, int bonusNumber) {
        int purchaseCount = getPurchaseCount(purchasePrice);

        return new LottoSimulator(purchaseCount, winningNumbers, bonusNumber);
    }

    public Statistics calculateStatistics(LottoSimulator simulator) {
        return new Statistics(); // 미구현
    }

    public List<Integer> makeLottoNumbers() {
        List<Integer> lottoNumbers = Randoms.pickUniqueNumbersInRange(
                LottoConstants.LOTTO_MIN_NUMBER.getValue(),
                LottoConstants.LOTTO_MAX_NUMBER.getValue(),
                LottoConstants.LOTTO_NUMBERS_LENGTH.getValue()
        );
        Collections.sort(lottoNumbers);

        return lottoNumbers;
    }

    private int getPurchaseCount(int purchasePrice) {
        return purchasePrice / LottoConstants.LOTTO_PRICE.getValue();
    }
}