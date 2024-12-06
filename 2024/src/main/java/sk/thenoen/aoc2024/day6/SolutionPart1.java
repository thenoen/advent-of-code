package sk.thenoen.aoc2024.day6;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.function.Function;

import sk.thenoen.aoc.Utils;

public class SolutionPart1 {

	public long solve(String inputPath) throws InterruptedException {
		final ArrayList<String> lines = Utils.loadLines(inputPath);

		char[][] map = new char[lines.size()][lines.getFirst().length()];

		for (int x = 0; x < map.length; x++) {
			for (int y = 0; y < map[0].length; y++) {
				map[x][y] = lines.get(x).charAt(y);
			}
		}

		printMap(map);

		Position start = null;

		mainLoop:
		for (int x = 0; x < map.length; x++) {
			for (int y = 0; y < map[0].length; y++) {
				if (map[x][y] == '^') {
					start = new Position(x, y);
					break mainLoop;
				}
			}
		}

		Direction currentDirection = Direction.UP;
		Position nextStep = null;
		Position currentStep = start;
		HashMap<Integer, Direction> directionOrder = new HashMap<>(4);
		directionOrder.put(0, Direction.UP);
		directionOrder.put(1, Direction.RIGHT);
		directionOrder.put(2, Direction.DOWN);
		directionOrder.put(3, Direction.LEFT);

		do {
			nextStep = new Position(currentDirection.xDirection.apply(currentStep.x), currentDirection.yDirection.apply(currentStep.y));

			if (!isInsideMap(nextStep, map)) {
				break;
			}

			if (map[nextStep.x][nextStep.y] == '#') {
				currentDirection = directionOrder.get((currentDirection.index + 1) % 4);
			} else {
				map[nextStep.x][nextStep.y] = 'o';
				currentStep = nextStep;
			}

//			printMap(map);
//			Thread.sleep(600);

		} while (isInsideMap(nextStep, map));

		int count = 0;

		for (int x = 0; x < map.length; x++) {
			for (int y = 0; y < map[0].length; y++) {
				if (map[x][y] == 'o' || map[x][y] == '^') {
					count++;
				}
			}
		}

		return count;
	}

	private static boolean isInsideMap(Position nextStep, char[][] map) {
		return nextStep.x > -1 && nextStep.y > -1 &&
			   nextStep.x < map.length && nextStep.y < map[0].length;
	}

	private static void printMap(char[][] puzzle) {
		for (int x = 0; x < puzzle.length; x++) {
			for (int y = 0; y < puzzle[0].length; y++) {
				System.out.print(puzzle[x][y]);
			}
			System.out.println();
		}
		System.out.println("\n\n");
	}

	private enum Direction {
		UP(0, x -> x - 1, y -> y),
		RIGHT(1, x -> x, y -> y + 1),
		DOWN(2, x -> x + 1, y -> y),
		LEFT(3, x -> x, y -> y - 1);

		int index;
		private Function<Integer, Integer> xDirection;
		private Function<Integer, Integer> yDirection;

		Direction(int index, Function<Integer, Integer> xDirection, Function<Integer, Integer> yDirection) {
			this.index = index;
			this.xDirection = xDirection;
			this.yDirection = yDirection;
		}
	}

	private record Position(int x, int y) {

	}
}
