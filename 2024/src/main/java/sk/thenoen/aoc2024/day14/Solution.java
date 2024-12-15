package sk.thenoen.aoc2024.day14;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import sk.thenoen.aoc.Utils;

public class Solution {

	private static final String ROBOT_REGEX = "p=*(.*?),(.*?)\sv=(.*?),(.*)";

	public int solvePart1(String inputPath, int width, int height, int seconds) {
		final ArrayList<String> lines = Utils.loadLines(inputPath);

		List<Robot>[][] floor = new ArrayList[width][height];
		List<Robot> robots = new ArrayList<>();

		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				floor[x][y] = new ArrayList<>();
			}
		}

		final Pattern pattern = Pattern.compile(ROBOT_REGEX);
		for (String line : lines) {
			final Matcher matcher = pattern.matcher(line);
			matcher.find();
			int pX = Integer.parseInt(matcher.group(1));
			int pY = Integer.parseInt(matcher.group(2));

			int vX = Integer.parseInt(matcher.group(3));
			int vY = Integer.parseInt(matcher.group(4));

			final Robot robot = new Robot(pX, pY, vX, vY);
			floor[pX][pY].add(robot);
			robots.add(robot);
		}
		printFloor(floor);

		cleanFloor(floor);
		positionRobots(floor, robots);
		printFloor(floor);

		for (int i = 1; i <= seconds; i++) {
			for (Robot robot : robots) {
				//move robot
				robot.x = (robot.x + robot.vX) % width;
				robot.y = (robot.y + robot.vY) % height;
				if (robot.x < 0) {
					robot.x = width + robot.x;
				}
				if (robot.y < 0) {
					robot.y = height + robot.y;
				}
			}

			cleanFloor(floor);
			positionRobots(floor, robots);
			System.out.println("second: " + i);
			printFloor(floor);
		}

		System.out.println("=============================");
		int halfWidth = width / 2;
		int halfHeight = height / 2;

		// I. quadrant
		int robots1 = 0;
		for (int x = 0; x < halfHeight; x++) {
			for (int y = 0; y < halfWidth; y++) {
				robots1 += floor[y][x].size();
				System.out.print(floor[y][x].size() + " ");
			}
			System.out.println();
		}
		System.out.println();

		// II. quadrant
		int robots2 = 0;
		for (int x = 0; x < halfHeight; x++) {
			for (int y = halfWidth + 1; y < width; y++) {
				robots2 += floor[y][x].size();
				System.out.print(floor[y][x].size() + " ");
			}
			System.out.println();
		}
		System.out.println();

		// III. quadrant
		int robots3 = 0;
		for (int x = halfHeight + 1; x < height; x++) {
			for (int y = 0; y < halfWidth; y++) {
				robots3 += floor[y][x].size();
				System.out.print(floor[y][x].size() + " ");
			}
			System.out.println();
		}
		System.out.println();

		// IV. quadrant
		int robots4 = 0;
		for (int x = halfHeight + 1; x < height; x++) {
			for (int y = halfWidth + 1; y < width; y++) {
				robots4 += floor[y][x].size();
				System.out.print(floor[y][x].size() + " ");
			}
			System.out.println();
		}
		System.out.println();

		System.out.println("robots1: " + robots1);
		System.out.println("robots2: " + robots2);
		System.out.println("robots3: " + robots3);
		System.out.println("robots4: " + robots4);

		return robots1 * robots2 * robots3 * robots4;
	}

	private void printFloor(List<Robot>[][] floor) {
		for (int x = 0; x < floor[0].length; x++) {
			for (int y = 0; y < floor.length; y++) {
				if (floor[y][x].isEmpty()) {
					System.out.print(". ");
				} else {
					System.out.print(floor[y][x].size() + " ");
				}
			}
			System.out.println();
		}
		System.out.println();
	}

	private void cleanFloor(List<Robot>[][] floor) {
		for (int x = 0; x < floor[0].length; x++) {
			for (int y = 0; y < floor.length; y++) {
				floor[y][x].clear();
			}
		}
	}

	private void positionRobots(List<Robot>[][] floor, List<Robot> robots) {
		for (Robot robot : robots) {
			//			floor[robot.y][robot.x].add(robot);
			floor[robot.x][robot.y].add(robot);
		}
	}

	private class Robot {

		private int x;
		private int y;
		private int vX;
		private int vY;

		public Robot(int x, int y, int vX, int vY) {
			this.x = x;
			this.y = y;
			this.vX = vX;
			this.vY = vY;
		}

	}
}
