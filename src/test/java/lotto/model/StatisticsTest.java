package lotto.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.EnumMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;


class StatisticsTest {
    private Map<Rank, Integer> rankCounts;

    @BeforeEach
    void setUp() {
        rankCounts = new EnumMap<>(Rank.class);

        rankCounts.put(Rank.FIRST, 1);
        rankCounts.put(Rank.SECOND, 0);
        rankCounts.put(Rank.THIRD, 2);
        rankCounts.put(Rank.FOURTH, 3);
        rankCounts.put(Rank.FIFTH, 4);
        rankCounts.put(Rank.NONE, 10);
    }

    @DisplayName("수익률 계산 기능 검사")
    @Test
    void 수익률_계산_기능_검사() {
        int purchasePrice = 20_000;
        Statistics statistics = new Statistics(rankCounts, purchasePrice);

        double outputRate = statistics.getOutputRate();

        long expectedTotalPrice = Rank.FIRST.getPrize() * 1
                + Rank.SECOND.getPrize() * 0
                + Rank.THIRD.getPrize() * 2
                + Rank.FOURTH.getPrize() * 3
                + Rank.FIFTH.getPrize() * 4
                + Rank.NONE.getPrize() * 10;

        double expectedOutputRate = (double) expectedTotalPrice / purchasePrice * 100;

        assertThat(outputRate).isEqualTo(expectedOutputRate);
    }

    @DisplayName("랭크 별 당첨 개수 반환 검사")
    @Test
    void 랭크_별_당첨_개수_반환_검사() {
        Statistics statistics = new Statistics(rankCounts, 20_000);

        assertThat(statistics.getCountByRank(Rank.FIRST)).isEqualTo(1);
        assertThat(statistics.getCountByRank(Rank.THIRD)).isEqualTo(2);
        assertThat(statistics.getCountByRank(Rank.NONE)).isEqualTo(10);
    }

    @DisplayName("랭크에 없는 값의 경우 0을 반환")
    @Test
    void 랭크에_없는_값의_경우_영을_반환() {
        Map<Rank, Integer> emptyRankCounts = new EnumMap<>(Rank.class);
        Statistics statistics = new Statistics(emptyRankCounts, 5_000);

        assertThat(statistics.getCountByRank(Rank.FIRST)).isEqualTo(0);
        assertThat(statistics.getCountByRank(Rank.SECOND)).isEqualTo(0);
        assertThat(statistics.getCountByRank(Rank.THIRD)).isEqualTo(0);
        assertThat(statistics.getCountByRank(Rank.FOURTH)).isEqualTo(0);
        assertThat(statistics.getCountByRank(Rank.FIFTH)).isEqualTo(0);
        assertThat(statistics.getCountByRank(Rank.NONE)).isEqualTo(0);
    }
}
