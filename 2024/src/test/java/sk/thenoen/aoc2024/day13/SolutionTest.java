package sk.thenoen.aoc2024.day13;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SolutionTest {

	@Test
	void solvePart1Sample1() {
		Solution solution = new Solution();
		long result = solution.solvePart1("day13/sample1.txt");
		System.out.println("result: " + result);
		assertEquals(480, result);
	}

	@Test
	void solvePart1() {
		Solution solution = new Solution();
		long result = solution.solvePart1("day13/input.txt");
		System.out.println("result: " + result);
		assertEquals(36838, result);
	}

	@Test
	void solvePart2Sample() {
		//only problems 2 and 4 have solution
		Solution solution = new Solution();
		long result = solution.solvePart2("day13/sample1.txt");
		System.out.println("result: " + result);
		assertEquals(-1, result);
	}
}