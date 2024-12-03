package sk.thenoen.aoc2024.day3;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class Day3Test {

	@Test
	void part1Sample() {
		SolutionPart1 solutionPart1 = new SolutionPart1();
		long result = solutionPart1.solvePart1("day3/sample.txt");
		System.out.println("result: " + result);
		Assertions.assertEquals(161, result);
	}

	@Test
	void part1() {
		SolutionPart1 solutionPart1 = new SolutionPart1();
		long result = solutionPart1.solvePart1("day3/input.txt");
		System.out.println("result: " + result);
		Assertions.assertEquals(196826776, result);
	}

	@Test
	void part2Sample() {
		SolutionPart2 solution = new SolutionPart2();
		long result = solution.solvePart2("day3/samplePart2.txt");
		System.out.println("result: " + result);
		Assertions.assertEquals(48, result);
	}

	@Test
	void part2() {
		SolutionPart2 solution = new SolutionPart2();
		long result = solution.solvePart2("day3/input.txt");
		System.out.println("result: " + result);
		Assertions.assertEquals(106780429, result);
	}

}
