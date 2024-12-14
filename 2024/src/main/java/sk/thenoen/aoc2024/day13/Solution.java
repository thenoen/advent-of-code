package sk.thenoen.aoc2024.day13;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import sk.thenoen.aoc.Utils;

public class Solution {

	public static final String BUTTON_REGEX = ".*?X\\+(\\d+),\\sY\\+(\\d+)";
	public static final String PRIZE_REGEX = ".*?X=(\\d+),\\sY=(\\d+)";

	private static final int A_COST = 3;
	private static final int B_COST = 1;

	public long solveAllPlantTypes(String inputPath) {
		final ArrayList<String> lines = Utils.loadLines(inputPath);
		final Pattern buttonPattern = Pattern.compile(BUTTON_REGEX);
		final Pattern prizePattern = Pattern.compile(PRIZE_REGEX);

		List<Problem> problems = new ArrayList<>();

		parseInput(lines, buttonPattern, prizePattern, problems);
		long totalPrice = 0;

		for (int i = 0; i < problems.size(); i++) {
			System.out.println("problem: " + i);
			final long price = solveProblem(problems.get(i));
			totalPrice += price;
		}

		return totalPrice;
	}

	private long solveProblem(Problem problem) {

		int minPrice = Integer.MAX_VALUE;
		boolean hasSolution = false;

		for (int a = 0; a < 200; a++) {
			for (int b = 0; b < 200; b++) {

				if (a * problem.buttonA.x + b * problem.buttonB.x == problem.prize.x &&
					a * problem.buttonA.y + b * problem.buttonB.y == problem.prize.y) {
					System.out.println("a: " + a + " b: " + b);
					hasSolution = true;
					int price = a * A_COST + b * B_COST;
					if (price < minPrice) {
						minPrice = price;
					}
				}
			}
		}
		if (hasSolution) {
			return minPrice;
		}
		return 0;
	}

	private static void parseInput(ArrayList<String> lines, Pattern buttonPattern, Pattern prizePattern, List<Problem> problems) {
		for (int i = 0; i < lines.size(); i++) {
			final Matcher matcherA = buttonPattern.matcher(lines.get(i));
			matcherA.find();
			final String aX = matcherA.group(1);
			final String aY = matcherA.group(2);
			Location buttonA = new Location(Integer.parseInt(aX), Integer.parseInt(aY));
			i++;
			final Matcher matcherB = buttonPattern.matcher(lines.get(i));
			matcherB.find();
			final String bX = matcherB.group(1);
			final String bY = matcherB.group(2);
			Location buttonB = new Location(Integer.parseInt(bX), Integer.parseInt(bY));
			i++;

			final Matcher prizeMatcher = prizePattern.matcher(lines.get(i));
			prizeMatcher.find();
			final String prizeX = prizeMatcher.group(1);
			final String prizeY = prizeMatcher.group(2);
			Location prize = new Location(Integer.parseInt(prizeX), Integer.parseInt(prizeY));

			System.out.println();
			i++;
			Problem problem = new Problem(buttonA, buttonB, prize);
			problems.add(problem);
		}
	}

	private record Problem(Location buttonA, Location buttonB, Location prize) {

	}

	private record Location(int x, int y) {

	}

}
