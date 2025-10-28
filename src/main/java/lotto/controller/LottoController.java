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

    public LottoController() {
        this.inputView = new InputView();
        this.outputView = new OutputView();
        this.inputService = new InputService();
        this.lottoService = new LottoService();
    }

    public void run(){
        try{
            LottoSimulator simulator = CreateLottoSimulator();
            startSimulation(simulator);
        }catch(Exception e){
            System.out.println(e.getMessage());
            throw e;
        }finally{
            Console.close();
        }
    }

    private LottoSimulator CreateLottoSimulator() {
        int purchasePrice = getPurchasePrice();
        List<Integer> winningNumbers = getWinningNumbers();
        int bonusNumber = getBonusNumber();

        return lottoService.createSimulator(purchasePrice, winningNumbers, bonusNumber);
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

    private void startSimulation(LottoSimulator simulator) {
        outputView.displayPurchasedLottos(simulator.getPurchasedLottos());
        Statistics statistics = lottoService.calculateStatistics(simulator);
        outputView.displayStatistics(statistics);
    }
}

/*
컨트롤러가 최대한 중계 역할만 수행하도록 클래스 분리
*/