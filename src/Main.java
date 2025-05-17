import com.superronjon.inputparse.GenericInputParser;
import com.superronjon.inputparse.UnrecognizedOptionException;
import com.superronjon.sudoku.Board;
import com.superronjon.sudoku.CheckCounter;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        final String exampleBoard = "x56xxx27872xx361xx8xxxxx46x5xxx47xxx4x9xxx7x5xxx65xxx4x35xxxxx7xx718xx32918xxx54x";
		final String VERSION = "v1.6";
		GenericInputParser inputParser = new GenericInputParser("sudoku-solver", "sudoku-solver [OPTIONS...] boardString|filePath");
		inputParser.addOption('b', "borders", "Print board with border lines");
		inputParser.addOption('p', "print-solved", "Prints only the solved board, not the starting board");
		inputParser.addOption('f', "file", "Reads boards line by line from an input file path rather than a single string");
		inputParser.addOption('c', "count", "Prints the total number of individual box checks required to solve");
		inputParser.addOption('r', "required-checks", true, "-1", "If counting and from a file input, prints all boards in file above this number of checks");
        inputParser.addOption('e', "example", "Prints an example valid sudoku board string");
		inputParser.addOption('v', "version", "Prints version number");
        inputParser.addOption('h', "help", "Prints help menu.");

		try{
			inputParser.parseInput(args);
		}
		catch(UnrecognizedOptionException e) {
			System.out.println(e.getMessage());
			return;
		}

		if(Boolean.parseBoolean(inputParser.getOptionValue("version"))) {
			System.out.println("sudoku-solver " + VERSION);
			return;
		}

        if(Boolean.parseBoolean(inputParser.getOptionValue("help"))) {
            inputParser.printHelp();
            return;
        }

        if(Boolean.parseBoolean(inputParser.getOptionValue("example"))) {
            System.out.println("Example board string: " + exampleBoard);
            return;
        }

        String boardString = inputParser.getUnflaggedArgument(0);
		boolean fileInput = Boolean.parseBoolean(inputParser.getOptionValue('f'));

        if(boardString == null || boardString.isEmpty()) {
            System.out.println("No input detected... requires a board string or filepath. Use --help for more info.");
            return;
        }

        if(boardString.equalsIgnoreCase("example")) {
            System.out.println("Example board: " + exampleBoard);
            return;
        }
        if(!boardString.isEmpty()) {
            if(!fileInput) {
                solveBoard(boardString, inputParser);
                return;
            }
            try {
                solveBoardsFromFile(boardString, inputParser);
            } catch (FileNotFoundException e) {
                System.out.println("Couldn't find input file: " + boardString);
            }
            return;
        }

        System.out.println("No board string detected. Must be 81 character string with one of x,X,0,o,O,. as empty characers");
        System.out.println("Example: " + exampleBoard);
    }

    static CheckCounter solveBoard(String boardString, GenericInputParser parser) {
        boolean borders = Boolean.parseBoolean(parser.getOptionValue('b'));
        boolean printBeforeSolved = !Boolean.parseBoolean(parser.getOptionValue('p'));
		CheckCounter counter = new CheckCounter(Boolean.parseBoolean(parser.getOptionValue('c')));

        if(boardString.length() != 81) {
            System.out.println("Invalid board string, incorrect length, must be 81 characters");
            return counter;
        }

        Board board = new Board(boardString);
        if(printBeforeSolved) {
            board.print(borders);
        }

        if(board.solve(counter)) {
			if(counter.getShouldCount()) {
				System.out.println("Solved in " + counter + " checks!");
			}
			else {
				System.out.println("Solved!");
			}
            board.print(borders);
        }
        else {
			if(counter.getShouldCount()) {
				System.out.println("Unable to solve after " + counter + " checks...");
			}
			else {
				System.out.println("Unable to solve...");
			}
        }
		return counter;
    }

    static void solveBoardsFromFile(String fileName, GenericInputParser parser) throws FileNotFoundException {
        int count = 0;
		List<String> bigCounts = new ArrayList<>();
		final int MIN_COUNTS = Integer.parseInt(parser.getOptionValue('r'));
        try(BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line = br.readLine();
            while(line != null) {
                count++;
                System.out.println("Puzzle #" + count + " - " + line);
                CheckCounter counter = solveBoard(line, parser);
				if(counter.getCount() >= MIN_COUNTS && MIN_COUNTS != -1) {
					bigCounts.add(line);
				}
                line = br.readLine();
            }

			if(MIN_COUNTS != -1) {
				System.out.println("Puzzles with required checks over " + MIN_COUNTS);
				System.out.println("==========================================");
				for (String bigCount : bigCounts)
				{
					System.out.println(bigCount);
				}
			}
        } catch (IOException e) {
            System.out.println("Error reading input file: " + fileName);
        }
    }
}