package sk.thenoen.aoc2024.day15;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import sk.thenoen.aoc.Utils;

public class Solution {

	public long solvePart1(String inputPath) {
		final ArrayList<String> lines = Utils.loadLines(inputPath);

		int emptyLineIndex = -1;
		for (int i = 0; i < lines.size(); i++) {
			final String line = lines.get(i);
			if (line.isEmpty()) {
				emptyLineIndex = i;
				break;
			}
		}

		final int width = lines.getFirst().length(); // y
		final int height = emptyLineIndex; // x
		Item[][] warehouse = new Item[height][width];

		Position robotPosition = null;

		for (int x = 0; x < emptyLineIndex; x++) {
			final String line = lines.get(x);
			for (int y = 0; y < line.length(); y++) {
				final char symbol = line.charAt(y);
				switch (symbol) {
					case '#':
						warehouse[x][y] = new Wall(x, y);
						break;
					case '.':
						warehouse[x][y] = new Empty(x, y);
						break;
					case 'O':
						warehouse[x][y] = new Box(x, y);
						break;
					case '@':
						warehouse[x][y] = new Robot(x, y);
						robotPosition = new Position(x, y);
						break;
				}
			}
		}

		print(warehouse);

		final List<Direction> directions = lines.subList(emptyLineIndex + 1, lines.size())
												.stream()
												.flatMapToInt(String::chars)
												.mapToObj(c -> switch (c) {
													case '^' -> Direction.UP;
													case '>' -> Direction.RIGHT;
													case 'v' -> Direction.DOWN;
													case '<' -> Direction.LEFT;
													default -> throw new IllegalArgumentException("Invalid direction: " + c);
												})
												.toList();

		for (Direction direction : directions) {
			System.out.println(direction);
			final Optional<Position> firstEmptyPosition = findFirstEmptyPosition(warehouse, robotPosition, direction);
			if (firstEmptyPosition.isPresent()) {
				Position emptyPosition = firstEmptyPosition.get();
				Position prevPosition;
				do {
					prevPosition = direction.applyReverse(emptyPosition);
					warehouse[emptyPosition.x][emptyPosition.y] = warehouse[prevPosition.x][prevPosition.y];
					emptyPosition = prevPosition;
				} while (!emptyPosition.equals(robotPosition));
				warehouse[robotPosition.x][robotPosition.y] = new Empty(robotPosition.x, robotPosition.y);
				robotPosition = direction.apply(robotPosition);
			}
			print(warehouse);
//			getSleep();
		}

		long sumOfGPS = 0;
		for (int x = 0; x < warehouse.length; x++) {
			for (int y = 0; y < warehouse[0].length; y++) {
				if (warehouse[x][y] instanceof Box) {
					sumOfGPS += 100L * x + y;
				}
			}
		}

		return sumOfGPS;
	}

	private static void getSleep() {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
	}

	private Optional<Position> findFirstEmptyPosition(Item[][] warehouse, Position robotPosition, Direction direction) {
		Position nextPosition = robotPosition;
		while (isInside(nextPosition, warehouse) && !isEmpty(warehouse, nextPosition) && !isWall(warehouse, nextPosition)) {
			nextPosition = direction.apply(nextPosition);
		}
		if (!isInside(nextPosition, warehouse)) {
			return Optional.empty();
		}
		if (isWall(warehouse, nextPosition)) {
			return Optional.empty();
		}
		if (isEmpty(warehouse, nextPosition) && !nextPosition.equals(robotPosition)) {
			return Optional.of(nextPosition);
		}
		return Optional.empty();
	}

	boolean isInside(Position position, Item[][] warehouse) {
		return position.x >= 0 && position.x < warehouse.length &&
			   position.y >= 0 && position.y < warehouse[0].length;
	}

	private boolean isEmpty(Item[][] warehouse, Position position) {
		return warehouse[position.x][position.y] instanceof Empty;
	}

	private boolean isWall(Item[][] warehouse, Position position) {
		return warehouse[position.x][position.y] instanceof Wall;
	}

	private static void print(Item[][] warehouse) {
		StringBuilder stringBuilder = new StringBuilder();
		for (int x = 0; x < warehouse.length; x++) {
			for (int y = 0; y < warehouse[0].length; y++) {
				stringBuilder.append(warehouse[x][y].symbol);
			}
			stringBuilder.append("\n");
		}
		stringBuilder.append("\n");
		System.out.println(stringBuilder);
	}

	private sealed class Item permits Wall, Empty, Robot, Box {

		private final char symbol;
		//		final int x;
		//		final int y;

		public Item(char symbol) {
			this.symbol = symbol;
			//			this.x = x;
			//			this.y = y;
		}
	}

	private final class Robot extends Item {

		public Robot(int x, int y) {
			super('@');
		}
	}

	private final class Wall extends Item {

		public Wall(int x, int y) {
			super('#');
		}

	}

	private final class Empty extends Item {

		public Empty(int x, int y) {
			super('.');
		}

	}

	private final class Box extends Item {

		public Box(int x, int y) {
			super('O');
		}
	}

	private static record Position(int x, int y) {
		//
		//		private int x;
		//		private int y;
		//
		//		public Position(int x, int y) {
		//			this.x = x;
		//			this.y = y;
		//		}
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

		public Position apply(Position position) {
			return new Position(xDirection.apply(position.x), yDirection.apply(position.y));
		}

		public Position applyReverse(Position position) {
			return switch (this) {
				case UP -> DOWN.apply(position);
				case RIGHT -> LEFT.apply(position);
				case DOWN -> UP.apply(position);
				case LEFT -> RIGHT.apply(position);
			};
		}
	}

}
