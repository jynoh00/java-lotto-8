package lotto.model;

import java.util.Map;

public class Statistics {
    private final Map<Rank, Integer> rankCounts;
    private final double outputRate;

    public Statistics(Map<Rank, Integer> rankCounts, int purchasePrice) {
        this.rankCounts = rankCounts;
        this.outputRate = calculateOutputRate(rankCounts, purchasePrice);
    }

    private double calculateOutputRate(Map<Rank, Integer> rankCounts, int purchasePrice) {
        long totalPrize = 0;
        for (Map.Entry<Rank, Integer> entry : rankCounts.entrySet()) {
            Rank rank = entry.getKey();
            int count = entry.getValue();
            if (rank != Rank.NONE) {
                totalPrize += rank.getPrize() * count;
            }
        }

        return (double) totalPrize / purchasePrice * 100;
    }

    public double getOutputRate() {
        return outputRate;
    }

    public int getCountByRank(Rank rank) {
        return rankCounts.getOrDefault(rank, 0);
    }
}