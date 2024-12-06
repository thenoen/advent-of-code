package sk.thenoen.aoc2024.day6.day6;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import sk.thenoen.aoc2024.day6.SolutionPart1;
import sk.thenoen.aoc2024.day6.SolutionPart2;

class SolutionTest {

	@Test
	void solvePart1Sample() throws InterruptedException {
		final SolutionPart1 solutionPart1 = new SolutionPart1();
		long result = solutionPart1.solve("day6/sample.txt");
		System.out.println("result: " + result);
		Assertions.assertEquals(41, result);
	}

	@Test
	void solvePart1() throws InterruptedException {
		final SolutionPart1 solutionPart1 = new SolutionPart1();
		long result = solutionPart1.solve("day6/input.txt");
		System.out.println("result: " + result);
		Assertions.assertEquals(5086, result);
	}

	@Test
	void solvePart2Sample() throws InterruptedException {
		final SolutionPart2 solution = new SolutionPart2();
		long result = solution.solve("day6/sample.txt");
		System.out.println("result: " + result);
		Assertions.assertEquals(6, result);
	}
//
//	@Test
//	void solvePart2() {
//		final SolutionPart2 solution = new SolutionPart2();
//		long result = solution.solve("day6/input.txt");
//		System.out.println("result: " + result);
//		Assertions.assertEquals(5479, result);
//	}
}