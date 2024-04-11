package model;

import model.vo.LottoBall;
import view.InputValidator;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class UserLotto {

    private static final LottoMachine lottoMachine = new LottoMachine();
    private static final Strategy strategy = new StrategyImpl();
    private List<LottoBall> balls;

    private UserLotto(List<Integer> numbers) {
        validateNumbers(numbers);

        this.balls = numbers.stream()
                .map(LottoBall::new)
                .collect(Collectors.toUnmodifiableList());
    }

    public List<LottoBall> getBalls() {
        return balls;
    }

    private void validateNumbers(List<Integer> numbers) {
        InputValidator.numberSizeCheck(numbers);

        for (int number : numbers) {
            InputValidator.rangeNumberCheck(number);
        }
    }

    public List<Integer> getLottoBallNumbers() {
        return balls.stream()
                .map(LottoBall::getNumber)
                .collect(Collectors.toUnmodifiableList());
    }

    public static UserLotto create(List<Integer> numbers) {
        return new UserLotto(numbers);
    }

    public static UserLotto create(String input) {
        List<Integer> numbers = Arrays.stream(input.replaceAll(" ", "").split(","))
                .map(Integer::parseInt)
                .collect(Collectors.toList());

        return new UserLotto(numbers);
    }

    public static List<UserLotto> generateUserLottos(List<String> inputManualLottos, int purchaseAmount) {
        List<UserLotto> manualLottos = inputManualLottos.stream()
                .map(UserLotto::create)
                .collect(Collectors.toList());

        List<UserLotto> autoLottos = generateAutoUserLotto(purchaseAmount);

        return Stream.concat(manualLottos.stream(), autoLottos.stream())
                .collect(Collectors.toList());
    }

    private static List<UserLotto> generateAutoUserLotto(int purchaseAmount) {
        return Stream.generate(() -> lottoMachine.extractLottoNumbers(strategy))
                .limit(purchaseAmount)
                .collect(Collectors.toList());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserLotto userLotto = (UserLotto) o;
        return Objects.equals(balls, userLotto.balls);
    }

    @Override
    public int hashCode() {
        return Objects.hash(balls);
    }
}
