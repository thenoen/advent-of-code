package sk.thenoen.aoc2024.day3;

import java.util.ArrayList;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import sk.thenoen.aoc.Utils;

public class SolutionPart1 {

	public long solvePart1(String inputPath) {
		final ArrayList<String> lines = Utils.loadLines(inputPath);

		final String memoryString = lines.stream().collect(Collectors.joining());

		int sum = 0;

//		Pattern.compile("mul\\((\\d+),(\\d+)\\)").matcher(memoryString).results().forEach(match -> {
//			final String group = match.group();
//			final int o1 = Integer.parseInt(match.group(1));
//			final int o2 = Integer.parseInt(match.group(2));
//			sum += o1 * o2;
//			//			sum += value;
//		});

		sum = Pattern.compile("mul\\((\\d+),(\\d+)\\)")
								.matcher(memoryString)
								.results().mapToInt(match -> {
					final String group = match.group();
					final int o1 = Integer.parseInt(match.group(1));
					final int o2 = Integer.parseInt(match.group(2));
					return o1 * o2;
					//			sum += value;
				})
								.sum();

		return sum;
	}
}
