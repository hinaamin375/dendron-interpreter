# Dendron Language Interpreter

A programming language interpreter and compiler for the Dendron language, built with Java.

## Overview

Dendron is a simple stack-based programming language that supports:
- Variable assignment (`:=`)
- Arithmetic operations (`+`, `-`, `*`, `/`)
- Unary operations (negation `_`, square root `#`)
- Print statements (`@`)

## Project Structure

- `src/dendron/tree/` - Parse tree node implementations
- `src/dendron/machine/` - Virtual machine and instruction set
- `src/test/` - Test runner
- `assy/` - Assembly code examples
- `source/` - Source code examples

## Features

- **Parse Tree Construction**: Builds abstract syntax trees from token lists
- **Interpreter Mode**: Direct execution of parse trees
- **Compiler Mode**: Generates stack-based machine instructions
- **Virtual Machine**: Executes compiled bytecode

## Usage

Run the test suite:
```bash
java -cp build/classes test.DendronTest <test_directory>
```

Example Dendron programs:
```
:= x 5
:= y 3
@ + x y
```

## Building

This is a NetBeans project. To build:
```bash
ant compile
```

## Technologies

- Java 8
- NetBeans IDE
- Ant build system