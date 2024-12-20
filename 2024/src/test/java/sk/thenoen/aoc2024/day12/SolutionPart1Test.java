package sk.thenoen.aoc2024.day12;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SolutionPart1Test {

	@Test
	void solvePart1MySample1() {
		SolutionPart1 solution = new SolutionPart1();
		long result = solution.solveAllPlantTypes("day12/mySample1.txt");
		System.out.println("result: " + result);
		assertEquals(2788, result);
	}

	@Test
	void solvePart1MySample2() {
		SolutionPart1 solution = new SolutionPart1();
		long result = solution.solveAllPlantTypes("day12/mySample2.txt");
		System.out.println("result: " + result);
		assertEquals(3*8+4*10, result);
	}

	@Test
	void solvePart1MySample3() {
		SolutionPart1 solution = new SolutionPart1();
		long result = solution.solveAllPlantTypes("day12/mySample3.txt");
		System.out.println("result: " + result);
		assertEquals(8962, result);
	}

	@Test
	void solvePart1MySample1PlotB() {
		SolutionPart1 solution = new SolutionPart1();
		long result = solution.solve("day12/mySample1.txt", 'B');
		System.out.println("result: " + result);
		assertEquals(16, result);
	}

	@Test
	void solvePart1Sample0PlotA() {
		SolutionPart1 solution = new SolutionPart1();
		long result = solution.solve("day12/sample0.txt", 'A');
		System.out.println("result: " + result);
		assertEquals(40, result);
	}

	@Test
	void solvePart1Sample0PlotB() {
		SolutionPart1 solution = new SolutionPart1();
		long result = solution.solve("day12/sample0.txt", 'B');
		System.out.println("result: " + result);
		assertEquals(32, result);
	}

	@Test
	void solvePart1Sample0PlotC() {
		SolutionPart1 solution = new SolutionPart1();
		long result = solution.solve("day12/sample0.txt", 'C');
		System.out.println("result: " + result);
		assertEquals(40, result);
	}

	@Test
	void solvePart1Sample0PlotD() {
		SolutionPart1 solution = new SolutionPart1();
		long result = solution.solve("day12/sample0.txt", 'D');
		System.out.println("result: " + result);
		assertEquals(4, result);
	}

	@Test
	void solvePart1Sample0PlotE() {
		SolutionPart1 solution = new SolutionPart1();
		long result = solution.solve("day12/sample0.txt", 'E');
		System.out.println("result: " + result);
		assertEquals(24, result);
	}

	@Test
	void solvePart1Sample0() {
		SolutionPart1 solution = new SolutionPart1();
		long result = solution.solveAllPlantTypes("day12/sample0.txt");
		System.out.println("result: " + result);
		assertEquals(140, result);
	}

	@Test
	void solvePart1Sample1PlotO() {
		SolutionPart1 solution = new SolutionPart1();
		long result = solution.solve("day12/sample1.txt", 'O');
		System.out.println("result: " + result);
		assertEquals(756, result);
	}

	@Test
	void solvePart1Sample1PlotX() {
		SolutionPart1 solution = new SolutionPart1();
		long result = solution.solve("day12/sample1.txt", 'X');
		System.out.println("result: " + result);
		assertEquals(4 * 4, result);
	}

	@Test
	void solvePart1Sample1() {
		SolutionPart1 solution = new SolutionPart1();
		long result = solution.solveAllPlantTypes("day12/sample1.txt");
		System.out.println("result: " + result);
		assertEquals(772, result);
	}

	@Test
	void solvePart1Sample2() {
		SolutionPart1 solution = new SolutionPart1();
		long result = solution.solveAllPlantTypes("day12/sample2.txt");
		System.out.println("result: " + result);
		assertEquals(1930, result);
	}

	@Test
	void solvePart1() {
		SolutionPart1 solution = new SolutionPart1();
		long result = solution.solveAllPlantTypes("day12/input.txt");
		System.out.println("result: " + result);
		assertEquals(1359028, result);
	}
}