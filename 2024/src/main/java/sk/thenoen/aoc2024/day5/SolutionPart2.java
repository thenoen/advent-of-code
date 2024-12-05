package sk.thenoen.aoc2024.day5;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import sk.thenoen.aoc.Utils;

public class SolutionPart2 {

	public long solve(String inputPath) {
		final ArrayList<String> lines = Utils.loadLines(inputPath);

		List<Rule> rules = new ArrayList<>();

		int i = 0;
		while (!lines.get(i).isEmpty()) {
			final String[] parts = lines.get(i).split("\\|");
			rules.add(new Rule(Integer.parseInt(parts[0]), Integer.parseInt(parts[1])));
			i++;
		}
		i++; // skip empty line

		List<List<Integer>> manuals = new ArrayList<>();
		for (int j = i; j < lines.size(); j++) {
			List<Integer> pages = new ArrayList<>();
			Arrays.stream(lines.get(j)
							   .split(","))
				  .forEach(part -> {
					  pages.add(Integer.parseInt(part));
				  });
			manuals.add(pages);
		}

		final List<List<Integer>> invalidManuals = manuals.stream()
														  .filter(pages -> rules.stream()
																				.anyMatch(rule -> {
																					int before = rule.before;
																					int after = rule.after;

																					int beforeIndex = pages.indexOf(before);
																					int afterIndex = pages.indexOf(after);

																					if (beforeIndex == -1 || afterIndex == -1) {
																						return false;
																					}

																					return beforeIndex > afterIndex;
																				}))
														  .toList();

		invalidManuals.forEach(manual -> manual.sort(new Comparator<Integer>() {

			@Override
			public int compare(Integer o1, Integer o2) {
				return rules.stream()
					 .filter(rule -> (rule.before == o1 && rule.after == o2) || (rule.before == o2 && rule.after == o1))
					 .findFirst()
					 .map(rule -> {
						 if(rule.before == o1) {
							 return -1;
						 } else {
							 return 1;
						 }
					 }).orElseThrow(() -> new IllegalStateException("no rule found"));
			}
		}));

		return invalidManuals.stream()
							 .peek(pages -> System.out.println(pages.get(pages.size() / 2)))
							 .map(pages -> pages.get(pages.size() / 2))
							 .mapToInt(Integer::intValue)
							 .sum();

	}

	public record Rule(int before, int after) {

	}

}
