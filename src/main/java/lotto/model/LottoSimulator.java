package lotto.model;

import lotto.validator.InputValidator;

import java.util.List;

public class LottoSimulator {
    private final int purchaseCount;
    private final List<Lotto> purchasedLottos;
    private List<Integer> winningNumbers;
    private int bonusNumber;

    public LottoSimulator(int purchaseCount, List<Lotto> purchasedLottos) {
        this.purchaseCount = purchaseCount;
        this.purchasedLottos = purchasedLottos;
    }

    public void setWinningInfo(List<Integer> winningNumbers, int bonusNumber) {
        InputValidator.BONUS_NUMBER.validateRedundancy(bonusNumber, winningNumbers);
        this.winningNumbers = winningNumbers;
        this.bonusNumber = bonusNumber;
    }

    public List<Lotto> getPurchasedLottos() {
        return purchasedLottos;
    }

    public int getPurchaseCount() {
        return purchaseCount;
    }

    public List<Integer> getWinningNumbers() {
        return winningNumbers;
    }

    public int getBonusNumber() {
        return bonusNumber;
    }
}