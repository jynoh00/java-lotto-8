package lotto.common;

public enum OutputMessage {
    PURCHASE_COUNT("%d개를 구매했습니다."),
    WINNING_STATISTICS("당첨 통계\n---"),
    MATCH_RESULT("%d개 일치 (%s원) - %d개"),
    MATCH_RESULT_WITH_BONUS("%d개 일치, 보너스 볼 일치 (%s원) - %d개"),
    TOTAL_RETURN_RATE("총 수익률은 %.1f%%입니다.");

    private final String format;

    OutputMessage(String format) {
        this.format = format;
    }

    public String getMessage() {
        return format;
    }

    public String format(Object... args) {
        return String.format(format, args);
    }
}