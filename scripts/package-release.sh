#!/bin/bash
SCRIPT_DIR=$(cd -- "$( dirname -- "${BASH_SOURCE[0]}" )" &> /dev/null && pwd )
VERSION=$1
OUTPUT_DIR="release/new-release"
OUTPUT_NAME="new-release"
if [ -z "${VERSION}" ]; then
  echo "No version specified, output to $OUTPUT_DIR"
else
  OUTPUT_DIR="release/sudoku-solver_v$VERSION"
  OUTPUT_NAME="sudoku-solver_v$VERSION"
  echo "output to $OUTPUT_DIR"
fi
cd $SCRIPT_DIR/../out
mkdir -p $OUTPUT_DIR
cp sudoku.jar $OUTPUT_DIR/sudoku.jar
cp ../sudoku-solver.sh $OUTPUT_DIR/sudoku-solver.sh
cp ../sudoku-solver.bat $OUTPUT_DIR/sudoku-solver.bat
cd $OUTPUT_DIR
tar -czvf $OUTPUT_NAME.tar.gz ./*
cp $OUTPUT_NAME.tar.gz ../../