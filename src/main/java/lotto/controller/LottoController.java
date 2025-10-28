package lotto.controller;

import camp.nextstep.edu.missionutils.Console;
import lotto.validator.InputValidator;
import lotto.view.InputView;
import lotto.view.OutputView;
import java.util.List;

public class LottoController {
    private final InputView inputView;
    private final OutputView outputView;

    private static int purchasePrice;
    private static int bonusNumber;
    private static String beforeWinningNumbers;
    private static List<Integer> winningNumbers;

    public LottoController() {
        this.inputView = new InputView();
        this.outputView = new OutputView();
    }

    public void run(){
        try{
            userInput();
            splitWinningNumbers();
            // 로또 생성 -> 로또 일치 여부 확인 -> 결과값 얻기 구현
            // Output 출력
        }catch(Exception e){
            System.out.println(e.getMessage());
            throw e;
        }finally{
            Console.close();
        }
    }

    private void userInput(){ // 입력값 담당 메서드
        String tmpPurchasePrice = inputView.getUserPurchasePrice();
        purchasePrice = InputValidator.PURCHASE_PRICE.validatePurchasePrice(tmpPurchasePrice);

        String tmpWinningNumbers = inputView.getWinningNumbers();
        beforeWinningNumbers = InputValidator.WINNING_NUMBER.validateWinningNumbers(tmpWinningNumbers); // 전체 포맷 검증
        // 스플릿 후 개별 검증 필요

        String tmpBonusNumber = inputView.getBonusNumber();
        bonusNumber = InputValidator.BONUS_NUMBER.validateBonusNumber(tmpBonusNumber);
    }

    private void splitWinningNumbers() {
        // beforeWinningNumbers 개별 스플릿 후 검증 메서드(LottoValidator) 호출
    }
}

/*
컨트롤러가 최대한 중계 역할만 수행하도록 클래스 분리
*/