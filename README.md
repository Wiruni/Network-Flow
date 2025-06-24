# Network-Flow
NetworkFlow is a Java program that computes the maximum flow in a directed network using the Ford-Fulkerson algorithm.

## Features

- **Network Representation:** Efficient data structures for representing flow networks with integer capacities.
- **File Input Parser:** Reads network specifications from formatted text files.
- **Max Flow Algorithm:** Implements the Ford-Fulkerson method (or alternative algorithm) to compute the maximum flow.
- **Step-by-step Output:** Provides incremental updates during computation to illustrate how flow is augmented.
- **Reports:** Outputs the maximum flow value and final flow assignment per edge.
- **Extensible Design:** Modular code for easy modification or extension.



## Input File Format

The input file should follow this structure:

- First line: single integer `n` representing the number of nodes (`0` to `n-1`).
- Following lines: triples `a b c` representing an edge from node `a` to node `b` with capacity `c`.

Example:

4
0 1 6
0 2 4
1 2 2
1 3 3
2 3 5


Here, node `0` is the source and node `3` is the sink.



## How to Run

### Compile the project

```bash
javac *.java



Run the program

java Main inputfile.txt

Where inputfile.txt is a text file formatted as described above.



Output
The program outputs the maximum flow value.

Details of augmenting paths and flow updates during computation.

Final flow per edge.




Design Choices
Data Structure: The flow network is represented using adjacency lists and capacity matrices, supporting efficient updates and queries.

Algorithm: The Ford-Fulkerson method with BFS-based path search (Edmonds-Karp) or DFS for augmenting paths.

Complexity: The implementation has a time complexity of O(E * F) for Ford-Fulkerson, where E is edges and F is maximum flow; Edmonds-Karp achieves O(V * E^2).





Notes
Input validation and error handling are implemented for robustness.

Sample input files are included for testing.

The project follows Java coding standards for readability and maintainability.
