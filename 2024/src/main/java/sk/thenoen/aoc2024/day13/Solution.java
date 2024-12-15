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

	public long solvePart1(String inputPath) {
		final ArrayList<String> lines = Utils.loadLines(inputPath);
		final Pattern buttonPattern = Pattern.compile(BUTTON_REGEX);
		final Pattern prizePattern = Pattern.compile(PRIZE_REGEX);

		final List<Problem> problems = parseInput(lines, buttonPattern, prizePattern, 0);
		long totalPrice = 0;

		for (int i = 0; i < problems.size(); i++) {
			System.out.println("problem: " + i);
			final long price = solveProblem1(problems.get(i));
			totalPrice += price;
		}

		return totalPrice;
	}

	public long solvePart2(String inputPath) {

		//		final long gcd1 = gcd(48, 18);
		//		final long gcd2 = gcd(18, 48);
		final ArrayList<String> lines = Utils.loadLines(inputPath);
		final Pattern buttonPattern = Pattern.compile(BUTTON_REGEX);
		final Pattern prizePattern = Pattern.compile(PRIZE_REGEX);

		List<Problem> problems = parseInput(lines, buttonPattern, prizePattern, 10_000_000_000_000L);

		long totalPrice = 0;
		for (int i = 0; i < problems.size(); i++) {
			System.out.println("problem: " + i);
			final long price = solveProblem2(problems.get(i));
			if (price > 0) {
				System.out.println("price: " + price);
			} else {
				System.out.println("no solution");
			}
			totalPrice += price;
			System.out.println();
		}

		return totalPrice;
	}

	private long solveProblem1(Problem problem) {

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

	private long solveProblem2(Problem problem) {

		final Location buttonA = problem.buttonA;
		final Location buttonB = problem.buttonB;
		final Location prize = problem.prize;

		long bDivident = buttonA.x * prize.y - buttonA.y * prize.x;
		long bDivisor = buttonA.x * buttonB.y - buttonA.y * buttonB.x;
		long test2 = bDivident % bDivisor;
		long bDivision = bDivident / bDivisor;

		long aCalcTest = (prize.x - bDivision* buttonB.x) % buttonA.x;
		long aCalc = (prize.x - bDivision* buttonB.x) / buttonA.x;

		if(test2 == 0 && aCalcTest == 0) {
			long priceCalc = aCalc * A_COST + bDivision * B_COST;
			return priceCalc;
		}

		return 0;
	}

	private static List<Problem> parseInput(ArrayList<String> lines,
											Pattern buttonPattern,
											Pattern prizePattern,
											long conversionError) {
		List<Problem> problems = new ArrayList<>();
		for (int i = 0; i < lines.size(); i++) {
			final Matcher matcherA = buttonPattern.matcher(lines.get(i));
			matcherA.find();
			final String aX = matcherA.group(1);
			final String aY = matcherA.group(2);
			Location buttonA = new Location(Long.parseLong(aX),
											Long.parseLong(aY));
			i++;
			final Matcher matcherB = buttonPattern.matcher(lines.get(i));
			matcherB.find();
			final String bX = matcherB.group(1);
			final String bY = matcherB.group(2);
			Location buttonB = new Location(Long.parseLong(bX),
											Long.parseLong(bY));
			i++;

			final Matcher prizeMatcher = prizePattern.matcher(lines.get(i));
			prizeMatcher.find();
			final String prizeX = prizeMatcher.group(1);
			final String prizeY = prizeMatcher.group(2);
			Location prize = new Location(Long.parseLong(prizeX) + conversionError,
										  Long.parseLong(prizeY) + conversionError);

//			System.out.println();
			i++;
			Problem problem = new Problem(buttonA, buttonB, prize);
			problems.add(problem);
		}
		return problems;
	}

	private record Problem(Location buttonA, Location buttonB, Location prize) {

	}

	private record Location(long x, long y) {

	}

	private long gcd(long a, long b) {
		if (b == 0) {
			return a;
		}
		return gcd(b, a % b);
	}

	private long lcm(long a, long b) {
		return a * b / gcd(a, b);
	}

}
