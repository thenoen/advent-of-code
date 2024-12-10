package sk.thenoen.aoc2024.day10;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SolutionTest {

	@Test
	void solvePart1Sample1() {
		SolutionPart1 solution = new SolutionPart1();
		int result = solution.solve("day10/sample1.txt");
		System.out.println("result: " + result);
		assertEquals(2, result);
	}

	@Test
	void solvePart1Sample2() {
		SolutionPart1 solution = new SolutionPart1();
		int result = solution.solve("day10/sample2.txt");
		System.out.println("result: " + result);
		assertEquals(4, result);
	}

	@Test
	void solvePart1Sample3() {
		SolutionPart1 solution = new SolutionPart1();
		int result = solution.solve("day10/sample3.txt");
		System.out.println("result: " + result);
		assertEquals(3, result);
	}

	@Test
	void solvePart1Sample4() {
		SolutionPart1 solution = new SolutionPart1();
		int result = solution.solve("day10/sample4.txt");
		System.out.println("result: " + result);
		assertEquals(36, result);
	}

	@Test
	void solvePart1() {
		SolutionPart1 solution = new SolutionPart1();
		int result = solution.solve("day10/input.txt");
		System.out.println("result: " + result);
		assertEquals(468, result);
	}


}