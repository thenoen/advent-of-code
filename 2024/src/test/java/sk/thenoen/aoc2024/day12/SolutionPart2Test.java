package sk.thenoen.aoc2024.day12;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SolutionPart2Test {

	@Test
	void solvePart2Sample1() {
		SolutionPart2 solution = new SolutionPart2();
		long result = solution.solveAllPlantTypes("day12/part2_sample1.txt");
		System.out.println("result: " + result);
		assertEquals(80, result);
	}

	@Test
	void solvePart2Sample2() {
		SolutionPart2 solution = new SolutionPart2();
		long result = solution.solveAllPlantTypes("day12/part2_sample2.txt");
		System.out.println("result: " + result);
		assertEquals(436, result);
	}

	@Test
	void solvePart2Sample3() {
		SolutionPart2 solution = new SolutionPart2();
		long result = solution.solveAllPlantTypes("day12/part2_sample3.txt");
		System.out.println("result: " + result);
		assertEquals(236, result);
	}

	@Test
	void solvePart2Sample4() {
		SolutionPart2 solution = new SolutionPart2();
		long result = solution.solveAllPlantTypes("day12/part2_sample4.txt");
		System.out.println("result: " + result);
		assertEquals(368, result);
	}

	@Test
	void solvePart2Sample5() {
		SolutionPart2 solution = new SolutionPart2();
		long result = solution.solveAllPlantTypes("day12/part2_sample5.txt");
		System.out.println("result: " + result);
		assertEquals(1206, result);
	}

	@Test
	void solvePart2() {
		SolutionPart2 solution = new SolutionPart2();
		long result = solution.solveAllPlantTypes("day12/input.txt");
		System.out.println("result: " + result);
		assertEquals(839780, result);
	}
}