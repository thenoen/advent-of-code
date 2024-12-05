package sk.thenoen.aoc2024.day4;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class Day4Test {

	@Test
	void part1TestSample() {
		SolutionPart1 solutionPart1 = new SolutionPart1();
		long result = solutionPart1.solvePart1("day4/test-sample.txt");
		System.out.println("result: " + result);
		Assertions.assertEquals(4, result);
	}

	@Test
	void part1Sample() {
		SolutionPart1 solutionPart1 = new SolutionPart1();
		long result = solutionPart1.solvePart1("day4/sample.txt");
		System.out.println("result: " + result);
		Assertions.assertEquals(18, result);
	}

	@Test
	void part1() {
		SolutionPart1 solutionPart1 = new SolutionPart1();
		long result = solutionPart1.solvePart1("day4/input.txt");
		System.out.println("result: " + result);
		Assertions.assertEquals(2336, result);
	}

	@Test
	void part2TestSample() {
		SolutionPart2 solutionPart2 = new SolutionPart2();
		long result = solutionPart2.solvePart2("day4/test-sample-part2.txt");
		System.out.println("result: " + result);
		Assertions.assertEquals(9, result);
	}

	@Test
	void part2Sample() {
		SolutionPart2 solutionPart2 = new SolutionPart2();
		long result = solutionPart2.solvePart2("day4/sample.txt");
		System.out.println("result: " + result);
		Assertions.assertEquals(9, result);
	}

	@Test
	void part2() {
		SolutionPart2 solutionPart2 = new SolutionPart2();
		long result = solutionPart2.solvePart2("day4/input.txt");
		System.out.println("result: " + result);
		Assertions.assertEquals(1831, result);
	}
}
