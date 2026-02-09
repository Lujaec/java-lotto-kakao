package lotto;

import static org.assertj.core.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class RottoNumberTest {
	@Test
	@DisplayName("1 ~ 45까지 로또 번호를 생성할 수 있다")
	void generate_rotto_number_from_1_to_45() {
		List<RottoNumber> rottoNumbers = new ArrayList<>();

		for (int i = 1; i <= 45; i++) {
			rottoNumbers.add(new RottoNumber(i));
		}

		Assertions.assertThat(rottoNumbers)
			.hasSize(45)
			.extracting(RottoNumber::getValue)
			.containsExactlyInAnyOrderElementsOf(generateExpectedNumbers());
	}

	@Test
	@DisplayName("1 ~ 45까지가 아닌 번호는 생성할 수 없다")
	public void cannot_create_invalid_rotto_number() {
		// 1. 범위보다 작은 값 (0) 테스트
		assertThatThrownBy(() -> new RottoNumber(0))
			.isInstanceOf(IllegalArgumentException.class);

		// 2. 범위보다 큰 값 (46) 테스트
		assertThatThrownBy(() -> new RottoNumber(46))
			.isInstanceOf(IllegalArgumentException.class);
	}

	private List<Integer> generateExpectedNumbers() {
		List<Integer> expectedNumbers = new ArrayList<>();
		for (int i = 1; i <= 45; i++) {
			expectedNumbers.add(i);
		}
		return expectedNumbers;
	}
}
