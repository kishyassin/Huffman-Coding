import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class HuffmanTree {

    private HuffmanNode root;

    public HuffmanNode buildHuffmanTree(Map<Character, Integer> frequencyMap) {
        PriorityQueue<HuffmanNode> priorityQueue = new PriorityQueue<>();
        for (Map.Entry<Character, Integer> entry : frequencyMap.entrySet()) {
            priorityQueue.add(new HuffmanNode(entry.getValue(), entry.getKey()));
        }

        while (priorityQueue.size() > 1) {
            HuffmanNode left = priorityQueue.poll(); //Removes and returns the smallest element
            HuffmanNode right = priorityQueue.poll();  //Removes and returns the smallest element
            HuffmanNode parent = new HuffmanNode(left.getFrequency() + right.getFrequency(), left, right);
            priorityQueue.add(parent);
        }

        this.root = priorityQueue.poll();
        return this.root;
    }

    public Map<Character, String> generateCodes() {
        Map<Character, String> codes = new HashMap<>();
        if (root != null) {
            if (root.isLeaf()) {
                // Special case: only one unique character
                codes.put(root.character, "0");
            } else {
                generateCodesHelper(root, "", codes);
            }
        }
        return codes;
    }

    private void generateCodesHelper(HuffmanNode node, String code, Map<Character, String> codes) {
        if (node == null) {
            return;
        }

        if (node.isLeaf()) {
            codes.put(node.character, code);
            return;
        }

        generateCodesHelper(node.left, code + "0", codes);
        generateCodesHelper(node.right, code + "1", codes);
    }

    public String encode(String text, Map<Character, String> codes) {
        StringBuilder encoded = new StringBuilder();
        for (char c : text.toCharArray()) {
            encoded.append(codes.get(c));
        }
        return encoded.toString();
    }

    public String decode(String encodedText) {
        StringBuilder decoded = new StringBuilder();
        HuffmanNode current = root;

        for (char bit : encodedText.toCharArray()) {
            if (bit == '0') {
                current = current.left;
            } else {
                current = current.right;
            }

            if (current.isLeaf()) {
                decoded.append(current.character);
                current = root;
            }
        }

        return decoded.toString();
    }

    public HuffmanNode getRoot() {
        return root;
    }
}
