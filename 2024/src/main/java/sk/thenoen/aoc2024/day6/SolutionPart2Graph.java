package sk.thenoen.aoc2024.day6;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Function;

import sk.thenoen.aoc.Utils;

public class SolutionPart2Graph {

	HashMap<Integer, Direction> directionOrder = HashMap.newHashMap(4);

	public long solve(String inputPath) throws InterruptedException {
		directionOrder.put(0, Direction.UP);
		directionOrder.put(1, Direction.RIGHT);
		directionOrder.put(2, Direction.DOWN);
		directionOrder.put(3, Direction.LEFT);

		final ArrayList<String> lines = Utils.loadLines(inputPath);

		char[][] map = new char[lines.size()][lines.getFirst().length()];
		Node[][] graph = new Node[lines.size()][lines.getFirst().length()];
		List<List<List<Direction>>> directionMap = new ArrayList<>(lines.size());
		Node startNode = null;

		for (int x = 0; x < map.length; x++) {
			directionMap.add(new ArrayList<>(lines.get(x).length()));
			for (int y = 0; y < map[0].length; y++) {
				directionMap.get(x).add(new ArrayList<>());
				map[x][y] = lines.get(x).charAt(y);
				graph[x][y] = new Node(new Position(x, y), map[x][y] == '#' ? Type.WALL : Type.EMPTY);
			}
		}

		printMap(map);

		mainLoop:
		for (int x = 0; x < map.length; x++) {
			for (int y = 0; y < map[0].length; y++) {
				if (map[x][y] == '^') {
					startNode = graph[x][y];
					startNode.getPathDirections().add(Direction.UP);
					startNode.currentDirection = Direction.UP;
					directionMap.get(x).get(y).add(Direction.UP);
					break mainLoop;
				}
			}
		}

		int nodeTests = 0;
		int emptyNodesCount = 0;
		for (int x = 0; x < graph.length; x++) {
			for (int y = 0; y < graph[0].length; y++) {
				if(graph[x][y].type == Type.EMPTY) {
					emptyNodesCount++;
				}
			}
		}

		List<Position> potentialObstacles = new ArrayList<>();

		for (int x = 0; x < graph.length; x++) {
			for (int y = 0; y < graph[0].length; y++) {

				startNode.getPathDirections().add(Direction.UP);
				startNode.currentDirection = Direction.UP;

				Node testedNode = graph[x][y];
				if (testedNode.type == Type.EMPTY) {nodeTests++;
					System.out.println("nodeTests: " + nodeTests + " / " + emptyNodesCount);
					testedNode.type = Type.WALL;
					if (testGraph(startNode, graph)) {
						potentialObstacles.add(new Position(x, y));
					}
					testedNode.type = Type.EMPTY;
					cleanMap(graph);
//					System.out.println("-----------------------\n");
				}
			}
		}

//		printMap(map);
//		printMap(directionMap);

		final HashSet<Position> uniqueObstacles = new HashSet<>(potentialObstacles);
		uniqueObstacles.forEach(obstacle -> map[obstacle.x][obstacle.y] = '■');
		printMap(map);

		//todo: remove obstacle on startNode

		return uniqueObstacles.size();
	}

	private boolean testGraph(Node currentNode, Node[][] graph) throws InterruptedException {

		List<Node> path = new ArrayList<>();
		path.add(currentNode);

		while (currentNode != null && isInsideGraph(currentNode, graph)) {
			Node newNode = advance(currentNode, graph);

			// TEST LOOP
			if (path.contains(newNode)) {
				if (newNode.pathDirections.contains(newNode.currentDirection)) {
					return true;
				}
			}

			currentNode = newNode;
			path.add(currentNode);
//			printMap(graph);
//			Thread.sleep(200);
		}
		return false;
	}

	private Node advance(Node currentNode, Node[][] graph) {
		Position nextStep = nextStep(currentNode.currentDirection, currentNode.position);
		if (!isInsideGraph(nextStep, graph)) {
			currentNode.getPathDirections().add(currentNode.currentDirection);
			return null;
		}
		Node nextNode = graph[nextStep.x][nextStep.y];

		if (nextNode.type == Type.WALL) {
			final Direction newDirection = directionOrder.get((currentNode.currentDirection.index + 1) % 4);
			currentNode.currentDirection = newDirection;
			return currentNode;
		}

		currentNode.getPathDirections().add(currentNode.currentDirection);
		nextNode.currentDirection = currentNode.currentDirection;
		return nextNode;
	}

	private static Position nextStep(Direction currentDirection, Position currentStep) {
		return new Position(currentDirection.xDirection.apply(currentStep.x),
							currentDirection.yDirection.apply(currentStep.y));
	}

	private static boolean isInsideGraph(Node node, Node[][] graph) {
		return isInsideGraph(node.position, graph);
	}

	private static boolean isInsideGraph(Position position, Node[][] graph) {
		return position.x > -1 &&
			   position.y > -1 &&
			   position.x < graph.length &&
			   position.y < graph[0].length;
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

	private static void printMap(Node[][] graph) {
		for (int x = 0; x < graph.length; x++) {
			for (int y = 0; y < graph[0].length; y++) {
				if (graph[x][y].type == Type.WALL) {
					System.out.print(Type.WALL.symbol);
				} else {
					final Set<Direction> pathDirections = graph[x][y].pathDirections;
					if (pathDirections.size() > 1) {
						System.out.print("+");
					} else if (pathDirections.size() == 1) {
						System.out.print(pathDirections.stream().findFirst().get().symbol);
					} else {
						System.out.print(Type.EMPTY.symbol);
					}
				}
			}
			System.out.println();
		}
		System.out.println();
	}

	private static void cleanMap(Node[][] graph) {
		for (int x = 0; x < graph.length; x++) {
			for (int y = 0; y < graph[0].length; y++) {
				graph[x][y].pathDirections.clear();
				graph[x][y].currentDirection = null;

			}
		}
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

	private class Node {

		private final Position position;
		private Type type;
		private Direction currentDirection;
		private Set<Direction> pathDirections = new HashSet<>();
		private List<Direction> testPathDirections = new ArrayList<>();

		public Node(Position position, Type type) {
			this.position = position;
			this.type = type;
		}

		public Set<Direction> getPathDirections() {
			return pathDirections;
		}

		public List<Direction> getTestPathDirections() {
			return testPathDirections;
		}
	}

	private enum Type {
		EMPTY('.'),
		WALL('#');

		private char symbol;

		Type(char symbol) {
			this.symbol = symbol;
		}
	}
}
