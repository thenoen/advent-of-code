package sk.thenoen.aoc2024.day16;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

import sk.thenoen.aoc.Utils;

public class Solution {

	public long solvePart1(String inputPath) {
		final List<Traveller> travellersAtEnd = this.solve(inputPath);

		final List<Traveller> sortedTravellers = travellersAtEnd.stream()
																.sorted((t1, t2) -> Long.compare(t1.getPathCost(), t2.getPathCost()))
																.toList();

		final long pathCost = sortedTravellers.getFirst().getPathCost();
		return pathCost + 1;
	}

	public long solvePart2(String inputPath) {
		final List<Traveller> travellersAtEnd = this.solve(inputPath);

		final List<Traveller> sortedTravellers = travellersAtEnd.stream()
																.sorted((t1, t2) -> Long.compare(t1.getPathCost(), t2.getPathCost()))
																.toList();

		final long minPathCost = sortedTravellers.getFirst().getPathCost();

		Set<Position> minPathPositions = sortedTravellers.stream()
														 .filter(t -> t.getPathCost() == minPathCost)
														 .map(t -> t.visitedPositions)
														 .flatMap(List::stream)
														 .collect(Collectors.toSet());

		return minPathPositions.size() + 2; // start and end
	}

	public List<Traveller> solve(String inputPath) {
		final ArrayList<String> lines = Utils.loadLines(inputPath);

		char[][] maze = new char[lines.size()][lines.get(0).length()];

		Position start = null;
		Position end = null;

		for (int x = 0; x < lines.size(); x++) {
			//			maze[x] = lines.get(x).toCharArray();
			for (int y = 0; y < lines.get(x).toCharArray().length; y++) {
				maze[x][y] = lines.get(x).toCharArray()[y];
				if (maze[x][y] == 'S') {
					start = new Position(x, y);
				}
				if (maze[x][y] == 'E') {
					end = new Position(x, y);
				}
			}
		}

		Traveller traveller = new Traveller(start, Rotation.RIGHT);
		List<Traveller> travellers = new ArrayList<>();
		travellers.add(traveller);

		List<Traveller> travellersAtEnd = new ArrayList<>();
		long minEndPathCost = Long.MAX_VALUE;

		Map<Position, Long> positionCache = new HashMap<>();

		while (!travellers.isEmpty()) {
			List<Traveller> newTravellers = new ArrayList<>();
			for (int i = 0; i < travellers.size(); i++) {
				final Traveller t = travellers.get(i);

				for (Rotation rotation : Rotation.values()) {
					final boolean isEnd = searchEnd(rotation, t, maze, newTravellers);
					if (isEnd) {
						travellersAtEnd.add(t);
						minEndPathCost = Math.min(minEndPathCost, t.getPathCost());
					}
				}
			}
			long localMin = minEndPathCost;
			travellers = newTravellers;
			travellers.removeIf(t -> positionCache.get(t.currentPosition) != null && positionCache.get(t.currentPosition) < t.getPathCost());
			travellers.removeIf(t -> t.getPathCost() > localMin);
			travellers.forEach(t -> positionCache.put(t.currentPosition, t.getPathCost()));

			System.out.println("number of travellers: " + travellers.size());

		}

		return travellersAtEnd;
	}

	private boolean searchEnd(Rotation rotation, Traveller t, char[][] maze, List<Traveller> newTravellers) {
		final Position newPosition = rotation.apply(t.currentPosition);
		if (isFree(newPosition, maze) && !t.visited(newPosition)) {
			final Traveller clonedTraveller = t.clone();
			clonedTraveller.addVisitedPosition(newPosition);
			clonedTraveller.addRotation(rotation);
			newTravellers.add(clonedTraveller);
			if (isEnd(newPosition, maze)) {
				return true;
			}
		}
		return false;
	}

	private boolean isFree(Position position, char[][] maze) {
		return maze[position.x][position.y] == '.' || maze[position.x][position.y] == 'E';
	}

	private boolean isEnd(Position position, char[][] maze) {
		return maze[position.x][position.y] == 'E';
	}

	private static void printMaze(char[][] maze, List<Traveller> travellers) {
		StringBuilder sb = new StringBuilder();
		for (int x = 0; x < maze.length; x++) {
			for (int y = 0; y < maze[x].length; y++) {
				sb.append(maze[x][y]);
			}
			sb.append("\n");
		}
		sb.append("\n");
		System.out.println(sb);
	}

	//	private static void print(char[][] maze, List<Traveller> travellers) {
	//		StringBuilder sb = new StringBuilder();
	//		for (int x = 0; x < maze.length; x++) {
	//			for (int y = 0; y < maze[x].length; y++) {
	//				sb.append(maze[x][y]);
	//			}
	//			sb.append("\n");
	//		}
	//		sb.append("\n");
	//		System.out.println(sb);
	//	}

	private record Position(int x, int y) {

		@Override
		public String toString() {
			return "[" + x + "," + y + ']';
		}
	}

	private class Traveller {

		private Position currentPosition;
		private Rotation currentRotation;

		private List<Position> visitedPositions = new ArrayList<>();
		private List<Rotation> rotations = new ArrayList<>();

		public Traveller(Position currentPosition, Rotation currentRotation) {
			this.currentPosition = currentPosition;
			this.currentRotation = currentRotation;
		}

		public void addVisitedPosition(Position position) {
			visitedPositions.add(position);
			currentPosition = position;
		}

		public boolean visited(Position position) {
			return visitedPositions.contains(position);
		}

		public void addRotation(Rotation rotation) {
			if (rotations.isEmpty() || !rotations.getLast().equals(rotation)) {
				rotations.add(rotation);
				currentRotation = rotation;
			}
		}

		public Traveller clone() {
			Traveller cloned = new Traveller(currentPosition, currentRotation);
			cloned.visitedPositions = new ArrayList<>(visitedPositions);
			cloned.rotations = new ArrayList<>(rotations);
			cloned.currentRotation = currentRotation;
			cloned.currentPosition = currentPosition;
			return cloned;
		}

		public long getPathCost() {
			return rotations.size() * 1000 + visitedPositions.size();
		}

		@Override
		public String toString() {
			return currentPosition + " " + currentRotation + " " + visitedPositions.subList(0, Math.min(10, visitedPositions.size()));
		}
	}

	private enum Rotation {
		UP(x -> x - 1, y -> y),
		RIGHT(x -> x, y -> y + 1),
		DOWN(x -> x + 1, y -> y),
		LEFT(x -> x, y -> y - 1);

		private Function<Integer, Integer> xDirection;
		private Function<Integer, Integer> yDirection;

		Rotation(Function<Integer, Integer> xDirection, Function<Integer, Integer> yDirection) {
			this.xDirection = xDirection;
			this.yDirection = yDirection;
		}

		private Position[][] cache = new Position[200][200]; // the input is known

		public Position apply(Position position) {
			final Integer newX = xDirection.apply(position.x);
			final Integer newY = yDirection.apply(position.y);
			if(cache[newX][newY] == null) {
				cache[newX][newY] = new Position(newX, newY);
			}
			return cache[newX][newY]; // does not help too much
//			return new Position(newX, newY);
		}
	}
}
