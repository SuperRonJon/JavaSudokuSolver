#! /usr/bin/env bash
SCRIPT_DIR=$(cd -- "$( dirname -- "${BASH_SOURCE[0]}" )" &> /dev/null && pwd )
cd $SCRIPT_DIR
VERSION=$1
OUTPUT_DIR="release/new-release"
OUTPUT_NAME="sudoku-solver"
mkdir -p ../out
cd $SCRIPT_DIR/../out
if [ ! -f "./sudoku.jar" ]; then
	echo "jar not found, creating new one..."
	../scripts/create-jar.sh
fi

if [ -z "${VERSION}" ]; then
  echo "No version specified, output to $OUTPUT_DIR"
else
  OUTPUT_DIR="release/sudoku-solver_v$VERSION"
  echo "output to $OUTPUT_DIR"
fi
mkdir -p $OUTPUT_DIR
mv sudoku.jar $OUTPUT_DIR/sudoku.jar
cp ../sudoku-solver.sh $OUTPUT_DIR/sudoku-solver.sh
cp ../sudoku-solver.bat $OUTPUT_DIR/sudoku-solver.bat
cd $OUTPUT_DIR
tar -czvf $OUTPUT_NAME.tar.gz ./*
zip sudoku-solver.zip sudoku.jar sudoku-solver.sh sudoku-solver.bat
