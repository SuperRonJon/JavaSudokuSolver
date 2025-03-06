import com.superronjon.sudoku.Board;
import com.superronjon.sudoku.InputParser;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

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

    static void solveBoard(String boardString, InputParser parser) {
        boolean borders = parser.getPrintBorders();
        boolean printBeforeSolved = parser.getPrintBeforeSolved();

        if(boardString.length() != 81) {
            System.out.println("Invalid board string, incorrect length, must be 81 characters");
            return;
        }

        Board board = new Board(boardString);
        if(printBeforeSolved) {
            board.print(borders);
        }

        if(board.solve()) {
            System.out.println("Solved!");
            board.print(borders);
        }
        else {
            System.out.println("Unable to solve...");
        }
    }

    static void solveBoardsFromFile(String fileName, InputParser parser) throws FileNotFoundException {
        int count = 0;
        try(BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line = br.readLine();
            while(line != null) {
                count++;
                System.out.println("Puzzle #" + count + " - " + line);
                solveBoard(line, parser);
                line = br.readLine();
            }
        } catch (IOException e) {
            System.out.println("Error reading input file: " + fileName);
        }
    }
}