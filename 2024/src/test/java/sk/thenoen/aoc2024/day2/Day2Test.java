package sk.thenoen.aoc2024.day2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class Day2Test {

	@Test
	void part1Sample() {
		SolutionPart1 solutionPart1 = new SolutionPart1();
		long result = solutionPart1.solvePart1("day2/sample.txt");
		System.out.println("result: " + result);
		Assertions.assertEquals(2, result);
	}

	@Test
	void part1() {
		SolutionPart1 solutionPart1 = new SolutionPart1();
		long result = solutionPart1.solvePart1("day2/input.txt");
		System.out.println("result: " + result);
		Assertions.assertEquals(631, result);
	}

	@Test
	void part2Sample() {
		SolutionPart2 solution = new SolutionPart2();
		long result = solution.solvePart2("day2/sample.txt");
		System.out.println("result: " + result);
		Assertions.assertEquals(4, result);
	}

	@Test
	void part2() {
		SolutionPart2 solution = new SolutionPart2();
		long result = solution.solvePart2("day2/input.txt");
		System.out.println("result: " + result);
		Assertions.assertEquals(665, result);
	}

}
