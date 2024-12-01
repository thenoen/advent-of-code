package sk.thenone.aoc2024.day1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import sk.thenoen.aoc2024.day1.Solution;

public class Day1Test {

	@Test
	void part1Sample() {
		Solution solution = new Solution();
		long result = solution.solvePart1("day1/sample.txt");
		System.out.println("result: " + result);
		Assertions.assertEquals(11, result);
	}

	@Test
	void part1() {
		Solution solution = new Solution();
		long result = solution.solvePart1("day1/input.txt");
		System.out.println("result: " + result);
		Assertions.assertEquals(1320851, result);
	}

	@Test
	void part2Sample() {
		Solution solution = new Solution();
		long result = solution.solvePart2("day1/sample.txt");
		System.out.println("result: " + result);
		Assertions.assertEquals(31, result);
	}

	@Test
	void part2() {
		Solution solution = new Solution();
		long result = solution.solvePart2("day1/input.txt");
		System.out.println("result: " + result);
		Assertions.assertEquals(26859182, result);
	}

}
