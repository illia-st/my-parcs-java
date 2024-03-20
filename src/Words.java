import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Pattern;

public class Words implements Serializable {
    private static final long serialVersionUID = 1L;
    private final List<String> words;
    private static final Pattern WORD_PATTERN = Pattern.compile("\\b\\w+\\b");

    public Words() {
        this.words = new ArrayList<>();
    }

    public Words(List<String> words) {
        this.words = new ArrayList<>(words);
    }

    public void add(String word) {
        words.add(word);
    }

    public void addAll(List<String> words) {
        this.words.addAll(words);
    }

    public void remove(String word) {
        words.remove(word);
    }

    public void removeAll(List<String> words) {
        this.words.removeAll(words);
    }

    public int size() {
        return words.size();
    }

    public boolean contains(String word) {
        return words.contains(word);
    }

    public List<String> getWords() {
        return Collections.unmodifiableList(words);
    }

    public void sort() {
        Collections.sort(words);
    }

    public void addWordsFromBuffer(String buffer) {
        List<String> wordsFromBuffer = new ArrayList<>();
        java.util.regex.Matcher matcher = WORD_PATTERN.matcher(buffer);
        while (matcher.find()) {
            wordsFromBuffer.add(matcher.group());
        }
        words.addAll(wordsFromBuffer);
    }

    public List<Words> split(int numParts) {
        List<Words> parts = new ArrayList<>(numParts);
        int partSize = words.size() / numParts;
        int remainder = words.size() % numParts;

        int start = 0;
        for (int i = 0; i < numParts; i++) {
            int end = start + partSize + (i < remainder ? 1 : 0);
            parts.add(new Words(new ArrayList<>(words.subList(start, end))));
            start = end;
        }

        return parts;
    }
}