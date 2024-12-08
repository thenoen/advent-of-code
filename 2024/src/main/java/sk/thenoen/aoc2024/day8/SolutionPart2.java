package sk.thenoen.aoc2024.day8;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import sk.thenoen.aoc.Utils;

public class SolutionPart2 {

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

//		Set<Position> antinodes = new HashSet<>();
		List<Position> antinodes = new ArrayList<>();

		for (List<Position> antennas : antennaTypes.values()) {
			for (int i = 0; i < antennas.size(); i++) {
				for (int j = i + 1; j < antennas.size(); j++) {
					System.out.println(i + " , " + j);
					Position antennaA = antennas.get(i);
					Position antennaB = antennas.get(j);

					//					int[][] antinodesA = new int[map.length][2];
					//					int[][] antinodesB = new int[map.length][2];
					List<int[]> antinodesA = new ArrayList<>();
					List<int[]> antinodesB = new ArrayList<>();

					final int xDiff = Math.abs(antennaA.x - antennaB.x);
					final int yDiff = Math.abs(antennaA.y - antennaB.y);

					int maxXCount = map.length;
					int maxYCount = map[0].length;
					int maxCount = Math.max(maxXCount, maxYCount);

					if (antennaA.x - antennaB.x < 0) { // A < B
						// antinodeA[0] = antennaA.x - xDiff;
						// antinodeB[0] = antennaB.x + xDiff;
						int newAx = antennaA.x - xDiff;
						while (newAx > -1 && newAx < map.length) {
							antinodesA.add(new int[]{newAx, -1});
							newAx -= xDiff;
						}
						int newBx = antennaB.x + xDiff;
						while (newBx > -1 && newBx < map.length) {
							antinodesB.add(new int[]{newBx, -1});
							newBx += xDiff;
						}

					} else {
						//						antinodeA[0] = antennaB.x - xDiff;
						//						antinodeB[0] = antennaA.x + xDiff;
						int newAx = antennaB.x - xDiff;
						//						while (newAx > -1 && newAx < map.length) {
						for (int iA = 0; iA < maxCount; iA++) {
							antinodesA.add(new int[]{newAx, -1});
							newAx -= xDiff;
						}
						int newBx = antennaA.x + xDiff;
						//						while (newBx > -1 && newBx < map.length) {
						for (int iB = 0; iB < maxCount; iB++) {
							antinodesB.add(new int[]{newBx, -1});
							newBx += xDiff;
						}
					}

					if (antennaA.y - antennaB.y < 0) { // A > B
						//						antinodeA[1] = antennaA.y - yDiff;
						//						antinodeB[1] = antennaB.y + yDiff;
						int newAy = antennaA.y - yDiff;
						for (int iA = 0; iA < antinodesA.size(); iA++) {
							antinodesA.get(iA)[1] = newAy;
							newAy -= yDiff;
						}
						int newBy = antennaB.y + yDiff;
						for (int iB = 0; iB < antinodesB.size(); iB++) {
							antinodesB.get(iB)[1] = newBy;
							newBy += yDiff;
						}
					} else {
						//						antinodeA[1] = antennaA.y + yDiff;
						//						antinodeB[1] = antennaB.y - yDiff;
						int newAy = antennaA.y + yDiff;
						for (int iA = 0; iA < antinodesA.size(); iA++) {
							antinodesA.get(iA)[1] = newAy;
							newAy += yDiff;
						}
						int newBy = antennaB.y - yDiff;
						for (int iB = 0; iB < antinodesB.size(); iB++) {
							antinodesB.get(iB)[1] = newBy;
							newBy -= yDiff;
						}
					}

					for (int[] antinodeA : antinodesA) {
						final Position position = new Position(antinodeA[0], antinodeA[1]);
						if (isInsideMap(position, map)) {
							antinodes.add(position);
						}
					}

					for (int[] antinodeB : antinodesB) {
						final Position position = new Position(antinodeB[0], antinodeB[1]);
						if (isInsideMap(position, map)) {
							antinodes.add(position);
						}
					}

					System.out.println("antinodes:" + antinodes.size());
				}

			}
		}

		for (Position antinode : antinodes) {
			map[antinode.x][antinode.y] = '#';
		}

		System.out.println("------------------------");
		printMap(map);

		int count = 0;
		for (int x = 0; x < map.length; x++) {
			for (int y = 0; y < map[0].length; y++) {
				if(map[x][y] != '.') {
					count++;
				}
			}
		}

		return count;
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
