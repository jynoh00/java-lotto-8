package lotto.view;

import lotto.common.OutputMessage;
import lotto.model.Lotto;
import lotto.model.Rank;
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
        System.out.println(OutputMessage.WINNING_STATISTICS.getMessage());
        System.out.println(OutputMessage.MATCH_RESULT.format(
                3, formatPrize(5_000), statistics.getCountByRank(Rank.FIFTH)));
        System.out.println(OutputMessage.MATCH_RESULT.format(
                4, formatPrize(50_000), statistics.getCountByRank(Rank.FOURTH)));
        System.out.println(OutputMessage.MATCH_RESULT.format(
                5, formatPrize(1_500_000), statistics.getCountByRank(Rank.THIRD)));
        System.out.println(OutputMessage.MATCH_RESULT_WITH_BONUS.format(
                5, formatPrize(30_000_000), statistics.getCountByRank(Rank.SECOND)));
        System.out.println(OutputMessage.MATCH_RESULT.format(
                6, formatPrize(2_000_000_000), statistics.getCountByRank(Rank.FIRST)));

        System.out.println(OutputMessage.TOTAL_RETURN_RATE.format(statistics.getOutputRate()));
    }

    private String formatPrize(int prize) {
        return String.format("%,d", prize);
    }
}
