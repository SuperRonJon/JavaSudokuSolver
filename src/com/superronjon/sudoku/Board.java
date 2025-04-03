package com.superronjon.sudoku;

public class Board {
    private static final int BOARD_SIZE = 9;
    Box[][] grid;

    public Board(String boardString) {
        int index = 0;
        grid = new Box[BOARD_SIZE][BOARD_SIZE];

        for(int i = 0; i < BOARD_SIZE; i++) {
            for(int j = 0; j < BOARD_SIZE; j++) {
                if(!isEmptyCharacter(boardString.charAt(index))) {
                    grid[i][j] = new Box(Integer.parseInt(String.valueOf(boardString.charAt(index))));
                }
                else {
                    grid[i][j] = new Box();
                }
                index++;
            }
        }
    }

    public void print(boolean borders) {
        for(int i = 0; i < BOARD_SIZE; i++) {
            if(borders && i != 0 && i % 3 == 0) {
                printLine();
            }
            for(int j = 0; j < BOARD_SIZE; j++) {
                if(borders && j != 0 && j % 3 == 0) {
                    System.out.print("| ");
                }
                if(!grid[i][j].isEmpty()) {
                    System.out.print(Integer.toString(grid[i][j].getValue()) + " ");
                }
                else {
                    System.out.print("x ");
                }
            }
            System.out.println();
        }
    }

    public boolean solve(CheckCounter counter) {
        return solve(0, 0, counter);
    }

    public int valueAt(int row, int col) {
        return grid[row][col].getValue();
    }

    private boolean solve(int row, int col, CheckCounter counter) {
        if(row == BOARD_SIZE - 1 && col == BOARD_SIZE) {
            return true;
        }
        if(col == BOARD_SIZE) {
            row++;
            col = 0;
        }

        if(!grid[row][col].isEmpty()) {
            return solve(row, col+1, counter);
        }

        for(int i = 1; i <= BOARD_SIZE; i++) {
            counter.addOne();
            if(isPossibility(i, row, col)) {
                grid[row][col].setValue(i);
                if(solve(row, col+1, counter)) {
                    return true;
                }
                grid[row][col].clear();
            }
        }
        return false;
    }

    private boolean isPossibility(int number, int row, int col) {
        return (!rowContains(number, row) && !columnContains(number, col) && !squareContains(number, row, col));
    }

    private boolean rowContains(int number, int row) {
        for(int i = 0; i < BOARD_SIZE; i++) {
            if(grid[row][i].getValue() == number) {
                return true;
            }
        }
        return false;
    }

    private boolean columnContains(int number, int col) {
        for(int i = 0; i < BOARD_SIZE; i++) {
            if(grid[i][col].getValue() == number) {
                return true;
            }
        }
        return false;
    }

    private boolean squareContains(int number, int row, int col) {
        final int SUB_SIZE = BOARD_SIZE / 3;
        int startRow = row - (row % SUB_SIZE);
        int startCol = col - (col % SUB_SIZE);

        for(int i = 0; i < SUB_SIZE; i++) {
            for(int j = 0; j < SUB_SIZE; j++) {
                if(grid[i + startRow][j + startCol].getValue() == number) {
                    return true;
                }
            }
        }
        return false;
    }

    private void printLine() {
        System.out.println("------+-------+------");
    }

    private boolean isEmptyCharacter(char c) {
        return (c == 'x' || c == 'X' || c == '0' || c == 'o' || c == 'O' || c == '.');
    }
}
