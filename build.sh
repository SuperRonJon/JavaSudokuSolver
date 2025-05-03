#!/bin/bash
rm -f bin/InputParser.jar bin/sudoku
rm -rf bin/com/superronjon/inputparse
mkdir -p bin/
javac -cp lib/InputParser.jar -d bin/ src/Main.java src/com/superronjon/sudoku/*.java
cd bin/
cp ../lib/InputParser.jar .
unzip ./InputParser.jar
jar -cfe sudoku.jar Main Main.class com/superronjon/sudoku/*.class com/superronjon/inputparse/*.class
cd ..
echo "Created bin/sudoku.jar"