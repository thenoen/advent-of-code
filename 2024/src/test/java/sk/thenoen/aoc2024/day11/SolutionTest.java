package sk.thenoen.aoc2024.day11;

import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertEquals;

class SolutionTest {

	@Test
	void solvePart1Sample() {
		SolutionPart1 solution = new SolutionPart1();
		long result = solution.solve("day11/sample.txt", 25);
		System.out.println("result: " + result);
		assertEquals(55312, result);
	}

	@Test
	void solvePart1() {
		SolutionPart1 solution = new SolutionPart1();
		long result = solution.solve("day11/input.txt", 25);
		System.out.println("result: " + result);
		assertEquals(183620, result);
	}

	/////////////// PART 2 ////////////////

	@Test
	void solvePart2Sample() {
		SolutionPart2 solution = new SolutionPart2();
		long result = solution.solve("day11/sample.txt", 25);
		System.out.println("result: " + result);
		assertEquals(55312, result);
	}

	@Test
	void solvePart2() {
		SolutionPart2 solution = new SolutionPart2();
		long result = solution.solve("day11/input.txt", 75);
		System.out.println("result: " + result);
		assertEquals(0, result);
	}



}