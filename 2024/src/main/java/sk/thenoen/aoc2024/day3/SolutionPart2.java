package sk.thenoen.aoc2024.day3;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;
import java.util.regex.MatchResult;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Gatherer;

import sk.thenoen.aoc.Utils;

public class SolutionPart2 {

	public long solvePart2(String inputPath) {
		final ArrayList<String> lines = Utils.loadLines(inputPath);

		final String memoryString = lines.stream().collect(Collectors.joining());

		final String pattern = String.join("|", List.of("mul\\((\\d+),(\\d+)\\)",
														"do\\(\\)",
														"don't\\(\\)"));

		return Pattern.compile(pattern)
					  .matcher(memoryString)
					  .results()
					  .gather(new FilteringGatherer())
					  .mapToInt(match -> {
						  final String group = match.group();
						  System.out.println(group);
						  final int o1 = Integer.parseInt(match.group(1));
						  final int o2 = Integer.parseInt(match.group(2));
						  return o1 * o2;
						  //			sum += value;
					  })
					  .sum();
	}

	private class FilteringGatherer implements Gatherer<MatchResult, State, MatchResult> {

		@Override
		public Supplier<State> initializer() {
			final State state = new State();
			state.enable();
			return () -> state;
		}

		@Override
		public Integrator<State, MatchResult, MatchResult> integrator() {

			return Integrator.ofGreedy((state, matchResult, downstream) -> {
				if (matchResult.group().startsWith("mul") && state.enabled) {
					downstream.push(matchResult);
				}
				if (matchResult.group().startsWith("do")) {
					state.enable();
				}
				if (matchResult.group().startsWith("don't")) {
					state.disable();
				}

				return true;
			});
		}
	}

	private class State {

		private boolean enabled = true;

		public void enable() {
			enabled = true;
		}

		public void disable() {
			enabled = false;
		}
	}
}
