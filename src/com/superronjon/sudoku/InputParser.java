package com.superronjon.sudoku;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class InputParser {
    private final List<String> tokens;
    private String boardString;
    private boolean printBorders;
    private boolean printBeforeSolved;
    private boolean fileInput;
	private boolean countChecks;
	private int minChecks;

    public InputParser(String[] args) {
        tokens = new ArrayList<>();
        tokens.addAll(Arrays.asList(args));

        printBorders = false;
        printBeforeSolved = true;
        fileInput = false;
		countChecks = false;
		minChecks = -1;
        settingsFromTokens();
    }

    private void settingsFromTokens() {
        if(tokens.isEmpty()) {
            boardString = "";
            return;
        }
        if(tokens.size() == 1 && !tokens.get(0).startsWith("-")) {
            boardString = tokens.get(0);
            return;
        }
        if(tokens.size() == 1 && tokens.get(0).startsWith("-")) {
            boardString = "";
            return;
        }
		int minChecksTokenIndex = -1;
        for(int i = 0; i < tokens.size(); i++) {
            if(tokens.get(i).startsWith("-")) {
                String options = tokens.get(i).substring(1);
                for(int j = 0; j < options.length(); j++) {
                    switch(options.charAt(j)) {
                        case 'b':
                            printBorders = true;
                            break;
                        case 'p':
                            printBeforeSolved = false;
                            break;
                        case 'f':
                            fileInput = true;
                            break;
						case 'c':
							countChecks = true;
							break;
						case 'r':
							minChecksTokenIndex = i+1;
							break;
                    }
                }
            }
			else if(i == minChecksTokenIndex && i != tokens.size() - 1) {
				minChecks = Integer.parseInt(tokens.get(i));
			}
        }
        boardString = tokens.get(tokens.size() - 1);
    }

    public String getBoardString() {
        return boardString;
    }

    public boolean getFileInput() {
        return fileInput;
    }

    public boolean getPrintBorders() {
        return printBorders;
    }

    public boolean getPrintBeforeSolved() {
        return printBeforeSolved;
    }

	public boolean getCountChecks() {
		return countChecks;
	}

	public int getMinChecks() {
		return minChecks;
	}
}
