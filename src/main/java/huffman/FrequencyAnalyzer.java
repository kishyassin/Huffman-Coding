import java.util.HashMap;
import java.util.Map;

public class FrequencyAnalyzer {

    public static Map<Character, Integer> analyze(String text) {
        Map<Character, Integer> frequencies = new HashMap<>();

        for (char c : text.toCharArray()) {
            frequencies.put(c, frequencies.getOrDefault(c, 0) + 1);
        }

        return frequencies;
    }
}
