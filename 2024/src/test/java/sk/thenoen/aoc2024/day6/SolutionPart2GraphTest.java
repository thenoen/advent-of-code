package sk.thenoen.aoc2024.day6;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SolutionPart2GraphTest {

	@Test
	void solvePart2Sample() throws InterruptedException {
		final SolutionPart2Graph solution = new SolutionPart2Graph();
		long result = solution.solve("day6/sample.txt");
		System.out.println("result: " + result);
		Assertions.assertEquals(6, result);
	}

	@Test
	void solvePart2MySample() throws InterruptedException {
		final SolutionPart2Graph solution = new SolutionPart2Graph();
		long result = solution.solve("day6/my_sample.txt");
		System.out.println("result: " + result);
		Assertions.assertEquals(5, result);
	}

	@Test
	void solvePart2MySample2() throws InterruptedException {
		final SolutionPart2Graph solution = new SolutionPart2Graph();
		long result = solution.solve("day6/my_sample2.txt");
		System.out.println("result: " + result);
		Assertions.assertEquals(5, result);
	}

	@Test
	void solvePart2MySample3() throws InterruptedException {
		final SolutionPart2Graph solution = new SolutionPart2Graph();
		long result = solution.solve("day6/my_sample3.txt");
		System.out.println("result: " + result);
		Assertions.assertEquals(5, result);
	}

	@Test
	void solvePart2MySample4() throws InterruptedException {
		final SolutionPart2Graph solution = new SolutionPart2Graph();
		long result = solution.solve("day6/my_sample4.txt");
		System.out.println("result: " + result);
		Assertions.assertEquals(1, result);
	}

	@Test
	void solvePart2() throws InterruptedException {
		final SolutionPart2Graph solution = new SolutionPart2Graph();
		long result = solution.solve("day6/input.txt");
		System.out.println("result: " + result);
		Assertions.assertNotEquals(672, result);
		Assertions.assertNotEquals(682, result);
		Assertions.assertTrue(result > 722, "too low: " + result);
		Assertions.assertNotEquals(900, result);
		Assertions.assertEquals(1770, result);
	}
}