package sk.thenoen.aoc2024.day6;

import java.awt.print.PrinterAbortException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.function.Function;

import sk.thenoen.aoc.Utils;

public class SolutionPart2 {

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

		List<Position> turnPositions = new ArrayList<>();
		List<Position> obstacles = new ArrayList<>();

		do {
			nextStep = new Position(currentDirection.xDirection.apply(currentStep.x), currentDirection.yDirection.apply(currentStep.y));

			if (!isInsideMap(nextStep, map)) {
				break;
			}

			if (map[nextStep.x][nextStep.y] == '#') {
				currentDirection = directionOrder.get((currentDirection.index + 1) % 4);
				turnPositions.add(currentStep);
				map[currentStep.x][currentStep.y] = '+';
			} else {
				map[nextStep.x][nextStep.y] = 'o';
				currentStep = nextStep;
			}

			if (turnPositions.size() > 2) {
				if (currentDirection == Direction.LEFT) {
					Position a = turnPositions.get(turnPositions.size() - 3);
					Position b = turnPositions.get(turnPositions.size() - 2);
					Position c = turnPositions.get(turnPositions.size() - 1);

					Position obstacle = new Position(currentDirection.xDirection.apply(c.x), currentDirection.yDirection.apply(a.y));
					boolean isObstacleInBetween = isObstacleInBetween(currentDirection, currentStep, obstacle, map);

					if (a.x == b.x && b.y == c.y && map[obstacle.x][obstacle.y] == '.' && !isObstacleInBetween) {
						obstacles.add(obstacle);
						printObstacle(map, obstacle);
					}
				}

				if (currentDirection == Direction.UP) {
					Position a = turnPositions.get(turnPositions.size() - 3);
					Position b = turnPositions.get(turnPositions.size() - 2);
					Position c = turnPositions.get(turnPositions.size() - 1);

					Position obstacle = new Position(currentDirection.xDirection.apply(a.x), currentDirection.yDirection.apply(c.y));
					boolean isObstacleInBetween = isObstacleInBetween(currentDirection, currentStep, obstacle, map);

					if (a.y == b.y && b.x == c.x && map[obstacle.x][obstacle.y] == '.' && !isObstacleInBetween) {
						obstacles.add(obstacle);
						printObstacle(map, obstacle);
					}
				}

				if (currentDirection == Direction.RIGHT) {
					Position a = turnPositions.get(turnPositions.size() - 3);
					Position b = turnPositions.get(turnPositions.size() - 2);
					Position c = turnPositions.get(turnPositions.size() - 1);

					Position obstacle = new Position(currentDirection.xDirection.apply(c.x), currentDirection.yDirection.apply(a.y));
					boolean isObstacleInBetween = isObstacleInBetween(currentDirection, currentStep, obstacle, map);

					if (a.x == b.x && b.y == c.y && map[obstacle.x][obstacle.y] == '.' && !isObstacleInBetween) {
						obstacles.add(obstacle);
						printObstacle(map, obstacle);
					}
				}

				if (currentDirection == Direction.DOWN) {
					Position a = turnPositions.get(turnPositions.size() - 3);
					Position b = turnPositions.get(turnPositions.size() - 2);
					Position c = turnPositions.get(turnPositions.size() - 1);

					Position obstacle = new Position(currentDirection.xDirection.apply(a.x), currentDirection.yDirection.apply(c.y));
					boolean isObstacleInBetween = isObstacleInBetween(currentDirection, currentStep, obstacle, map);

					if (a.y == b.y && b.x == c.x && map[obstacle.x][obstacle.y] == '.' && !isObstacleInBetween) {
						obstacles.add(obstacle);
						printObstacle(map, obstacle);
					}
				}
			}

			printMap(map);
			Thread.sleep(200);

		} while (isInsideMap(nextStep, map));

		final HashSet<Position> uniqueObstacles = new HashSet<>(obstacles);
		uniqueObstacles.forEach(obstacle -> map[obstacle.x][obstacle.y] = '■');
		printMap(map);

		return uniqueObstacles.size();
	}

	private static boolean isObstacleInBetween(Direction currentDirection, Position currentStep, Position obstacle, char[][] map) {
		Position tmp = new Position(currentDirection.xDirection.apply(currentStep.x),
									currentDirection.yDirection.apply(currentStep.y));
		while (!obstacle.equals(tmp) && (tmp.x < map.length && tmp.y < map[0].length)) {
			if(map[tmp.x][tmp.y] == '#') {
				return true;
			}
			tmp = new Position(currentDirection.xDirection.apply(tmp.x),
							   currentDirection.yDirection.apply(tmp.y));
		}
		return false;
	}

	private static void printObstacle(char[][] map, Position obstacle) throws InterruptedException {
		map[obstacle.x][obstacle.y] = '■';
		printMap(map);
//		Thread.sleep(300);
		map[obstacle.x][obstacle.y] = '.';
		printMap(map);
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
