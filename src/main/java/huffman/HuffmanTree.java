import huffman.HuffmanNode;
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
}