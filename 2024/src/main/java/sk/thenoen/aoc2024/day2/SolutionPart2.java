package sk.thenoen.aoc2024.day2;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import sk.thenoen.aoc.Utils;

public class SolutionPart2 {

	public long solvePart2(String inputPath) {
		final ArrayList<String> lines = Utils.loadLines(inputPath);

		int sum = 0;

		for (String line : lines) {
			List<Integer> levels = new ArrayList<>();
			final Scanner scanner = new Scanner(line).useDelimiter(" ");
			while (scanner.hasNextInt()) {
				levels.add(scanner.nextInt());
			}

			if (isSafe(levels)) {
				sum++;
				continue;
			}

			for (int i = 0; i < levels.size(); i++) {
				final ArrayList<Integer> dampenedLevels = new ArrayList<>(levels);
				dampenedLevels.remove(i);
				if (isSafe(dampenedLevels)) {
					sum++;
					break;
				}
			}

		}

		return sum;
	}

	private static boolean isSafe(List<Integer> levels) {
		if (levels.getFirst() > levels.getLast()) {
			if (isDecreasing(levels)) {
				return true;
			}
		} else if (levels.getFirst() < levels.getLast()) {
			if (isIncreasing(levels)) {
				return true;
			}
		} else {
			return false;
		}
		return false;
	}

	private static boolean isDecreasing(List<Integer> levels) {
		for (int i = 0; i < levels.size() - 1; i++) {
			final int step = levels.get(i) - levels.get(i + 1);
			if (step > 0 && step < 4) {
				continue;
			} else {
				return false;

			}
		}
		return true;
	}

	private static boolean isIncreasing(List<Integer> levels) {
		for (int i = 0; i < levels.size() - 1; i++) {
			final int step = levels.get(i + 1) - levels.get(i);
			if (step > 0 && step < 4) {
				continue;
			} else {
				return false;

			}
		}
		return true;
	}
}
