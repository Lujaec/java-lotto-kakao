package lotto;

import java.util.ArrayList;
import java.util.List;

public enum RankView {
	FIFTH(Rank.FIFTH, "3개 일치 (5000원)"),
	FOURTH(Rank.FOURTH, "4개 일치 (50000원)"),
	THIRD(Rank.THIRD, "5개 일치 (1500000원)"),
	SECOND(Rank.SECOND, "5개 일치, 보너스 볼 일치(30000000원)"),
	FIRST(Rank.FIRST, "6개 일치 (2000000000원)");

	private final Rank rank;
	private final String description;

	RankView(Rank rank, String description) {
		this.rank = rank;
		this.description = description;
	}

	public static List<RankView> winningViews() {
		return new ArrayList<>(
			List.of(FIFTH, FOURTH, THIRD, SECOND, FIRST)
		);
	}
	public Rank rank() {
		return rank;
	}

	public String description() {
		return description;
	}
}
