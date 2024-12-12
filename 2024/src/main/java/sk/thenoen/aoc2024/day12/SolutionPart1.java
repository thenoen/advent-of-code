package sk.thenoen.aoc2024.day12;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import sk.thenoen.aoc.Utils;

public class SolutionPart1 {

	private final int PADDING = 1;

	public long solve(String inputPath, char plantType) {

		final ArrayList<String> lines = Utils.loadLines(inputPath);

		final char[][] garden = loadGarden(lines);

		final char[][] plots = initPlots(garden);
		filterRegions(garden, plots, plantType);
		//		System.out.println("plots:");
		//		print(plots);

		final long perimeter = calculatePrice(plots);

		//		System.out.println("garden:");
		//		print(garden);

		return perimeter;
	}

	public long solveAllPlantTypes(String inputPath) {

		final ArrayList<String> lines = Utils.loadLines(inputPath);

		final char[][] garden = loadGarden(lines);
		Set<Character> plantTypes = new HashSet<>();
		for (int x = 0; x < garden.length; x++) {
			for (int y = 0; y < garden[0].length; y++) {
				if (garden[x][y] != '.') {
					plantTypes.add(garden[x][y]);
				}
			}
		}

		long totalPrice = 0;
		for (Character plantType : plantTypes) {
			final char[][] plots = initPlots(garden);
			filterRegions(garden, plots, plantType);
			//			System.out.println("plots:");
			//			print(plots);
			totalPrice += calculatePrice(plots);
			System.out.println("total price: " + totalPrice);;
		}
		return totalPrice;
	}

	private char[][] loadGarden(ArrayList<String> lines) {
		char[][] garden = new char[lines.size() + 2 * PADDING][lines.getFirst().length() + 2 * PADDING];

		for (int x = 0; x < garden.length; x++) {
			for (int y = 0; y < garden[0].length; y++) {
				garden[x][y] = '.';
			}
		}

		for (int x = 0; x < lines.size(); x++) {
			for (int y = 0; y < lines.getFirst().length(); y++) {
				garden[x + PADDING][y + PADDING] = lines.get(x).charAt(y);
			}
		}

		//		print(garden);
		return garden;
	}

	private static char[][] initPlots(char[][] garden) {
		char[][] plots = new char[garden.length][garden[0].length];

		for (int x = 0; x < garden.length; x++) {
			for (int y = 0; y < garden[0].length; y++) {
				plots[x][y] = '.';
			}
		}
		return plots;
	}

	private static long calculatePrice(char[][] plots) {
		Map<Character, Integer> plotTypes = new HashMap<>();
		for (int x = 0; x < plots.length; x++) {
			for (int y = 0; y < plots[0].length; y++) {
				if (plots[x][y] != '.' && plots[x][y] != '*') {
					plotTypes.computeIfPresent(plots[x][y], (k, v) -> v + 1);
					plotTypes.putIfAbsent(plots[x][y], 1);
				}
			}
		}

		long priceSum = 0;
		for (Character plotType : plotTypes.keySet()) {
			//			System.out.println("--------");
			//			System.out.println("plotType: " + plotType);
			final short[][] fences = findFences(plotType, plots);
//			print("fences", fences);

			long perimeter = 0;
			for (int x = 0; x < fences.length; x++) {
				for (int y = 0; y < fences[0].length; y++) {
					perimeter += fences[x][y];
				}
			}
			final Integer area = plotTypes.get(plotType);
			long price = area * perimeter;
			//			System.out.println("perimeter: " + perimeter);
			//			System.out.println("area: " + area);
			priceSum += price;
		}

		return priceSum;
	}

	private static short[][] findFences(char plotId, char[][] plots) {
		final short[][] fences = initFences(plots);

		for (int x = 0; x < plots.length; x++) {
			for (int y = 0; y < plots[0].length; y++) {
				//				if (!isInsideMap(x, y, plots)) {
				//					continue;
				//				}
				if (plots[x][y] == plotId) {
					if (plots[x - 1][y] == '*') {
						fences[x - 1][y]++;
					}
					if (plots[x + 1][y] == '*') {
						fences[x + 1][y]++;
					}
					if (plots[x][y - 1] == '*') {
						fences[x][y - 1]++;
					}
					if (plots[x][y + 1] == '*') {
						fences[x][y + 1]++;
					}
				}
			}
		}
		return fences;
	}

	private static short[][] initFences(char[][] garden) {
		short[][] fences = new short[garden.length][garden[0].length];

		for (int x = 0; x < fences.length; x++) {
			for (int y = 0; y < fences[0].length; y++) {
				fences[x][y] = 0;
			}
		}
		return fences;
	}

	private static void print(String title, char[][] plots) {
		System.out.println(title + ":");
		for (int x = 0; x < plots.length; x++) {
			for (int y = 0; y < plots[0].length; y++) {
				System.out.print(plots[x][y]);
			}
			System.out.println();
		}
		System.out.println();
	}

	private static void print(String title, short[][] plots) {
		System.out.println(title + ":");
		for (int x = 0; x < plots.length; x++) {
			for (int y = 0; y < plots[0].length; y++) {
				System.out.print(plots[x][y]);
			}
			System.out.println();
		}
		System.out.println();
	}

	private void filterRegions(char[][] garden, char[][] plots, char plantType) {
		for (int x = 0; x < plots.length; x++) {
			for (int y = 0; y < plots[0].length; y++) {
				if (garden[x][y] == plantType) {
					plots[x][y] = plantType;
				}
			}
		}

		//		print(plots);

		char id = 'a';
		int count = 0;
		for (int x = 0; x < plots.length; x++) {
			for (int y = 0; y < plots[0].length; y++) {
				if (plots[x][y] == plantType) {
					groupRegionInGarden(plots, x, y, plantType, id);
					id++;
					count++;
				}
			}
		}
		//		print(plots);
		System.out.println("number of plots with type '" + plantType + "' : " + count);
	}

	private void groupRegionInGarden(char[][] plots, int x, int y, char plantType, char id) {
		//		print(plots);
		if (!isInsideMap(x, y, plots)) {
			return;
		}

		if (plots[x][y] == id || plots[x][y] == '*') {
			return;
		}

		if (plots[x][y] == plantType) {
			plots[x][y] = id;
		} else {
			plots[x][y] = '*';
			return;
		}

		groupRegionInGarden(plots, x - 1, y, plantType, id);
		groupRegionInGarden(plots, x + 1, y, plantType, id);
		groupRegionInGarden(plots, x, y - 1, plantType, id);
		groupRegionInGarden(plots, x, y + 1, plantType, id);

		groupRegionInGarden(plots, x - 1, y - 1, plantType, id);
		groupRegionInGarden(plots, x - 1, y + 1, plantType, id);
		groupRegionInGarden(plots, x + 1, y - 1, plantType, id);
		groupRegionInGarden(plots, x + 1, y + 1, plantType, id);

	}

	private static boolean isInsideMap(int x, int y, char[][] map) {
		return x > -1 && y > -1 &&
			   x < map.length && y < map[0].length;
	}

	private record Plot(int x, int y) {

	}

}
