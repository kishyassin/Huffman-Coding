package huffman;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class GraphvizGenerator {

    private int nodeCounter = 0;

    /**
     * Generate a Graphviz DOT file to visualize the Huffman tree
     *
     * @param root The root node of the Huffman tree
     * @param filename The output filename (without extension)
     * @return true if successful, false otherwise
     */
    public boolean generateDotFile(HuffmanNode root, String filename) {
        if (root == null) {
            System.err.println("Error: Huffman tree is empty.");
            return false;
        }

        nodeCounter = 0;
        StringBuilder dotContent = new StringBuilder();

        // DOT file header
        dotContent.append("digraph HuffmanTree {\n");
        dotContent.append("    node [shape=circle, style=filled, fontname=\"Arial\"];\n");
        dotContent.append("    edge [fontname=\"Arial\", fontsize=10];\n");
        dotContent.append("    rankdir=TB;\n\n");

        // Generate nodes and edges
        generateDotHelper(root, dotContent);

        // DOT file footer
        dotContent.append("}\n");

        // Write to file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename + ".dot"))) {
            writer.write(dotContent.toString());
            System.out.println("Graphviz DOT file generated: " + filename + ".dot");
            System.out.println("To visualize, run: dot -Tpng " + filename + ".dot -o " + filename + ".png");
            return true;
        } catch (IOException e) {
            System.err.println("Error writing DOT file: " + e.getMessage());
            return false;
        }
    }

    /**
     * Recursive helper to generate DOT content
     *
     * @param node Current node
     * @param dotContent StringBuilder to append DOT content
     * @return The ID of the current node
     */
    private String generateDotHelper(HuffmanNode node, StringBuilder dotContent) {
        if (node == null) {
            return null;
        }

        String currentNodeId = "node" + nodeCounter++;

        if (node.isLeaf()) {
            // Leaf node: display character and frequency
            String displayChar = getDisplayChar(node.character);
            dotContent.append("    ").append(currentNodeId)
                    .append(" [label=\"").append(displayChar)
                    .append("\\n(").append(node.frequency).append(")\", ")
                    .append("fillcolor=\"lightblue\"];\n");
        } else {
            // Internal node: display only frequency
            dotContent.append("    ").append(currentNodeId)
                    .append(" [label=\"").append(node.frequency)
                    .append("\", fillcolor=\"lightgray\"];\n");

            // Process left child
            if (node.left != null) {
                String leftNodeId = generateDotHelper(node.left, dotContent);
                dotContent.append("    ").append(currentNodeId)
                        .append(" -> ").append(leftNodeId)
                        .append(" [label=\"0\", color=\"blue\"];\n");
            }

            // Process right child
            if (node.right != null) {
                String rightNodeId = generateDotHelper(node.right, dotContent);
                dotContent.append("    ").append(currentNodeId)
                        .append(" -> ").append(rightNodeId)
                        .append(" [label=\"1\", color=\"red\"];\n");
            }
        }

        return currentNodeId;
    }

    /**
     * Get a displayable string for a character
     *
     * @param c The character
     * @return A displayable string
     */
    private String getDisplayChar(char c) {
        switch (c) {
            case ' ':
                return "SPACE";
            case '\n':
                return "\\\\n";
            case '\t':
                return "\\\\t";
            case '\r':
                return "\\\\r";
            case '"':
                return "\\\"";
            case '\\':
                return "\\\\";
            default:
                // Escape special characters for DOT format
                if (c < 32 || c > 126) {
                    return String.format("\\\\x%02X", (int) c);
                }
                return String.valueOf(c);
        }
    }
}
