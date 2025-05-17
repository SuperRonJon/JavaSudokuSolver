#!/bin/bash
mkdir tmp-sudoku-download
cd tmp-sudoku-download
curl -LO https://github.com/SuperRonJon/JavaSudokuSolver/releases/latest/download/sudoku.jar
mv sudoku.jar $HOME/bin/sudoku.jar
cd ..
rm -rf tmp-sudoku-download
echo "Updated jar!"