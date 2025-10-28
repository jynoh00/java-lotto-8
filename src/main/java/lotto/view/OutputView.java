package lotto.view;

import lotto.model.Lotto;
import lotto.model.Statistics;

import java.util.List;

public class OutputView {
    public void displayPurchasedLottos(List<Lotto> Lottos) {
        // 임시 구현
        for (Lotto lotto : Lottos) {
            System.out.println(lotto.getNumbers());
        }
    }

    public void displayStatistics (Statistics statistics) {
        // 미구현
    }
}
