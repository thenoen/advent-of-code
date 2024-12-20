package sk.thenoen.aoc2024.day6;

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
		List<List<List<Direction>>> directionMap = new ArrayList<>(lines.size());

		for (int x = 0; x < map.length; x++) {
			directionMap.add(new ArrayList<>(lines.get(x).length()));
			for (int y = 0; y < map[0].length; y++) {
				directionMap.get(x).add(new ArrayList<>());
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
					directionMap.get(x).get(y).add(Direction.UP);
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
		boolean rotationDuringPreviousStep = false;

		do {
			nextStep = nextStep(currentDirection, currentStep);

			if (!isInsideMap(nextStep, map)) {
				break;
			}

			if (map[nextStep.x][nextStep.y] == '#') {
				currentDirection = directionOrder.get((currentDirection.index + 1) % 4);
				turnPositions.add(currentStep);
				map[currentStep.x][currentStep.y] = '+';
				directionMap.get(currentStep.x).get(currentStep.y).add(currentDirection);
			} else {
				map[nextStep.x][nextStep.y] = 'o';
				currentStep = nextStep;
				directionMap.get(currentStep.x).get(currentStep.y).add(currentDirection);
			}

			/////////// TEST OBSTACLE ///////////
			Position potentialObstacle = nextStep(currentDirection, currentStep);
			if (!isInsideMap(potentialObstacle, map)) {
				break;
			}
			printSpecialPosition(map, potentialObstacle, '■');
			/// EXPLORE

			final Direction testDirection = directionOrder.get((currentDirection.index + 1) % 4);
			Position testStep = nextStep(testDirection, currentStep);
			while (isInsideMap(testStep, map)) {

				printSpecialPosition(map, testStep, '?');

				//				 first step after potential obstacle turn is obstacle => 180 degree turn
				if (map[testStep.x][testStep.y] == '#') {
					/// EXPLORE 180 DEGREE TURN - find next 180 degree turn
					Direction next180Direction = directionOrder.get((testDirection.index + 1) % 4);
					Position next180Step = nextStep(next180Direction, testStep);
					do {
						if (map[next180Step.x][next180Step.y] == '#') {
							Direction next180Direction2 = directionOrder.get((next180Direction.index + 1) % 4);
							Position next180Step2 = nextStep(next180Direction2, next180Step);
							if (map[next180Step2.x][next180Step2.y] == '#') {
								obstacles.add(potentialObstacle);
								break;
							}
						}
						next180Step = nextStep(next180Direction, next180Step);
					} while (isInsideMap(next180Step, map) && map[next180Step.x][next180Step.y] != '#');

					break;
				}

				if (directionMap.get(testStep.x).get(testStep.y).contains(testDirection)) {
					obstacles.add(potentialObstacle);
					break;
				} else {
					Position oneMoreStep = nextStep(testDirection, testStep);
					if (isInsideMap(oneMoreStep, map) && map[oneMoreStep.x][oneMoreStep.y] == '#') {
						//						printMap(map);
						//						printMap(directionMap);
						final Direction nextRotation = directionOrder.get((testDirection.index + 1) % 4);
						if (directionMap.get(testStep.x).get(testStep.y).contains(nextRotation)) {
							obstacles.add(potentialObstacle);
							break;
						}
						break;
					}
				}
				testStep = nextStep(testDirection, testStep);
			}

			//			printMap(map);
			//			printMap(directionMap);
			//			Thread.sleep(100);
			System.out.println("---------------------------");

		} while (isInsideMap(nextStep, map));

		printMap(map);
		printMap(directionMap);

		final HashSet<Position> uniqueObstacles = new HashSet<>(obstacles);
		uniqueObstacles.forEach(obstacle -> map[obstacle.x][obstacle.y] = '■');
		printMap(map);

		return uniqueObstacles.size();
	}

	private static char printSpecialPosition(char[][] map, Position potentialObstacle, char c) {
		char bak1 = map[potentialObstacle.x][potentialObstacle.y];
		map[potentialObstacle.x][potentialObstacle.y] = c;
//		printMap(map);
		map[potentialObstacle.x][potentialObstacle.y] = bak1;
		return bak1;
	}

	private static Position nextStep(Direction currentDirection, Position currentStep) {
		return new Position(currentDirection.xDirection.apply(currentStep.x),
							currentDirection.yDirection.apply(currentStep.y));
	}

	private static boolean isObstacleInBetween(Direction currentDirection, Position currentStep, Position obstacle, char[][] map) {
		Position tmp = nextStep(currentDirection, currentStep);
		while (!obstacle.equals(tmp) && (tmp.x < map.length && tmp.y < map[0].length)) {
			if (map[tmp.x][tmp.y] == '#') {
				return true;
			}
			tmp = nextStep(currentDirection, tmp);
		}
		return false;
	}

	private static void printObstacle(char[][] map, Position obstacle) throws InterruptedException {
		map[obstacle.x][obstacle.y] = '■';
		printMap(map);
		//		Thread.sleep(100);
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
		System.out.println();
	}

	private static void printMap(List<List<List<Direction>>> puzzle) {
		for (int x = 0; x < puzzle.size(); x++) {
			for (int y = 0; y < puzzle.get(0).size(); y++) {
				//				System.out.print(puzzle.get(x).get(y).size());
				if (puzzle.get(x).get(y).size() == 0) {
					System.out.print(".");
				}
				if (puzzle.get(x).get(y).size() == 1) {
					System.out.print(puzzle.get(x).get(y).get(0).symbol);
				}
				if (puzzle.get(x).get(y).size() > 1) {
					System.out.print("+");
				}
			}
			System.out.println();
		}
		System.out.println("\n");
	}

	private enum Direction {
		UP(0, x -> x - 1, y -> y, '|'),
		RIGHT(1, x -> x, y -> y + 1, '-'),
		DOWN(2, x -> x + 1, y -> y, '|'),
		LEFT(3, x -> x, y -> y - 1, '-');

		int index;
		private Function<Integer, Integer> xDirection;
		private Function<Integer, Integer> yDirection;
		char symbol;

		Direction(int index, Function<Integer, Integer> xDirection, Function<Integer, Integer> yDirection, char symbol) {
			this.index = index;
			this.xDirection = xDirection;
			this.yDirection = yDirection;
			this.symbol = symbol;
		}
	}

	private record Position(int x, int y) {

	}
}
