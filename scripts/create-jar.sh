#!/bin/bash
SCRIPT_DIR=$(cd -- "$( dirname -- "${BASH_SOURCE[0]}" )" &> /dev/null && pwd )
cd $SCRIPT_DIR/..
rm -rf target/*
mkdir -p target/
javac --release 8 -cp lib/InputParser.jar -d target/ src/Main.java src/com/superronjon/sudoku/*.java
cd target/
unzip ../lib/InputParser.jar -d .
jar -cfe sudoku.jar Main Main.class com/superronjon/sudoku/*.class com/superronjon/inputparse/*.class
mkdir -p ../out
rm -f ../out/sudoku.jar
mv sudoku.jar ../out/sudoku.jar
echo "Created out/sudoku.jar"
