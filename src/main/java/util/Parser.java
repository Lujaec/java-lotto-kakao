package util;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Parser {
	public static List<Integer> parseNumbersWithComma(String input) {
		return Arrays.stream(input.replaceAll(" ", "").split(","))
			.mapToInt(Integer::parseInt)
			.boxed()
			.collect(Collectors.toList());
	}
}
