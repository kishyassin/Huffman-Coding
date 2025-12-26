import java.util.Map;

public class Frequency {

    public Map<Character, Integer> calculateFrequency(String text) {
        Map<Character, Integer> frequencyMap = new java.util.HashMap<>();
        for (char c : text.toCharArray()) {
            frequencyMap.put(c, frequencyMap.getOrDefault(c, 0) + 1);
        }
        return frequencyMap;
    }
}
