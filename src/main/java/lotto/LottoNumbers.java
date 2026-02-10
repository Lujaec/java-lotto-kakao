package lotto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class LottoNumbers {
	private static final int LOTTO_NUMBER_COUNT = 6;
	private static final int MIN_LOTTO_NUMBER = 1;
	private static final int MAX_LOTTO_NUMBER = 45;
	private final ArrayList<LottoNumber> numbers;

	public LottoNumbers(ArrayList<LottoNumber> numbers) {
		validateNumberCount(numbers);
		validateUniqueNumbers(numbers);
		this.numbers = new ArrayList<>(numbers);
		sortNumbers();
	}

	public static LottoNumbers random() {
		ArrayList<Integer> numberCandidates = createNumberCandidates();
		Collections.shuffle(numberCandidates);
		return new LottoNumbers(convertToLottoNumbers(numberCandidates));
	}

	public ArrayList<LottoNumber> values() {
		return new ArrayList<>(numbers);
	}

	public boolean contains(LottoNumber lottoNumber) {
		return numbers.contains(lottoNumber);
	}

	public int matchCount(LottoNumbers otherNumbers) {
		return (int) numbers.stream()
			.filter(otherNumbers::contains)
			.count();
	}

	private void validateNumberCount(ArrayList<LottoNumber> numbers) {
		if (numbers.size() != LOTTO_NUMBER_COUNT) {
			throw new IllegalArgumentException("로또 번호는 6개여야 합니다.");
		}
	}

	private void validateUniqueNumbers(ArrayList<LottoNumber> numbers) {
		ArrayList<LottoNumber> uniqueNumbers = new ArrayList<>();
		for (LottoNumber lottoNumber : numbers) {
			validateNotDuplicated(uniqueNumbers, lottoNumber);
			uniqueNumbers.add(lottoNumber);
		}
	}

	private void validateNotDuplicated(ArrayList<LottoNumber> uniqueNumbers, LottoNumber lottoNumber) {
		if (uniqueNumbers.contains(lottoNumber)) {
			throw new IllegalArgumentException("로또 번호는 중복될 수 없습니다.");
		}
	}

	private static ArrayList<Integer> createNumberCandidates() {
		ArrayList<Integer> numberCandidates = new ArrayList<>();
		for (int lottoNumberValue = MIN_LOTTO_NUMBER; lottoNumberValue <= MAX_LOTTO_NUMBER; lottoNumberValue++) {
			numberCandidates.add(lottoNumberValue);
		}
		return numberCandidates;
	}

	private static ArrayList<LottoNumber> convertToLottoNumbers(ArrayList<Integer> numberCandidates) {
		ArrayList<LottoNumber> lottoNumbers = new ArrayList<>();
		for (Integer number : numberCandidates.subList(0, LOTTO_NUMBER_COUNT)) {
			lottoNumbers.add(new LottoNumber(number));
		}
		return lottoNumbers;
	}

	private void sortNumbers() {
		numbers.sort(Comparator.comparingInt(LottoNumber::getValue));
	}
}
