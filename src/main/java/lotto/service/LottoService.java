package lotto.service;

import camp.nextstep.edu.missionutils.Randoms;

import lotto.common.LottoConstants;
import lotto.model.Lotto;
import lotto.model.LottoSimulator;
import lotto.model.Rank;
import lotto.model.Statistics;

import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public class LottoService {
    public LottoSimulator createSimulator(int purchasePrice) {
        int purchaseCount = getPurchaseCount(purchasePrice);
        return new LottoSimulator(purchaseCount, makePurchasedLottos(purchaseCount));
    }

    public Statistics calculateStatistics(LottoSimulator simulator) {
        Map<Rank, Integer> rankCounts = new HashMap<>();
        for (Rank rank : Rank.values()) {
            rankCounts.put(rank, 0);
        }

        for (Lotto lotto : simulator.getPurchasedLottos()) {
            Rank rank = calculateRank(lotto, simulator.getWinningNumbers(), simulator.getBonusNumber());
            rankCounts.put(rank, rankCounts.get(rank) + 1);
        }

        int totalPurchaseAmount = simulator.getPurchaseCount() * LottoConstants.LOTTO_PRICE.getValue();
        return new Statistics(rankCounts, totalPurchaseAmount);
    }

    private Rank calculateRank(Lotto lotto, List<Integer> winningNumbers, int bonusNumber) {
        int matchCount = countMatches(lotto.getNumbers(), winningNumbers);
        boolean matchBonus = lotto.getNumbers().contains(bonusNumber);

        return Rank.valueOf(matchCount, matchBonus);
    }

    private int countMatches(List<Integer> numbers, List<Integer> winningNumbers) {
        int count = 0;
        for (int number : numbers) {
            if (winningNumbers.contains(number)) count++;
        }

        return count;
    }

    private List<Lotto> makePurchasedLottos(int purchaseCount) {
        List<Lotto> purchasedLottos = new ArrayList<>();
        for (int i = LottoConstants.ZERO.getValue(); i < purchaseCount; i++) {
            List<Integer> numbers = makeLottoNumbers();
            purchasedLottos.add(new Lotto(numbers));
        }

        return purchasedLottos;
    }

    private List<Integer> makeLottoNumbers() {
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