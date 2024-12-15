package sk.thenoen.aoc2024.day14;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SolutionTest {

	@Test
	void solvePart1Sample1() {
		Solution solution = new Solution();
		final int result = solution.solvePart1("day14/sample1.txt", 11, 7, 5);
		assertEquals(-1, result);
	}

	@Test
	void solvePart1Sample() {
		Solution solution = new Solution();
		final int result = solution.solvePart1("day14/sample.txt", 11, 7, 100);
		assertEquals(12, result);
	}

	@Test
	void solvePart1() {
		Solution solution = new Solution();
		final int result = solution.solvePart1("day14/input.txt", 101, 103, 100);
		assertEquals(-1, result);
	}
}