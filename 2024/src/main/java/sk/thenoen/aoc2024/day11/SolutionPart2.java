package sk.thenoen.aoc2024.day11;

import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

import sk.thenoen.aoc.Utils;

public class SolutionPart2 {

	private final static List<Rule> rules = buildRules();

	public int solveInLoop(String inputPath, int blinkCount) {
		final ArrayList<String> lines = Utils.loadLines(inputPath);

		List<String> stones = new ArrayList<>(Arrays.asList(lines.get(0).split(" ")));

		stones = solveInLoop(blinkCount, stones, rules);

		return stones.size();
	}

	public long solveRecursively(String inputPath, int maxBblinkCount) {
		final ArrayList<String> lines = Utils.loadLines(inputPath);

		List<String> stones = new ArrayList<>(Arrays.asList(lines.get(0).split(" ")));
		long count = 0;

		for (String stone : stones) {
			count += expandStone(stone, 0, maxBblinkCount);
		}

		return count;
	}

	private long expandStone(String stone, int blinkNr, int maxBlinkCount) {
		if (blinkNr == maxBlinkCount) {
//			System.out.print(stone + " ");
			return 1;
		}
		long count = 0;
		boolean wasRuleApplied = false;
		for (Rule rule : rules) {
			if (rule.isApplicable(stone)) {
				wasRuleApplied = true;
				List<String> generatedStones = rule.apply(stone);
//				System.out.println(stone + " => " + generatedStones + "(" + blinkNr + ")");
				for (String generatedStone : generatedStones) {
					count += expandStone(generatedStone, blinkNr + 1, maxBlinkCount);
				}
				break;
			}
		}
		if (!wasRuleApplied) {
			count = 1;
		}
		return count;
	}

	private static List<Rule> buildRules() {
		List<Rule> rules = new ArrayList<>();
		rules.add(new Rule(s -> s.equals("0"), s -> List.of("1")));
		rules.add(new Rule(s -> s.length() % 2 == 0, s -> {
			final int half = s.length() / 2;
			return List.of(s.substring(0, half), String.valueOf(Integer.parseInt(s.substring(half))));
		}));
		rules.add(new Rule(s -> true, s -> List.of(String.valueOf(Long.parseLong(s) * 2024L))));
		return rules;
	}

	private static List<String> solveInLoop(int blinkCount, List<String> stones, List<Rule> rules) {
		LocalTime start = LocalTime.now();

		for (int i = 1; i <= blinkCount; i++) {
			List<String> newStones = new ArrayList<>(stones.size() * 2);
			for (int s = 0; s < stones.size(); s++) {

				final String currentStone = stones.get(s);
				boolean wasRuleApplied = false;
				for (Rule rule : rules) {
					if (rule.isApplicable(currentStone)) {
						List<String> generatedStones = rule.apply(currentStone);
						newStones.addAll(generatedStones);
						wasRuleApplied = true;
						break;
					}
				}
				if (!wasRuleApplied) {
					newStones.add(currentStone);
				}
			}
			stones = newStones;
			//			System.out.println(stones);
			System.out.println(Duration.between(start, LocalTime.now()).withNanos(0) + " : " + i + " => " + stones.size());
		}
		return stones;
	}

	private static class Rule {

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
