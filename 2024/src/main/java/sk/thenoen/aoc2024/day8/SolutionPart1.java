package sk.thenoen.aoc2024.day8;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import sk.thenoen.aoc.Utils;

public class SolutionPart1 {

	public long solve(String inputPath) {
		final ArrayList<String> lines = Utils.loadLines(inputPath);

		char[][] map = new char[lines.size()][lines.getFirst().length()];

		Map<Character, List<Position>> antennaTypes = new HashMap<>();

		for (int x = 0; x < map.length; x++) {
			for (int y = 0; y < map[0].length; y++) {
				final char type = lines.get(x).charAt(y);
				map[x][y] = type;
				if (type == '.') {
					continue;
				}
				antennaTypes.computeIfAbsent(type, k -> new ArrayList<>())
							.add(new Position(x, y));
			}
		}

		printMap(map);

		Set<Position> antinodes = new HashSet<>();

		for (List<Position> antennas : antennaTypes.values()) {
			for (int i = 0; i < antennas.size(); i++) {
				for (int j = i + 1; j < antennas.size(); j++) {
//					System.out.println(i + " , " + j);
					Position antennaA = antennas.get(i);
					Position antennaB = antennas.get(j);

					int[] antinodeA = new int[2];
					int[] antinodeB = new int[2];

					final int xDiff = Math.abs(antennaA.x - antennaB.x);
					final int yDiff = Math.abs(antennaA.y - antennaB.y);

					if (antennaA.x - antennaB.x < 0) { // A < B
						antinodeA[0] = antennaA.x - xDiff;
						antinodeB[0] = antennaB.x + xDiff;
					} else {
						antinodeA[0] = antennaB.x - xDiff;
						antinodeB[0] = antennaA.x + xDiff;
					}

					if (antennaA.y - antennaB.y < 0) { // A < B
						antinodeA[1] = antennaA.y - yDiff;
						antinodeB[1] = antennaB.y + yDiff;
					} else {
						antinodeA[1] = antennaA.y + yDiff;
						antinodeB[1] = antennaB.y - yDiff;
					}

					final Position newAntinoteA = new Position(antinodeA[0], antinodeA[1]);
					if (isInsideMap(newAntinoteA, map)) {
						antinodes.add(newAntinoteA);
					}
					final Position newAntinodeB = new Position(antinodeB[0], antinodeB[1]);
					if (isInsideMap(newAntinodeB, map)) {
						antinodes.add(newAntinodeB);
					}
				}

			}
		}

		for (Position antinode : antinodes) {
			map[antinode.x][antinode.y] = '#';
		}

		printMap(map);
		return antinodes.size();
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

	private static boolean isInsideMap(Position nextStep, char[][] map) {
		return nextStep.x > -1 && nextStep.y > -1 &&
			   nextStep.x < map.length && nextStep.y < map[0].length;
	}

	private record Position(int x, int y) {

	}
}
