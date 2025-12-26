import utils.TextProcessor;
import java.util.Map;

public class Main {

    public static void main(String[] args) {
        try {
            // Read input text
            String text = TextProcessor.readFile("input.txt");
            if (text == null || text.isEmpty()) {
                System.out.println("Error: Input file is empty or could not be read.");
                return;
            }

            System.out.println("=== HUFFMAN CODING DEMONSTRATION ===\n");
            System.out.println("Original Text: " + text);
            System.out.println("Original Text Length: " + text.length() + " characters");
            System.out.println();

            // Step 1: Calculate character frequencies
            Frequency frequencyCalculator = new Frequency();
            Map<Character, Integer> frequencyMap = frequencyCalculator.calculateFrequency(text);

            System.out.println("--- Character Frequencies ---");
            for (Map.Entry<Character, Integer> entry : frequencyMap.entrySet()) {
                char c = entry.getKey();
                String displayChar = (c == ' ') ? "SPACE" : (c == '\n') ? "NEWLINE" : String.valueOf(c);
                System.out.println(displayChar + ": " + entry.getValue());
            }
            System.out.println();

            // Step 2: Build Huffman Tree
            HuffmanTree huffmanTree = new HuffmanTree();
            huffmanTree.buildHuffmanTree(frequencyMap);
            System.out.println("--- Huffman Tree Built Successfully ---\n");

            // Step 3: Generate Huffman Codes
            Map<Character, String> codes = huffmanTree.generateCodes();
            System.out.println("--- Huffman Codes ---");
            for (Map.Entry<Character, String> entry : codes.entrySet()) {
                char c = entry.getKey();
                String displayChar = (c == ' ') ? "SPACE" : (c == '\n') ? "NEWLINE" : String.valueOf(c);
                System.out.println(displayChar + ": " + entry.getValue());
            }
            System.out.println();

            // Step 4: Encode the text
            String encodedText = huffmanTree.encode(text, codes);
            System.out.println("--- Encoded Text ---");
            System.out.println(encodedText);
            System.out.println("Encoded Length: " + encodedText.length() + " bits");
            System.out.println();

            // Step 5: Decode the text
            String decodedText = huffmanTree.decode(encodedText);
            System.out.println("--- Decoded Text ---");
            System.out.println(decodedText);
            System.out.println();

            // Step 6: Verify correctness
            boolean isCorrect = text.equals(decodedText);
            System.out.println("--- Verification ---");
            System.out.println("Decoding Successful: " + (isCorrect ? "YES ✓" : "NO ✗"));
            System.out.println();

            // Step 7: Calculate compression statistics
            int originalBits = text.length() * 8; // 8 bits per character (ASCII)
            int compressedBits = encodedText.length();
            double compressionRatio = (1 - (double) compressedBits / originalBits) * 100;

            System.out.println("=== COMPRESSION STATISTICS ===");
            System.out.println("Original Size: " + originalBits + " bits (" + text.length() + " characters × 8 bits)");
            System.out.println("Compressed Size: " + compressedBits + " bits");
            System.out.println("Space Saved: " + (originalBits - compressedBits) + " bits");
            System.out.println("Compression Ratio: " + String.format("%.2f%%", compressionRatio));

        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
