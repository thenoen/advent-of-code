package sk.thenoen.aoc;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public final class Utils {

	private Utils() {
		throw new AssertionError("Do not initialize");
	}

	public static ArrayList<String> loadLines(String inputPath) {
		final Scanner scanner = new Scanner(Utils.class.getClassLoader().getResourceAsStream(inputPath));
		final ArrayList<String> lines = new ArrayList<>();
		while (scanner.hasNextLine()) {
			lines.add(scanner.nextLine());
		}
		return lines;
	}

	public static void writeToFile(List<String> lines, String pathString) {
		try (final FileWriter fileWriter = new FileWriter(pathString)) {
			for (String line : lines) {
				fileWriter.write(line + "\n");
			}
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}
