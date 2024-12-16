package sk.thenoen.aoc2024.day15;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SolutionTest {

	@Test
	void solvePart1Sample() {
		Solution solution = new Solution();
		final long result = solution.solvePart1("day15/sample.txt");
		assertEquals(2028L, result);
	}

	@Test
	void solvePart1SampleLarge() {
		Solution solution = new Solution();
		final long result = solution.solvePart1("day15/sample_large.txt");
		assertEquals(10092L, result);
	}

	@Test
	void solvePart1() {
		Solution solution = new Solution();
		final long result = solution.solvePart1("day15/input.txt");
		assertEquals(1511865, result);
	}
}