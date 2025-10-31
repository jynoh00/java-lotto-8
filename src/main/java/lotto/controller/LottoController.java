package lotto.controller;

import camp.nextstep.edu.missionutils.Console;
import lotto.service.InputService;
import lotto.service.LottoService;
import lotto.model.Statistics;
import lotto.model.LottoSimulator;
import lotto.view.InputView;
import lotto.view.OutputView;

import java.util.List;

public class LottoController {
    private final InputView inputView;
    private final OutputView outputView;
    private final InputService inputService;
    private final LottoService lottoService;

    private LottoSimulator simulator;

    public LottoController() {
        this.inputView = new InputView();
        this.outputView = new OutputView();
        this.inputService = new InputService();
        this.lottoService = new LottoService();
    }

    public void run() {
        try {
            purchaseStage();
            setStage();
            statisticStage();
        } catch (IllegalArgumentException | IllegalStateException e) {
            System.out.println(e.getMessage());
        }
    }

    private void purchaseStage() {
        int purchasePrice = getPurchasePrice();
        simulator = lottoService.createSimulator(purchasePrice);

        outputView.displayPurchasedLottos(simulator.getPurchaseCount(), simulator.getPurchasedLottos());
    }

    private void setStage() {
        List<Integer> winningNumbers = getWinningNumbers();
        int bonusNumber = getBonusNumber();

        simulator.setWinningInfo(winningNumbers, bonusNumber);
    }

    private void statisticStage() {
        Statistics statistics = lottoService.calculateStatistics(simulator);
        outputView.displayStatistics(statistics);
    }

    private int getPurchasePrice() {
        String userPurChasePrice = inputView.readUserPurchasePrice();
        return inputService.validateAndParsePurchasePrice(userPurChasePrice);
    }

    private List<Integer> getWinningNumbers() {
        String userWinningNumbers = inputView.readWinningNumbers();
        return inputService.validateAndParseWinningNumbers(userWinningNumbers);
    }

    private int getBonusNumber() {
        String userBonusNumber = inputView.readBonusNumber();
        return inputService.validateAndParseBonusNumber(userBonusNumber);
    }
}