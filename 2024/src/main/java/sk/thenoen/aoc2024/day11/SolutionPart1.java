package sk.thenoen.aoc2024.day11;

import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Function;

import sk.thenoen.aoc.Utils;

public class SolutionPart1 {

	public int solve(String inputPath, int blinkCount) {
		final ArrayList<String> lines = Utils.loadLines(inputPath);

		final List<String> stones = new ArrayList<>(Arrays.asList(lines.get(0).split(" ")));
		System.out.println(stones);

		List<Rule> rules = new ArrayList<>();
		rules.add(new Rule(s -> s.equals("0"), s -> List.of("1")));
		rules.add(new Rule(s -> s.length() % 2 == 0, s -> {
			final int half = s.length() / 2;
			return List.of(s.substring(0, half), String.valueOf(Integer.parseInt(s.substring(half))));
		}));
		rules.add(new Rule(s -> true, s -> List.of(String.valueOf(Long.parseLong(s) * 2024L))));

		LocalTime start = LocalTime.now();

		for (int i = 1; i <= blinkCount; i++) {
			for (int s = 0; s < stones.size(); s++) {

				for (Rule rule : rules) {
					final String currentStone = stones.get(s);
					if (rule.isApplicable(currentStone)) {
						List<String> newStones = rule.apply(currentStone);
						stones.remove(s);
						for (int newStoneIndex = 0; newStoneIndex < newStones.size(); newStoneIndex++) {
							stones.add(s + newStoneIndex, newStones.get(newStoneIndex));
						}
						s += newStones.size()-1;
						break;
					}
				}
			}
//			System.out.println(stones);
			System.out.println(Duration.between(start, LocalTime.now()).withNanos(0) + " : " + i + " => " + stones.size());
		}

		return stones.size();
	}

	private class Rule {

		private Function<String, Boolean> condition;
		private Function<String, List<String>> operation;

		public Rule(Function<String, Boolean> condition,
					Function<String, List<String>> operation) {
			this.condition = condition;
			this.operation = operation;
		}

		public boolean isApplicable(String s) {
			return condition.apply(s);
		}

		public List<String> apply(String s) {
			return operation.apply(s);
		}
	}

}
