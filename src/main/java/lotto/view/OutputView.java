package lotto.view;

import lotto.common.OutputMessage;
import lotto.model.Lotto;
import lotto.model.Statistics;

import java.util.List;

public class OutputView {
    public void displayPurchasedLottos(int purchaseCount, List<Lotto> Lottos) {
        System.out.println(OutputMessage.PURCHASE_COUNT.format(purchaseCount));
        for (Lotto lotto : Lottos) {
            System.out.println(lotto.getNumbers());
        }
    }

    public void displayStatistics (Statistics statistics) {
        // 미구현
    }
}
