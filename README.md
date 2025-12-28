# Huffman Coding – Data Structures Project

This project implements the Huffman coding algorithm for text compression
using Java.

## Features

- Frequency analysis of characters
- Huffman tree construction using a priority queue
- Binary code generation
- Text encoding and decoding
- Compression rate calculation
- **Graphviz tree visualization** - Generates a visual representation of the Huffman tree

## How to Run

1. Place your text in `input.txt`
2. Compile:
   ```bash
   javac -d bin src/main/java/huffman/*.java src/main/java/utils/*.java
   ```
3. Run:
   ```bash
   java -cp bin huffman.Main
   ```
4. The program will:
   - Display character frequencies
   - Build the Huffman tree
   - Show Huffman codes for each character
   - Encode and decode the text
   - Display compression statistics
   - Generate `huffman_tree.dot` for visualization

## Tree Visualization

The program automatically generates a Graphviz DOT file (`huffman_tree.dot`) to visualize the Huffman tree structure.

### Visualizing the Tree

**Option 1: Using Graphviz (Recommended)**

Install Graphviz:

- **Windows**: `choco install graphviz` or download from [graphviz.org](https://graphviz.org/download/)
- **Linux**: `sudo apt-get install graphviz`
- **macOS**: `brew install graphviz`

Then generate the image:

```bash
dot -Tpng huffman_tree.dot -o huffman_tree.png
```

**Option 2: Online Viewer**

Open [Graphviz Online](https://dreampuf.github.io/GraphvizOnline/) and paste the contents of `huffman_tree.dot`.

### Tree Legend

- **Gray nodes**: Internal nodes (show combined frequency)
- **Blue nodes**: Leaf nodes (show character and frequency)
- **Blue edges (0)**: Left branch
- **Red edges (1)**: Right branch

## Project Structure

```
Huffman-Coding/
├── input.txt                    # Input text file
├── huffman_tree.dot            # Generated tree visualization
├── bin/                        # Compiled classes
└── src/main/java/
    ├── huffman/
    │   ├── Frequency.java      # Calculates character frequencies
    │   ├── HuffmanNode.java    # Tree node implementation
    │   ├── HuffmanTree.java    # Tree construction and encoding/decoding
    │   ├── GraphvizGenerator.java  # Tree visualization generator
    │   └── Main.java           # Main application
    └── utils/
        └── TextProcessor.java  # File I/O utilities
```

## Example Output

```
=== HUFFMAN CODING DEMONSTRATION ===

Original Text: aaa b c ac dadada cca acc
Original Text Length: 25 characters

--- Character Frequencies ---
SPACE: 6
a: 9
b: 1
c: 6
d: 3

--- Huffman Codes ---
SPACE: 10
a: 11
b: 000
c: 01
d: 001

=== COMPRESSION STATISTICS ===
Original Size: 200 bits (25 characters × 8 bits)
Compressed Size: 54 bits
Space Saved: 146 bits
Compression Ratio: 73.00%

=== TREE VISUALIZATION ===
Graphviz DOT file generated: huffman_tree.dot
```

## Constraints

- No external Huffman libraries used
- Uses Java PriorityQueue
- Supports special characters (space, newline, tab, etc.)
