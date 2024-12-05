package sk.thenoen.aoc2024.day4;

import java.util.ArrayList;
import java.util.List;

import sk.thenoen.aoc.Utils;

public class SolutionPart1 {

	private final String WORD = "XMAS";

	public long solvePart1(String inputPath) {
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
				System.out.print(puzzle[x][y]);
			}
			System.out.println();
		}


		////////////////// START SEARCH //////////////////

		List<String> words = new ArrayList<>();

		for (int x = 0; x < puzzle.length; x++) {
			for (int y = 0; y < puzzle[0].length; y++) {
				if(puzzle[x][y] == WORD.charAt(0)) {
					words.addAll(findWords(puzzle, x, y));
				}
			}
		}

		return words.stream()
				.filter(word -> word.equals(WORD))
				.count();

//		return -1;

	}

	private List<String> findWords(char[][] puzzle, int x, int y) {
		final ArrayList<String> foundWords = new ArrayList<>();

		StringBuilder top = new StringBuilder();
		StringBuilder topRight = new StringBuilder();
		StringBuilder right = new StringBuilder();
		StringBuilder bottomRight = new StringBuilder();
		StringBuilder bottom = new StringBuilder();
		StringBuilder bottomLeft = new StringBuilder();
		StringBuilder left = new StringBuilder();
		StringBuilder topLeft = new StringBuilder();

		// top
		for (int i = 0; i < WORD.length(); i++) {
			top.append(puzzle[x-i][y]);
			topRight.append(puzzle[x-i][y+i]);
			right.append(puzzle[x][y+i]);
			bottomRight.append(puzzle[x+i][y+i]);
			bottom.append(puzzle[x+i][y]);
			bottomLeft.append(puzzle[x+i][y-i]);
			left.append(puzzle[x][y-i]);
			topLeft.append(puzzle[x-i][y-i]);
		}

		foundWords.add(top.toString());
		foundWords.add(topRight.toString());
		foundWords.add(right.toString());
		foundWords.add(bottomRight.toString());
		foundWords.add(bottom.toString());
		foundWords.add(bottomLeft.toString());
		foundWords.add(left.toString());
		foundWords.add(topLeft.toString());

		return foundWords;
	}
}
