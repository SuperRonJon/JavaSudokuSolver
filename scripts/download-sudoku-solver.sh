#!/bin/bash
echo "Creating temporary download directory..."
mkdir ./tmp-sudoku-download
cd tmp-sudoku-download/
echo "Downloading latest release..."
curl -LO https://github.com/SuperRonJon/JavaSudokuSolver/releases/latest/download/sudoku-solver.zip
echo "Extracting files..."
unzip ./sudoku-solver.zip
chmod +x ./sudoku-solver.sh
mkdir -p $HOME/bin
mv ./sudoku-solver.sh $HOME/bin/sudoku-solver
mv ./sudoku.jar $HOME/bin/sudoku.jar
cd ..
rm -rf tmp-sudoku-download/
echo "Installed script and jar to $HOME/bin"
echo 'add \"[[ ":$PATH:" == *:$HOME/bin:* ]] || PATH="$PATH:$HOME/bin"\" to your .bashrc or .zshrc file if your $HOME/bin folder is not already on your path.'
