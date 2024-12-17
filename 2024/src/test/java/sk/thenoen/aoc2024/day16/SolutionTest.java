package sk.thenoen.aoc2024.day16;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SolutionTest {

	@Test
	void solvePart1Sample() {
		Solution solution = new Solution();
		final long result = solution.solvePart1("day16/sample.txt");
		assertEquals(7036L, result);
	}

	@Test
	void solvePart1SampleLarge() {
		Solution solution = new Solution();
		final long result = solution.solvePart1("day16/sample_large.txt");
		assertEquals(11048L, result);
	}

	@Test
	void solvePart1() {
		Solution solution = new Solution();
		final long result = solution.solvePart1("day16/input.txt");
		assertEquals(85420L, result);
	}

	@Test
	void solvePart2Sample() {
		Solution solution = new Solution();
		final long result = solution.solvePart2("day16/sample.txt");
		assertEquals(45L, result);
	}

	@Test
	void solvePart2SampleLarge() {
		Solution solution = new Solution();
		final long result = solution.solvePart2("day16/sample_large.txt");
		assertEquals(64L, result);
	}

	@Test
	void solvePart2() {
		Solution solution = new Solution();
		final long result = solution.solvePart2("day16/input.txt");
		assertEquals(492, result);
	}
}