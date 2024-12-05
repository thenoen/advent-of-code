package sk.thenoen.aoc2024.day4;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class Day4Test {

	@Test
	void part1TestSample() {
		SolutionPart1 solutionPart1 = new SolutionPart1();
		long result = solutionPart1.solvePart1("day4/test-sample.txt");
		System.out.println("result: " + result);
		Assertions.assertEquals(4, result);
	}

	@Test
	void part1Sample() {
		SolutionPart1 solutionPart1 = new SolutionPart1();
		long result = solutionPart1.solvePart1("day4/sample.txt");
		System.out.println("result: " + result);
		Assertions.assertEquals(18, result);
	}

	@Test
	void part1() {
		SolutionPart1 solutionPart1 = new SolutionPart1();
		long result = solutionPart1.solvePart1("day4/input.txt");
		System.out.println("result: " + result);
		Assertions.assertEquals(2336, result);
	}

//	@Test
//	void part1() {
//		sk.thenoen.aoc2024.day3.SolutionPart1 solutionPart1 = new SolutionPart1();
//		long result = solutionPart1.solvePart1("day3/input.txt");
//		System.out.println("result: " + result);
//		Assertions.assertEquals(196826776, result);
//	}
//
//	@Test
//	void part2Sample() {
//		sk.thenoen.aoc2024.day3.SolutionPart2 solution = new sk.thenoen.aoc2024.day3.SolutionPart2();
//		long result = solution.solvePart2("day3/samplePart2.txt");
//		System.out.println("result: " + result);
//		Assertions.assertEquals(48, result);
//	}
//
//	@Test
//	void part2() {
//		sk.thenoen.aoc2024.day3.SolutionPart2 solution = new SolutionPart2();
//		long result = solution.solvePart2("day3/input.txt");
//		System.out.println("result: " + result);
//		Assertions.assertEquals(106780429, result);
//	}

}
