package sk.thenoen.aoc2024.day1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.LongSummaryStatistics;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

import sk.thenoen.aoc.Utils;

public class Solution {

	public long solvePart1(String inputPath) {
		final ArrayList<String> lines = Utils.loadLines(inputPath);

		List<Integer> leftIds = new ArrayList<>();
		List<Integer> rightIds = new ArrayList<>();

		for (String line : lines) {

			final Scanner scanner = new Scanner(line);
			scanner.useDelimiter("   ");
			leftIds.add(scanner.nextInt());
			rightIds.add(scanner.nextInt());
		}

		leftIds.sort(Integer::compareTo);
		rightIds.sort(Integer::compareTo);

		long sum = 0;
		for (int i = 0; i < lines.size(); i++) {
			sum += Math.abs(leftIds.get(i) - rightIds.get(i));
		}
		return sum;
	}

	public long solvePart2(String inputPath) {
		final ArrayList<String> lines = Utils.loadLines(inputPath);

		List<Integer> leftIds = new ArrayList<>();
		List<Integer> rightIds = new ArrayList<>();

		for (String line : lines) {

			final Scanner scanner = new Scanner(line);
			scanner.useDelimiter("   ");
			leftIds.add(scanner.nextInt());
			rightIds.add(scanner.nextInt());
		}

		List<Similarity> similarities = new ArrayList<>();

		leftIds.forEach(leftId -> {
			final long count = rightIds.stream()
									   .filter(leftId::equals)
									   .count();
			similarities.add(new Similarity(leftId, count));
		});

		final Long sum = similarities.stream()
									 .map(similarity -> {
										 return similarity.id * similarity.count;
									 })
									 .collect(Collectors.summingLong(Long::longValue));

		return sum;
	}

	record Similarity(Integer id, Long count) {

	}
}
