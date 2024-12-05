package sk.thenoen.aoc2024.day4;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;
import java.util.regex.MatchResult;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Gatherer;

import sk.thenoen.aoc.Utils;

public class SolutionPart2 {

	private final String WORD = "MAS";

	public long solvePart2(String inputPath) {
		final ArrayList<String> lines = Utils.loadLines(inputPath);

		char[][] puzzle = new char[lines.size() + 2 * WORD.length()][lines.getFirst().length() + 2 * WORD.length()];

		for (int x = 0; x < puzzle.length; x++) {
			for (int y = 0; y < puzzle[0].length; y++) {
				puzzle[x][y] = '*';
			}
		}

		for (int x = 0; x < lines.size(); x++) {
			for (int y = 0; y < lines.getFirst().length(); y++) {
				puzzle[x + WORD.length()][y + WORD.length()] = lines.get(x).charAt(y);
			}
		}

		for (int x = 0; x < puzzle.length; x++) {
			for (int y = 0; y < puzzle[0].length; y++) {
				System.out.print(puzzle[x][y] + " ");
			}
			System.out.println();
		}

		////////////////// START SEARCH //////////////////

		Map<Position, Short> foundPositions = new HashMap<>();

		for (int x = 0; x < puzzle.length; x++) {
			for (int y = 0; y < puzzle[0].length; y++) {
				if (puzzle[x][y] == WORD.charAt(0)) {
					final List<Position> words = findWords(puzzle, x, y);
					words.forEach(position -> {
						foundPositions.computeIfPresent(position, (key, oldValue) -> (short) (2));
						foundPositions.putIfAbsent(position, (short) 1);
					});
				}
			}
		}

		return foundPositions.entrySet()
							 .stream()
							 .filter(entry -> entry.getValue() == 2)
							 .peek(System.out::println)
							 .count();
	}

	private List<Position> findWords(char[][] puzzle, int x, int y) {
		final List<Position> foundWords = new ArrayList<>();

		StringBuilder topRight = new StringBuilder();
		StringBuilder bottomRight = new StringBuilder();
		StringBuilder bottomLeft = new StringBuilder();
		StringBuilder topLeft = new StringBuilder();

		for (int i = 0; i < WORD.length(); i++) {
			topRight.append(puzzle[x - i][y + i]);
			bottomRight.append(puzzle[x + i][y + i]);
			bottomLeft.append(puzzle[x + i][y - i]);
			topLeft.append(puzzle[x - i][y - i]);
		}

		if (topRight.toString().equals(WORD)) {
			foundWords.add(new Position(x - 1, y + 1));
		}

		if (bottomRight.toString().equals(WORD)) {
			foundWords.add(new Position(x + 1, y + 1));
		}

		if (bottomLeft.toString().equals(WORD)) {
			foundWords.add(new Position(x + 1, y - 1));
		}

		if (topLeft.toString().equals(WORD)) {
			foundWords.add(new Position(x - 1, y - 1));
		}

		return foundWords;
	}

	private record Position(int x, int y) {

	}

}
