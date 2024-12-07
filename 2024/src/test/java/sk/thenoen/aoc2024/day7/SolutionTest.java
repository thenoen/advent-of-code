package sk.thenoen.aoc2024.day7;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SolutionTest {

	@Test
	void solvePart1Sample() {
		final Solution solution = new Solution();
		long result = solution.solve("day7/sample.txt");
		System.out.println("result: " + result);
		assertEquals(3749, result);
	}

	@Test
	void solvePart1() {
		final Solution solution = new Solution();
		long result = solution.solve("day7/input.txt");
		System.out.println("result: " + result);
		assertEquals(2941973819040L, result);
	}

}