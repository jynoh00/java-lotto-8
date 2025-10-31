package lotto.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

class RankTest {
    @DisplayName("6개 일치 시 1등 판정")
    @Test
    void 여섯개_일치_시_일등_판정() {
        Rank rank = Rank.valueOf(6, false);

        assertThat(rank).isEqualTo(Rank.FIRST);
        assertThat(rank.getPrize()).isEqualTo(2_000_000_000);
    }

    @DisplayName("5개 일치 및 보너스 일치 시 2등 판정")
    @Test
    void 다섯개_일치_및_보너스_일치_시_이등_판정() {
        Rank rank = Rank.valueOf(5, true);

        assertThat(rank).isEqualTo(Rank.SECOND);
        assertThat(rank.getPrize()).isEqualTo(30_000_000);
    }

    @DisplayName("5개 일치 및 보너스 불일치 시 3등 판정")
    @Test
    void 다섯개_일치_및_보너스_불일치_시_삼등_판정() {
        Rank rank = Rank.valueOf(5, false);

        assertThat(rank).isEqualTo(Rank.THIRD);
        assertThat(rank.getPrize()).isEqualTo(1_500_000);
    }

    @DisplayName("4개 일치 시 4등 판정")
    @Test
    void 네개_일치_시_사등_판정() {
        Rank rank = Rank.valueOf(4, false);

        assertThat(rank).isEqualTo(Rank.FOURTH);
        assertThat(rank.getPrize()).isEqualTo(50_000);
    }

    @DisplayName("4개 일치 및 보너스 무시")
    @Test
    void 네개_일치_및_보너스_무시() {
        Rank rankWithBonus = Rank.valueOf(4, true);
        Rank rankWithoutBonus = Rank.valueOf(4, false);

        assertThat(rankWithBonus).isEqualTo(Rank.FOURTH);
        assertThat(rankWithoutBonus).isEqualTo(Rank.FOURTH);
        assertThat(rankWithBonus).isEqualTo(rankWithoutBonus);
    }

    @DisplayName("3개 일치 시 5등 판정")
    @Test
    void 세개_일치_시_오등_판정() {
        Rank rank = Rank.valueOf(3, false);

        assertThat(rank).isEqualTo(Rank.FIFTH);
        assertThat(rank.getPrize()).isEqualTo(5_000);
    }

    @DisplayName("3개 일치 및 보너스 무시")
    @Test
    void 세개_일치_및_보너스_무시() {
        Rank rankWithBonus = Rank.valueOf(3, true);
        Rank rankWithoutBonus = Rank.valueOf(3, false);

        assertThat(rankWithBonus).isEqualTo(Rank.FIFTH);
        assertThat(rankWithoutBonus).isEqualTo(Rank.FIFTH);
        assertThat(rankWithBonus).isEqualTo(rankWithoutBonus);
    }

    @DisplayName("2개 이하 일치 시 낙첨")
    @ParameterizedTest
    @CsvSource({
            "2, false",
            "2, true",
            "1, false",
            "1, true",
            "0, false",
            "0, true"
    })
    void 두개_이하_일치_시_낙첨(int matchCount, boolean matchBonus) {
        Rank rank = Rank.valueOf(matchCount, matchBonus);

        assertThat(rank).isEqualTo(Rank.NONE);
        assertThat(rank.getPrize()).isEqualTo(0);
    }

    @DisplayName("등수 별 상금 정확도 확인")
    @Test
    void 등수_별_상금_정확도_확인() {
        assertThat(Rank.FIRST.getPrize()).isEqualTo(2_000_000_000);
        assertThat(Rank.SECOND.getPrize()).isEqualTo(30_000_000);
        assertThat(Rank.THIRD.getPrize()).isEqualTo(1_500_000);
        assertThat(Rank.FOURTH.getPrize()).isEqualTo(50_000);
        assertThat(Rank.FIFTH.getPrize()).isEqualTo(5_000);
        assertThat(Rank.NONE.getPrize()).isEqualTo(0);
    }

    @DisplayName("6개 일치 시 보너스 번호 상관 없음")
    @Test
    void 여섯개_일치_시_보너스_번호_상관_없음() {
        Rank rankWithBonus = Rank.valueOf(6, true);
        Rank rankWithoutBonus = Rank.valueOf(6, false);

        assertThat(rankWithBonus).isEqualTo(Rank.FIRST);
        assertThat(rankWithoutBonus).isEqualTo(Rank.FIRST);
        assertThat(rankWithBonus).isEqualTo(rankWithoutBonus);
    }

    @DisplayName("5개 일치할 때만 보너스 번호 영향")
    @Test
    void 다섯개_일치할_때만_보너스_번호_영향() {
        Rank rankWithBonus = Rank.valueOf(5, true);
        Rank rankWithoutBonus = Rank.valueOf(5, false);

        assertThat(rankWithBonus).isEqualTo(Rank.SECOND);
        assertThat(rankWithoutBonus).isEqualTo(Rank.THIRD);
        assertThat(rankWithBonus).isNotEqualTo(rankWithoutBonus);
    }

    @DisplayName("낙첨의 경우 상금은 0")
    @Test
    void 낙첨의_경우_상금은_영() {
        Rank rank = Rank.valueOf(0, false);

        assertThat(rank).isEqualTo(Rank.NONE);
        assertThat(rank.getPrize()).isEqualTo(0);
    }
}
