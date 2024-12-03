package sk.thenoen.aoc2024.day3;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import sk.thenoen.aoc.Utils;

public class SolutionPart2 {

	public long solvePart2(String inputPath) {
		final ArrayList<String> lines = Utils.loadLines(inputPath);

		final String memoryString = lines.stream().collect(Collectors.joining());

		final String pattern = String.join("|", List.of("mul\\((\\d+),(\\d+)\\)",
														"do\\(\\)",
														"don't\\(\\)"));

		int sum = Pattern.compile(pattern)
						 .matcher(memoryString)
						 .results().mapToInt(match -> {
					final String group = match.group();
					System.out.println(group);
//					final int o1 = Integer.parseInt(match.group(1));
//					final int o2 = Integer.parseInt(match.group(2));
					return 1;
					//			sum += value;
				})
						 .sum();

		return sum;
	}
}
