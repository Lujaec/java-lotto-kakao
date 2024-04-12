package domain;

import java.util.*;
import java.util.stream.Collectors;

public class Lotto {
    public static final int LOTTO_NUMBER_SIZE = 6;

    private final List<LottoBall> lottoNumbers;

    public Lotto(List<LottoBall> lottoNumbers) {
        validateSize(lottoNumbers);
        validateDistinction(lottoNumbers);

        Collections.sort(lottoNumbers);
        this.lottoNumbers = lottoNumbers;
    }

    public boolean contains(LottoBall lottoNumber) {
        return lottoNumbers.contains(lottoNumber);
    }

    private void validateDistinction(List<LottoBall> lottoNumbers) {
        Set<LottoBall> ballSet = new HashSet<>(lottoNumbers);

        if (ballSet.size() != lottoNumbers.size()) {
            throw new IllegalArgumentException("로또 번호는 중복될 수 없습니다.");
        }
    }

    private void validateSize(List<LottoBall> lottoNumbers) {
        if (lottoNumbers.size() != LOTTO_NUMBER_SIZE) {
            throw new IllegalArgumentException("로또 번호는 6개로 이뤄져야 합니다!");
        }
    }

    public int getMatchCount(Lotto compareLotto) {
        return (int) lottoNumbers.stream().filter(compareLotto::contains).count();
    }

    public List<LottoBall> getLottoNumbers() {
        return lottoNumbers;
    }

    public static Lotto parseLotto(String[] lines) {
        List<LottoBall> lottoBalls = Arrays.stream(lines)
                .map(Integer::parseInt)
                .map(LottoBall::valueOf)
                .collect(Collectors.toList());

        return new Lotto(lottoBalls);
    }
}
