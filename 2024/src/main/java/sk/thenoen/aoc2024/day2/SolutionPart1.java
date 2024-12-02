package sk.thenoen.aoc2024.day2;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import sk.thenoen.aoc.Utils;

public class SolutionPart1 {

	public long solvePart1(String inputPath) {
		final ArrayList<String> lines = Utils.loadLines(inputPath);

		int sum = 0;

		for (String line : lines) {
			List<Integer> levels = new ArrayList<>();
			final Scanner scanner = new Scanner(line).useDelimiter(" ");
			while (scanner.hasNextInt()) {
				levels.add(scanner.nextInt());
			}

			if (levels.getFirst() > levels.getLast()) {

				if (isDecreasing(levels)) {
					sum++;
				}

			} else if (levels.getFirst() < levels.getLast()) {
				if (isIncreasing(levels)) {
					sum++;
				}
			} else {
				// do nothing, I have not forgotten
			}

		}

		return sum;
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

	public long solvePart2(String inputPath) {
		return -1L;
	}

	record Similarity(Integer id, Long count) {

	}
}
