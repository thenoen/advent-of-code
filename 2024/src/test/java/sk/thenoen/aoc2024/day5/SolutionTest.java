package sk.thenoen.aoc2024.day5;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class SolutionTest {

	@Test
	void solvePart1Sample() {
		final Solution solution = new Solution();
		long result = solution.solve("day5/sample.txt");
		System.out.println("result: " + result);
		Assertions.assertEquals(143, result);
	}

	@Test
	void solvePart1() {
		final Solution solution = new Solution();
		long result = solution.solve("day5/input.txt");
		System.out.println("result: " + result);
		Assertions.assertEquals(5991, result);
	}

	@Test
	void solvePart2Sample() {
		final SolutionPart2 solution = new SolutionPart2();
		long result = solution.solve("day5/sample.txt");
		System.out.println("result: " + result);
		Assertions.assertEquals(123, result);
	}

	@Test
	void solvePart2() {
		final SolutionPart2 solution = new SolutionPart2();
		long result = solution.solve("day5/input.txt");
		System.out.println("result: " + result);
		Assertions.assertEquals(5479, result);
	}
}