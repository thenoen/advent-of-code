package sk.thenoen.aoc2024.day8;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SolutionTest {

	@Test
	void solvePart1Sample0() {
		SolutionPart1 solution = new SolutionPart1();
		long result = solution.solve("day8/samples/sample0.txt");
		System.out.println("result: " + result);
		assertEquals(12, result);
	}

	@Test
	void solvePart1Sample1() {
		SolutionPart1 solution = new SolutionPart1();
		long result = solution.solve("day8/samples/sample1.txt");
		System.out.println("result: " + result);
		assertEquals(2, result);
	}

	@Test
	void solvePart1Sample2() {
		SolutionPart1 solution = new SolutionPart1();
		long result = solution.solve("day8/samples/sample2.txt");
		System.out.println("result: " + result);
		assertEquals(2, result);
	}

	@Test
	void solvePart1Sample() {
		SolutionPart1 solution = new SolutionPart1();
		long result = solution.solve("day8/sample.txt");
		System.out.println("result: " + result);
		assertEquals(14, result);
	}

	@Test
	void solvePart1() {
		SolutionPart1 solution = new SolutionPart1();
		long result = solution.solve("day8/input.txt");
		System.out.println("result: " + result);
		assertEquals(341, result);
	}

	/////////// PART 2 ///////////



	@Test
	void solvePart2Sample0() {
		SolutionPart2 solution = new SolutionPart2();
		long result = solution.solve("day8/samples/sample0.txt");
		System.out.println("result: " + result);
		assertEquals(30, result);
	}


	@Test
	void solvePart2Sample2_1() {
		SolutionPart2 solution = new SolutionPart2();
		long result = solution.solve("day8/samples/sample2-1.txt");
		System.out.println("result: " + result);
		assertEquals(6, result);
	}


	@Test
	void solvePart2Sample() {
		SolutionPart2 solution = new SolutionPart2();
		long result = solution.solve("day8/sample.txt");
		System.out.println("result: " + result);
		assertEquals(34, result);
	}

	@Test
	void solvePart2() {
		SolutionPart2 solution = new SolutionPart2();
		long result = solution.solve("day8/input.txt");
		System.out.println("result: " + result);
		assertEquals(1134, result);
	}

}