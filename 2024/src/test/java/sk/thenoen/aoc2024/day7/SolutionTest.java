package sk.thenoen.aoc2024.day7;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SolutionTest {

	@Test
	void solvePart1Sample() {
		final SolutionPart1 solution = new SolutionPart1();
		long result = solution.solve("day7/sample.txt");
		System.out.println("result: " + result);
		assertEquals(3749, result);
	}

	@Test
	void solvePart1() {
		final SolutionPart1 solution = new SolutionPart1();
		long result = solution.solve("day7/input.txt");
		System.out.println("result: " + result);
		assertEquals(2941973819040L, result);
	}

	@Test
	void solvePart2Sample() {
		final SolutionPart2 solution = new SolutionPart2();
		long result = solution.solve("day7/sample.txt");
		System.out.println("result: " + result);
		assertEquals(11387, result);
	}

	@Test
	void solvePart2() {
		final SolutionPart2 solution = new SolutionPart2();
		long result = solution.solve("day7/input.txt");
		System.out.println("result: " + result);
		assertEquals(249943041417600L, result);
	}

}