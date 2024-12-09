package sk.thenoen.aoc2024.day9;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import sk.thenoen.aoc.Utils;

public class SolutionPart1 {

	public static final int EMPTY_SPACE = -1;

	public BigDecimal solve(String inputPath) {
		final ArrayList<String> lines = Utils.loadLines(inputPath);

		final int[] diskMap = Arrays.stream(lines.get(0).split(""))
									.mapToInt(Integer::parseInt)
									.toArray();

		List<Block> disk = new ArrayList<>();
		boolean isFreeSpace = false;
		int fileId = 0;
		for (int i : diskMap) {
			if (isFreeSpace) {
				writeToDisk(disk, i, EMPTY_SPACE);
				isFreeSpace = false;
			} else {
				System.out.println("writing to disk: " + i + " * " + fileId);
				writeToDisk(disk, i, fileId);
				fileId++;
				isFreeSpace = true;
			}
		}
		printDisk(disk);

		///////// DEFRAGMENTATION /////////
		int indexFromEnd = disk.size();
		for (int i = 0; i < disk.size(); i++) {
			if (disk.get(i).fileId == EMPTY_SPACE && i < indexFromEnd) {

				for (int e = indexFromEnd - 1; e > i; e--) {
					if (disk.get(e).fileId != EMPTY_SPACE) {
						disk.set(i, disk.get(e));
						disk.set(e, new Block(EMPTY_SPACE));
						indexFromEnd = e;
						break;
					}
				}
				//				printDisk(disk);
			}
		}
		System.out.println("------------------------------------------");
		printDisk(disk);

		//////// CALCULATE CHECKSUM ////////

		BigDecimal checksum = BigDecimal.ZERO;
		for (int i = 0; i < disk.size(); i++) {
			if (disk.get(i).fileId == EMPTY_SPACE) {
				continue;
			}
			checksum = checksum.add(BigDecimal.valueOf(disk.get(i).fileId * i));
		}

		return checksum;
	}

	private void writeToDisk(List<Block> disk, long count, long fileId) {
		if (fileId == EMPTY_SPACE) {
			for (long i = 0; i < count; i++) {
				disk.add(new Block(EMPTY_SPACE));
			}
		} else {
			//			String stringValue = String.valueOf(value);
			for (long i = 0; i < count; i++) {
				//				disk.add(Integer.parseInt("" + stringValue.charAt(i % stringValue.length())));
				disk.add(new Block(fileId));
			}
		}
	}

	private void printDisk(List<Block> disk) {
		for (int i = 0; i < disk.size(); i++) {
			final Block block = disk.get(i);
			if (block.fileId == EMPTY_SPACE) {
				System.out.print(".");
			} else {
				//				System.out.print(block.blockValue);
				System.out.print("[" + block.fileId + "]");
			}
		}
		System.out.println();
	}

	private record Block(long fileId) {

	}

}
