package sk.thenoen.aoc2024.day10;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import sk.thenoen.aoc.Utils;

public class SolutionPart1 {

	public int solve(String inputPath) {
		final ArrayList<String> lines = Utils.loadLines(inputPath);

		Position[][] map = new Position[lines.size()][lines.getFirst().length()];

		for (int x = 0; x < map.length; x++) {
			for (int y = 0; y < map[0].length; y++) {
				final char heightChar = lines.get(x).charAt(y);
				int height = -1;
				if (heightChar != '.') {
					height = Integer.parseInt(heightChar + "");
				}
				final Position type = new Position(x, y, height);
				map[x][y] = type;
			}
		}

		printMap(map);

		List<List<Position>> paths = new ArrayList<>();

		for (int x = 0; x < map.length; x++) {
			for (int y = 0; y < map[0].length; y++) {
				final Position position = map[x][y];
				if (position.height == 0) {
					List<Position> path = new ArrayList<>();
					path.add(position);
					paths.add(path);
				}
			}
		}

		//////////// FIND PATHS /////////////
		boolean canContinue = true;

		while (canContinue) {
			canContinue = false;
			for (int i = 0; i < paths.size(); i++) {
				final List<Position> path = paths.get(i);
				final Optional<Position> top = getPosition(path.getLast().x - 1, path.getLast().y, map);
				final Optional<Position> right = getPosition(path.getLast().x, path.getLast().y + 1, map);
				final Optional<Position> bottom = getPosition(path.getLast().x + 1, path.getLast().y, map);
				final Optional<Position> left = getPosition(path.getLast().x, path.getLast().y - 1, map);

				final List<Position> nextPositions = Stream.of(top, right, bottom, left)
														   .filter(Optional::isPresent)
														   .map(Optional::get)
														   .filter(nextPosition -> nextPosition.height - path.getLast().height == 1)
														   .toList();
				if (!nextPositions.isEmpty()) {
					canContinue = true;
				}
				if (nextPositions.size() == 1) {
					path.add(nextPositions.getFirst());
				} else if (nextPositions.size() > 1) {
					paths.remove(i);
					nextPositions.forEach(nextPosition -> {
						List<Position> newPath = new ArrayList<>(path);
						newPath.add(nextPosition);
						paths.addFirst(newPath);
					});
					i += nextPositions.size() - 1;
				}
			}

		}

		final Map<Position, List<Position>> collect = paths.stream()
														   .filter(path -> path.getLast().height == 9)
														   .collect(Collectors.groupingBy(List::getFirst))
														   .entrySet()
														   .stream()
														   .map(e -> new AbstractMap.SimpleEntry<>(e.getKey(),
																								   e.getValue()
																									.stream()
																									.map(List::getLast)
																									.distinct()
																									.toList()))
														   .collect(Collectors.toMap(AbstractMap.SimpleEntry::getKey,
																					 AbstractMap.SimpleEntry::getValue));
		final int sum = collect.values()
							   .stream()
							   .map(List::size)
							   .mapToInt(Integer::intValue)
							   .sum();

		return sum;
	}

	private Optional<Position> getPosition(int x, int y, Position[][] map) {
		if (x < 0 || x >= map.length || y < 0 || y >= map[0].length) {
			return Optional.empty();
		}
		return Optional.of(map[x][y]);
	}

	private record Position(int x, int y, int height) {

	}

	private static void printMap(Position[][] map) {
		for (int x = 0; x < map.length; x++) {
			for (int y = 0; y < map[0].length; y++) {
				final Position position = map[x][y];
				if (position.height < 0) {
					System.out.print('.');
				} else {
					System.out.print(position.height);

				}
			}
			System.out.println();
		}
		System.out.println();
	}

}
