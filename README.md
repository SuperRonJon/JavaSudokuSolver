# Sudoku-Solver
```
$ sudoku-solver --borders x56xxx27872xx361xx8xxxxx46x5xxx47xxx4x9xxx7x5xxx65xxx4x35xxxxx7xx718xx32918xxx54x
x 5 6 | x x x | 2 7 8
7 2 x | x 3 6 | 1 x x
8 x x | x x x | 4 6 x
------+-------+------
5 x x | x 4 7 | x x x
4 x 9 | x x x | 7 x 5
x x x | 6 5 x | x x 4
------+-------+------
x 3 5 | x x x | x x 7
x x 7 | 1 8 x | x 3 2
9 1 8 | x x x | 5 4 x
Solved!
3 5 6 | 4 9 1 | 2 7 8
7 2 4 | 8 3 6 | 1 5 9
8 9 1 | 5 7 2 | 4 6 3
------+-------+------
5 8 3 | 2 4 7 | 6 9 1
4 6 9 | 3 1 8 | 7 2 5
1 7 2 | 6 5 9 | 3 8 4
------+-------+------
2 3 5 | 9 6 4 | 8 1 7
6 4 7 | 1 8 5 | 9 3 2
9 1 8 | 7 2 3 | 5 4 6

```
## Options
`sudoku-solver [OPTIONS] board-string|filepath`
- --borders, -b = Prints the boards with border-lines
- --print-solved, -p = Prints only the solved board and not the before board like it does by default
- --file, -f = reads multiple boards from a file rather than a single board from the CLI, takes the path to the text file as input rather than the board string.
- --count, -c = count the number of individual box checks required to solve the puzzle
- --version, -v = print version number
- --required-checks VAL = when reading from a file and counting the checks, at the end of the output print all boards that require more than VAL number of checks to solve
# Installation
## Release Artifacts

New releases come with zip file that also includes .bat and .sh scripts to easier run jar from command line, instructions below if needed.

Generally, just unzip the jar and .bat/.sh file (windows/linux+mac respectively) to a folder on the PATH and run sudoku-solver via the appropriate script from the command line.

Requires Java JRE 8+

## Command Line Installation Instructions

- unzip contents (jar and .bat/.sh file, .bat is for windows) to your home bin folder, `~/bin`, create one if needed. (`C:\Users\<your-user-name>\bin`)
- If on linux/mac and using the .sh file, you can rename it and make it executable by running `mv ~/bin/sudoku-solver.sh ~/bin/sudoku-solver` and `chmod +x ~/bin/sudoku-solver`
- If on windows you do not need to rename the .bat file to run it with just `sudoku-solver`
- add that bin folder (`C:\Users\<your-user-name>\bin`) to your PATH. (On windows search->Edit environment variables for your account->User variables -> Path -> Edit... -> New -> `%USERPROFILE%\bin\`)
- Now restart terminal and command should run with `sudoku-solver [options] boardString` from anywhere in the terminal.

## New install script

- The install script can be run on git bash on windows or in the terminal on unix to download the latest release, unzip and install the new jar and start script to your `$HOME/bin` folder, which can be added to your path to execute the program from the command line, and can be re-ran to update to the newest release version.

```
sh -c "$(curl -fsSL https://raw.githubusercontent.com/SuperRonJon/JavaSudokuSolver/master/scripts/download-sudoku-solver.sh)"
```

## Update script

- Run this script if you have already installed sudoku-solver via the above script, and it will update your jar to the latest version automatically

```
sh -c "$(curl -fsSL https://raw.githubusercontent.com/SuperRonJon/JavaSudokuSolver/master/scripts/update-sudoku-solver.sh)"
```