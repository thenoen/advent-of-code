package sk.thenoen.aoc2024.day9;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class SolutionTest {

	@Test
	void solvePart1Sample() {
		SolutionPart1 solution = new SolutionPart1();
		BigDecimal result = solution.solve("day9/sample.txt");
		System.out.println("result: " + result);
		assertEquals(BigDecimal.valueOf(1928), result);
	}

	@Test
	void solvePart1MySample1() {
		SolutionPart1 solution = new SolutionPart1();
		BigDecimal result = solution.solve("day9/my_sample_1.txt");
		System.out.println("result: " + result);
		assertNotEquals(BigDecimal.valueOf(4111), result);
		assertEquals(BigDecimal.valueOf(5482), result);
	}


	@Test
	void solvePart1() {
		SolutionPart1 solution = new SolutionPart1();
		BigDecimal result = solution.solve("day9/input.txt");
		System.out.println("result: " + result);
		assertTrue(new BigDecimal("6384282079460").compareTo(result) == 0);
	}

	/////////// PART 2 ///////////

	//	@Test
	//	void solvePart2Sample() {
	//		SolutionPart2 solution = new SolutionPart2();
	//		long result = solution.solve("day8/sample.txt");
	//		System.out.println("result: " + result);
	//		assertEquals(34, result);
	//	}

	//	@Test
	//	void solvePart2() {
	//		SolutionPart2 solution = new SolutionPart2();
	//		long result = solution.solve("day8/input.txt");
	//		System.out.println("result: " + result);
	//		assertEquals(1134, result);
	//	}

}