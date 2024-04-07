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

    public List<String> getWords() {
        return Collections.unmodifiableList(words);
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