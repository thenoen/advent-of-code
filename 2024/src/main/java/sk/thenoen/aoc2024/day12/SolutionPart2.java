package sk.thenoen.aoc2024.day12;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import sk.thenoen.aoc.Utils;

public class SolutionPart2 {

	private final int PADDING = 2;

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
			print("plots", plots);
			totalPrice += calculatePrice(plots);
			//			System.out.println("total price: " + totalPrice);
			System.out.println("--------------------------------------------------------------------------");
			;
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
			System.out.println("--------");
			System.out.println("plotType: " + plotType);
			final int[][] fences = findFences(plotType, plots);
			print("plots", plots);
			print("fences", fences);

			List<Fence> fenceList = new ArrayList<>();
			for (int x = 0; x < plots.length; x++) {
				for (int y = 0; y < plots[0].length; y++) {
					if (fences[x][y] != 0) {
						fenceList.add(new Fence(x, y));
					}
				}
			}

			Fence start = fenceList.get(0);
			int sideCount = followFences(start, fences);

			final Integer area = plotTypes.get(plotType);
			//			long price = area * sideCount;
			//			System.out.println("sideCount: " + sideCount);
			//			System.out.println("area: " + area);
			//			System.out.println("price: " + price);
			//			priceSum += price;
		}

		return priceSum;
	}

	private static int followFences(Fence start, int[][] plots) {
		int x = start.x;
		int y = start.y;
		int corners = 0;
		do {
			System.out.println(plots[x][y] + " ");
			if (isInsideMap(x, y + 1, plots) && plots[x][y + 1] != 0) {
				corners++;
				System.out.println();
				while (plots[x][y + 1] != 0) {
					System.out.println(plots[x][y + 1] + " ");
					y++;
				}
			}
			if (isInsideMap(x + 1, y, plots) && plots[x + 1][y] != 0) {
				corners++;
//				System.out.println();
				while (plots[x + 1][y] != 0) {
					System.out.println(plots[x + 1][y] + " ");
					x++;
				}
			}
			if (isInsideMap(x, y - 1, plots) && plots[x][y - 1] != 0) {
				corners++;
//				System.out.println();
				while (plots[x][y - 1] != 0) {
					System.out.println(plots[x][y - 1] + " ");
					y--;
				}
			}
			if (isInsideMap(x - 1, y, plots) && plots[x - 1][y] != 0) {
				corners++;
//				System.out.println();
				while (plots[x - 1][y] != 0) {
					System.out.println(plots[x - 1][y] + " ");
					x--;
				}
			}
		} while (x != start.x && y != start.y);
		return corners;
	}

	private static int[][] findFences(char plotId, char[][] plots) {
		final int[][] fences = initFences(plots);

		for (int x = 0; x < plots.length; x++) {
			for (int y = 0; y < plots[0].length; y++) {
				//				if (!isInsideMap(x, y, plots)) {
				//					continue;
				//				}
				if (plots[x][y] == plotId) {
					if (plots[x + 1][y] != plotId) {
						fences[x + 1][y]++;
					}
					if (plots[x - 1][y] != plotId) {
						fences[x - 1][y]++;
					}
					if (plots[x][y + 1] != plotId) {
						fences[x][y + 1]++;
					}
					if (plots[x][y - 1] != plotId) {
						fences[x][y - 1]++;
					}

					if (plots[x + 1][y + 1] != plotId) {
						fences[x + 1][y + 1]++;
					}
					if (plots[x + 1][y - 1] != plotId) {
						fences[x + 1][y - 1]++;
					}
					if (plots[x - 1][y + 1] != plotId) {
						fences[x - 1][y + 1]++;
					}
					if (plots[x - 1][y - 1] != plotId) {
						fences[x - 1][y - 1]++;
					}
				}
			}
		}
		return fences;
	}

	private static int[][] initFences(char[][] garden) {
		int[][] fences = new int[garden.length][garden[0].length];

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

	private static void print(String title, int[][] plots) {
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

		//		print("plots", plots);

		char id = 'a';
		int count = 0;
		for (int x = 0; x < plots.length; x++) {
			for (int y = 0; y < plots[0].length; y++) {
				if (plots[x][y] == plantType) {
					System.out.println("PlantType: " + plantType + " -> " + id);
					groupRegionInGarden(plots, x, y, plantType, id);
					//					print("plots", plots);
					id++;
					count++;
				}
			}
		}
		//		print("plots", plots);
		System.out.println("number of plots with type '" + plantType + "' : " + count);
		//		System.out.println("-------------");
	}

	private void groupRegionInGarden(char[][] plots, int x, int y, char plantType, char id) {
		//				print("plots", plots);
		if (!isInsideMap(x, y, plots)) {
			return;
		}

		if (plots[x][y] == id || plots[x][y] == '.') {
			return;
		}

		if (plots[x][y] == plantType) {
			plots[x][y] = id;
		}
		//		else {
		//			plots[x][y] = '*';
		//			return;
		//		}

		groupRegionInGarden(plots, x - 1, y, plantType, id);
		groupRegionInGarden(plots, x + 1, y, plantType, id);
		groupRegionInGarden(plots, x, y - 1, plantType, id);
		groupRegionInGarden(plots, x, y + 1, plantType, id);

		//				groupRegionInGarden(plots, x - 1, y - 1, plantType, id);
		//				groupRegionInGarden(plots, x - 1, y + 1, plantType, id);
		//				groupRegionInGarden(plots, x + 1, y - 1, plantType, id);
		//				groupRegionInGarden(plots, x + 1, y + 1, plantType, id);

	}

	private static boolean isInsideMap(int x, int y, char[][] map) {
		return x > -1 && y > -1 &&
			   x < map.length && y < map[0].length;
	}

	private static boolean isInsideMap(int x, int y, int[][] map) {
		return x > -1 && y > -1 &&
			   x < map.length && y < map[0].length;
	}

	private record Fence(int x, int y) {

	}

}
