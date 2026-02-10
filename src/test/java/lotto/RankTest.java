package lotto;

import java.util.HashMap;
import java.util.Map;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class RankTest {
	private final Map<String, Long> mapper = new HashMap<>() {{
		put("FIRST", 2000000000L);
		put("SECOND", 30000000L);
		put("THIRD", 1500000L);
		put("FOURTH", 50000L);
		put("FIFTH", 5000L);
		put("OTHER", 0L);
	}};

	@Test
	@DisplayName("ENUM 필드와 값이 일치하는지 확인")
	public void test_enum(){
		for (Map.Entry<String, Long> map: mapper.entrySet()){
			Assertions.assertThat(Rank.valueOf(map.getKey()).prizeMoney()).isEqualTo(map.getValue());
		}
	}

}