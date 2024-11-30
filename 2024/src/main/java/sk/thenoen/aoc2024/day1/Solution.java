package sk.thenoen.aoc2024.day1;

import java.util.ArrayList;

import sk.thenoen.aoc.Utils;

public class Solution {


	public long solvePart1() {
		final ArrayList<String> lines = Utils.loadLines("day1/input.txt");
		return lines.getFirst().length();
	}

	public long solvePart2(String inputPathString) {
		return 0;
	}
}
