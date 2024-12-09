package sk.thenoen.aoc2024.day9;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import sk.thenoen.aoc.Utils;

public class SolutionPart2 {

	public static final int EMPTY_SPACE = -1;

	public BigDecimal solve(String inputPath) {
		final ArrayList<String> lines = Utils.loadLines(inputPath);

		final int[] diskMap = Arrays.stream(lines.get(0).split(""))
									.mapToInt(Integer::parseInt)
									.toArray();

		List<File> diskFiles = new ArrayList<>();
		boolean isFreeSpace = false;
		int fileId = 0;
		for (int i : diskMap) {
			if (isFreeSpace) {
				diskFiles.add(new File(EMPTY_SPACE, i));
				isFreeSpace = false;
			} else {
				diskFiles.add(new File(fileId, i));
				fileId++;
				isFreeSpace = true;
			}
		}
		//		printDiskBlocks(diskFiles);

		///////// DEFRAGMENTATION /////////

		final List<File> filesToMove = diskFiles.stream()
												.filter(file -> file.fileId != EMPTY_SPACE)
												.toList();
		//		while (!filesToMove.isEmpty()) {

		//		printDiskBlocks(diskFiles);
		for (int e = diskFiles.size() - 1; e >= 0; e--) {
			File fileToMove = diskFiles.get(e);
			if (fileToMove.fileId == EMPTY_SPACE) {
				continue;
			}
			//			System.out.println();
			//			System.out.println("file to move: " + fileToMove);
			for (int i = 0; i < e; i++) {
				final File emptyFile = diskFiles.get(i);
				if (emptyFile.fileId == EMPTY_SPACE && emptyFile.size >= fileToMove.size) {
					diskFiles.set(i, fileToMove);
					//						diskFiles.remove(e);
					diskFiles.set(e, new File(EMPTY_SPACE, fileToMove.size));
					if (emptyFile.size > fileToMove.size) {
						diskFiles.add(i + 1, new File(EMPTY_SPACE, emptyFile.size - fileToMove.size));
					}
//					printDiskBlocks(diskFiles);
					break;
				}
			}
		}
		//		}
		//		printDiskBlocks(diskFiles);

		//////// CALCULATE CHECKSUM ////////

		final List<Block> diskBlocks = getDiskBlocks(diskFiles);

		BigDecimal checksum = BigDecimal.ZERO;
		for (int i = 0; i < diskBlocks.size(); i++) {
			final Block block = diskBlocks.get(i);
			if (block.fileId == EMPTY_SPACE) {
				continue;
			}
			checksum = checksum.add(BigDecimal.valueOf(block.fileId * i));
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

	private void printDisk(List<File> disk) {
		for (int i = 0; i < disk.size(); i++) {
			final File file = disk.get(i);
			if (file.fileId == EMPTY_SPACE) {
				System.out.print("[" + file.size + ", .]");
			} else {

				System.out.print("[" + file.size + ", " + file.fileId + "]");
			}
		}
		System.out.println();
	}

	private void printDiskBlocks(List<File> disk) {
		for (int i = 0; i < disk.size(); i++) {
			final File file = disk.get(i);
			if (file.fileId == EMPTY_SPACE) {
				for (long j = 0; j < file.size; j++) {
					//					System.out.print("[" + "." + "]");
					System.out.print(".");

				}
			} else {
				for (long j = 0; j < file.size; j++) {
					System.out.print(file.fileId);
				}
			}
		}
		System.out.println();
	}

	private List<Block> getDiskBlocks(List<File> diskFiles) {
		List<Block> diskBlocks = new ArrayList<>();
		for (int i = 0; i < diskFiles.size(); i++) {
			final File file = diskFiles.get(i);
			if (file.fileId == EMPTY_SPACE) {
				for (long j = 0; j < file.size; j++) {
					diskBlocks.add(new Block(EMPTY_SPACE));

				}
			} else {
				for (long j = 0; j < file.size; j++) {
					diskBlocks.add(new Block(file.fileId));
				}
			}
		}
		return diskBlocks;
	}

	private record Block(long fileId) {

	}

	private record File(long fileId, long size) {

	}

}
