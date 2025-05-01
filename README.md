# Design and Analysis of Algorithms Programs

This repository contains implementations of various algorithms covered in Design and Analysis of Algorithms course.

## Prerequisites

- Java Development Kit (JDK) 8 or higher
- Basic knowledge of command line interface

## Directory Structure

```
.
├── build/          # Compiled class files
├── labs/           # Core algorithm implementations
└── others/         # Additional implementations
```

## Available Algorithms

### Searching

- Binary Search (Iterative)
- Binary Search (Recursive)

### Sorting

- Quick Sort
- Merge Sort

### Graph Algorithms

- Kruskal's MST Algorithm
- Prim's MST Algorithm
- Floyd's All Pairs Shortest Path

### Dynamic Programming

- Knapsack Problem
- Travelling Salesman Problem

## How to Compile and Run

1. Create build directory (first time only):

```bash
mkdir build
```

2. Compile a program:

```bash
javac -d build labs/ProgramName.java
```

3. Run a program:

```bash
java -cp build ProgramName
```

### Example Commands

To run Quick Sort:

```bash
javac -d build labs/QuickSort.java
java -cp build QuickSort
```

To run Knapsack:

```bash
javac -d build labs/KnapsackDP.java
java -cp build KnapsackDP
```

## Notes

- All programs accept input through command line
- Make sure input arrays are sorted for binary search algorithms
- For graph algorithms, provide input as adjacency matrix
- Use 999 or INF for representing infinity in graph algorithms
