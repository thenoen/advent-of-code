package sk.thenoen.aoc2024.day7;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;

import sk.thenoen.aoc.Utils;

public class SolutionPart1 {

	public long solve(String inputPath) {
		final ArrayList<String> lines = Utils.loadLines(inputPath);

		List<Problem> problems = new ArrayList<>();
		Integer maxInputCount = 0;

		for (String line : lines) {

			final String[] parts = line.split(":");
			final long result = Long.parseLong(parts[0]);
			final String[] inputStrings = parts[1].split(" ");

			List<Long> inputs = new ArrayList<>();

			for (int i = 1; i < inputStrings.length; i++) {
				inputs.add(Long.parseLong(inputStrings[i]));
			}

			System.out.print(result);
			System.out.print(" -> ");
			System.out.println(inputs);

			problems.add(new Problem(result, inputs));
			if (maxInputCount < inputs.size()) {
				maxInputCount = inputs.size();
			}

		}

		Map<Integer, List<List<Operator>>> operatorCombinationsLengths = new HashMap<>();
		final ArrayList<List<Operator>> value = new ArrayList<>();
		value.add(new ArrayList<>());
		operatorCombinationsLengths.put(0, value);

		for (Integer i = 1; i < maxInputCount; i++) {
			List<List<Operator>> operatorCombinations = operatorCombinationsLengths.getOrDefault(i, operatorCombinationsLengths.get(i - 1));
			List<List<Operator>> newOperatorCombinations = new ArrayList<>();
			for (List<Operator> operatorCombination : operatorCombinations) {
				for (Operator operator : Operator.values()) {
					List<Operator> newOperatorCombination = new ArrayList<>(operatorCombination);
					newOperatorCombination.add(operator);
					newOperatorCombinations.add(newOperatorCombination);
				}
			}
			operatorCombinationsLengths.put(i, newOperatorCombinations);
		}

		Long sumOfValidCalibrations = 0L;

		for (Problem problem : problems) {

			final List<List<Operator>> operatorCombinations = operatorCombinationsLengths.get(problem.inputs.size() - 1);

			for (List<Operator> operatorCombination : operatorCombinations) {
				long res = problem.inputs.get(0);
				for (int i = 0; i < operatorCombination.size(); i++) {
					res = operatorCombination.get(i).apply(res, problem.inputs.get(i + 1));
				}
				if (res == problem.result) {
					sumOfValidCalibrations += res;
					break;
				}
			}

		}

		return sumOfValidCalibrations;
	}

	private record Problem(Long result, List<Long> inputs) {

	}

	private enum Operator {
		ADD((a, b) -> a + b),
		MULTIPLY((a, b) -> a * b);

		private BiFunction<Long, Long, Long> operation;

		Operator(BiFunction<Long, Long, Long> operation) {
			this.operation = operation;
		}

		public Long apply(Long a, Long b) {
			return this.operation.apply(a, b);
		}
	}
}
