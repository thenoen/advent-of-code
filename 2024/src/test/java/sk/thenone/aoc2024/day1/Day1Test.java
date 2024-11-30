package sk.thenone.aoc2024.day1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import sk.thenoen.aoc2024.day1.Solution;

public class Day1Test {

	@Test
	void part1() {
		Solution solution = new Solution();
		long result = solution.solvePart1();
		System.out.println("result: " + result);
		Assertions.assertEquals(21, result);
	}


}
