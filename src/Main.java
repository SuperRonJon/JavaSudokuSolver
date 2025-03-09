import com.superronjon.sudoku.Board;
import com.superronjon.sudoku.CheckCounter;
import com.superronjon.sudoku.InputParser;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        final String exampleBoard = "x56xxx27872xx361xx8xxxxx46x5xxx47xxx4x9xxx7x5xxx65xxx4x35xxxxx7xx718xx32918xxx54x";
        InputParser parser = new InputParser(args);
        String boardString = parser.getBoardString();

        if(boardString.equalsIgnoreCase("example")) {
            System.out.println("Example board: " + exampleBoard);
            return;
        }
        if(!boardString.isEmpty()) {
            if(!parser.getFileInput()) {
                solveBoard(boardString, parser);
                return;
            }
            try {
                solveBoardsFromFile(boardString, parser);
            } catch (FileNotFoundException e) {
                System.out.println("Couldn't find input file: " + boardString);
            }
            return;
        }

        System.out.println("No board string detected. Must be 81 character string with one of x,X,0,o,O,. as empty characers");
        System.out.println("Example: " + exampleBoard);
    }

    static CheckCounter solveBoard(String boardString, InputParser parser) {
        boolean borders = parser.getPrintBorders();
        boolean printBeforeSolved = parser.getPrintBeforeSolved();
		CheckCounter counter = new CheckCounter();

        if(boardString.length() != 81) {
            System.out.println("Invalid board string, incorrect length, must be 81 characters");
            return counter;
        }

        Board board = new Board(boardString);
        if(printBeforeSolved) {
            board.print(borders);
        }

		counter.setShouldCount(parser.getCountChecks());
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

    static void solveBoardsFromFile(String fileName, InputParser parser) throws FileNotFoundException {
        int count = 0;
		List<String> bigCounts = new ArrayList<>();
		final int MIN_COUNTS = parser.getMinChecks();
        try(BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line = br.readLine();
            while(line != null) {
                count++;
                System.out.println("Puzzle #" + count + " - " + line);
                CheckCounter counter = solveBoard(line, parser);
				if(counter.getCount().compareTo(new BigInteger(Integer.toString(MIN_COUNTS))) >= 0 && MIN_COUNTS != -1) {
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