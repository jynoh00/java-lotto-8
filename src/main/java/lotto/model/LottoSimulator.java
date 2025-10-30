package lotto.model;

import lotto.common.LottoConstants;
import lotto.service.LottoService;

import java.util.ArrayList;
import java.util.List;

public class LottoSimulator {
    private final LottoService lottoService;
    private final int purchaseCount;
    private final List<Integer> winningNumbers;
    private final int bonusNumber;
    private final List<Lotto> purchasedLottos = new ArrayList<>();

    public LottoSimulator(int purchaseCount, List<Integer> winningNumbers, int bonusNumber) {
        this.lottoService = new LottoService();
        this.purchaseCount = purchaseCount;
        this.winningNumbers = winningNumbers;
        this.bonusNumber = bonusNumber;

        run();
    }

    public List<Lotto> getPurchasedLottos() {
        return purchasedLottos;
    }

    public int getPurchaseCount() {
        return purchaseCount;
    }

    private void run() {
        makePurchasedLottos();
    }

    private void makePurchasedLottos() {
        for (int i = LottoConstants.ZERO.getValue(); i < purchaseCount; i++) {
            List<Integer> tmpLottoNumbers = lottoService.makeLottoNumbers();
            if (isDuplicate(tmpLottoNumbers)) {
                i--;
                continue;
            }

            purchasedLottos.add(new Lotto(tmpLottoNumbers));
        }
    }

    private boolean isDuplicate(List<Integer> tmpLottoNumbers) {
        String newLottoNumbers = tmpLottoNumbers.toString();

        for (Lotto lotto : purchasedLottos) {
            if (lotto.getNumbers().equals(newLottoNumbers)) return true;
        }

        return false;
    }
}
