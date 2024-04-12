package lotto.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.EnumMap;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LottoGame {

    private final List<LottoGroup> lottoGroups;
    private final LottoMachine lottoMachine;

    public LottoGame(final AutoGenerator autoGenerator, final List<String> manualLottoInputs, final int autoLottoCount) {
        this.lottoMachine = new LottoMachine(autoGenerator);
        this.lottoGroups = new ArrayList<>();

        addManualLottos(manualLottoInputs);
        addAutoLottos(autoLottoCount);
    }

    private void addManualLottos(final List<String> manualLottoInputs) {
        manualLottoInputs.forEach(this::addManualLotto);
    }

    private void addManualLotto(final String manualLottoInput) {
        final List<Integer> customLotto = Arrays.stream(manualLottoInput.split(", "))
                .map(Integer::parseInt)
                .collect(Collectors.toUnmodifiableList());
        lottoGroups.add(lottoMachine.manualGenerate(customLotto));
    }

    private void addAutoLottos(final int count) {
        lottoGroups.addAll(
                Stream.generate(lottoMachine::autoGenerate)
                        .limit(count)
                        .collect(Collectors.toUnmodifiableList())
        );
    }

    public List<LottoGroup> getLottoGroups() {
        return lottoGroups;
    }

    public WinningStatistics makeResult(final WinningGroup winningGroup) {
        return new WinningStatistics(lottoGroups.stream()
                .collect(Collectors.groupingBy(
                        winningGroup::calculateRoundResult,
                        () -> new EnumMap<>(LottoRoundResult.class),
                        Collectors.summingInt(it -> 1)
                ))
        );
    }
}
