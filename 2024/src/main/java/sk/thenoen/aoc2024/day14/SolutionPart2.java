package sk.thenoen.aoc2024.day14;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import sk.thenoen.aoc.Utils;

public class SolutionPart2 {

	private static final String ROBOT_REGEX = "p=*(.*?),(.*?)\sv=(.*?),(.*)";

	public int solve(String inputPath, int width, int height, int seconds) {
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

			int robotCount = 0;
			for (int x = 0; x < 20; x++) {
				for (int y = 0; y < 20; y++) {
					robotCount += floor[y][x].size();
				}
			}

//			System.out.println("second: " + i);
			if (robotCount < 10) {
				cleanFloor(floor);
				positionRobots(floor, robots);
				printFloor(floor);
				System.out.println("second: " + i);
				sleep(200);
			}



		}


		return 0;
	}

	private static void sleep(int i) {
		try {
			Thread.sleep(i);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
	}

	private void printFloor(List<Robot>[][] floor) {
		StringBuilder stringBuilder = new StringBuilder();
		for (int x = 0; x < floor[0].length; x++) {
			for (int y = 0; y < floor.length; y++) {
				if (floor[y][x].isEmpty()) {
//					System.out.print(". ");
					stringBuilder.append(" ");
				} else {
//					System.out.print(floor[y][x].size() + " ");
//					System.out.print(floor[y][x].size() );
//					stringBuilder.append(floor[y][x].size());
					stringBuilder.append("*");
				}
			}
//			System.out.println();
			stringBuilder.append("\n");
		}
//		System.out.println();
		System.out.println(stringBuilder);
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
