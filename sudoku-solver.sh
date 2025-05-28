#!/bin/bash
SCRIPT_DIR=$(cd -- "$( dirname -- "${BASH_SOURCE[0]}" )" &> /dev/null && pwd )

if ! command -v java &>/dev/null; then
	echo "Java not installed... Please ensure that Java Runtime 8+ is installed and accessible on PATH"
	exit 1
fi

java -jar $SCRIPT_DIR/sudoku.jar "$@"
