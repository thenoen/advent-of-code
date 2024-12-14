package sk.thenoen.aoc2024.day13;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SolutionTest {

	@Test
	void solvePart1Sample1() {
		Solution solution = new Solution();
		long result = solution.solveAllPlantTypes("day13/sample1.txt");
		System.out.println("result: " + result);
		assertEquals(480, result);
	}

	@Test
	void solvePart1() {
		Solution solution = new Solution();
		long result = solution.solveAllPlantTypes("day13/input.txt");
		System.out.println("result: " + result);
		assertEquals(36838, result);
	}
}