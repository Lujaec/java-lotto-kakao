package model.random;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import model.Amount;

import static model.Amount.LOTTO_UNIT_PRICE;

public class LottoGenerator {
    private final List<LottoNumbers> automaticLottoNumberList;
    private final List<LottoNumbers> manaulLottoNumberList;
    private List<LottoNumbers> entireLottoNumberList;

    private LottoGenerator(List<LottoNumbers> automaticLottoNumberList, List<LottoNumbers> manualLottoNumberList) {
        this.automaticLottoNumberList = new ArrayList<>(automaticLottoNumberList);
        this.manaulLottoNumberList = new ArrayList<>(manualLottoNumberList);
        this.entireLottoNumberList = Stream.concat(automaticLottoNumberList.stream(), manaulLottoNumberList.stream())
                .collect(Collectors.toList());
    }

    public static LottoGenerator generate(List<String> manualString, Amount amount) {
        List<LottoNumbers> automaticLottoNumberList = generateAutomaticLottoNumbers(amount);
        List<LottoNumbers> manualLottoNumberList = generateManualLottoNumbers(manualString);

        return new LottoGenerator(automaticLottoNumberList, manualLottoNumberList);
    }

    private static List<LottoNumbers> generateAutomaticLottoNumbers(Amount amount) {
        int automaticCount = amount.getAmount() / LOTTO_UNIT_PRICE;
        List<LottoNumbers> automaticLottoNumberList = new ArrayList<>();

        for (int i = 0; i < automaticCount; i++) {
            LottoNumbers lottoNumbers =
                    new LottoNumbers(LottoNumbers.generateRandNums(new RandomNumberGenerator() {
                    }));
            automaticLottoNumberList.add(lottoNumbers);
        }

        return automaticLottoNumberList;
    }

    private static List<LottoNumbers> generateManualLottoNumbers(List<String> manualString) {
        Pattern lineSplitPattern = Pattern.compile("\n");

        List<LottoNumbers> manualLottoNumberList = manualString.stream()
                .flatMap(lineSplitPattern::splitAsStream)
                .filter(Objects::nonNull)
                .map(LottoNumbers::createBallNumbers)
                .map(LottoNumbers::new)
                .collect(Collectors.toList());

        return manualLottoNumberList;
    }

    public int calculateAutomaticAmount() {
        return automaticLottoNumberList.size();
    }

    public int calculateManualAmount() {
        return manaulLottoNumberList.size();
    }

    public List<LottoNumbers> getAutomaticLottoNumberList() {
        return new ArrayList<>(automaticLottoNumberList);
    }

    public List<LottoNumbers> getEntireLottoNumberList() {

        return new ArrayList<>(entireLottoNumberList);
    }

    public int getEntireLottoSize() {
        return entireLottoNumberList.size();
    }
}
