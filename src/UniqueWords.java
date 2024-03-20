import java.io.Serializable;

import java.util.HashSet;
import java.util.Set;

public class UniqueWords implements Serializable {
    private static final long serialVersionUID = 1L; // Recommended for Serializable classes
    private final Set<String> uniqueWords;

    public UniqueWords() {
        this.uniqueWords = new HashSet<>();
    }

    public UniqueWords(Set<String> words) {
        this.uniqueWords = new HashSet<>(words);
    }

    public void addAll(Set<String> words) {
        uniqueWords.addAll(words);
    }

    public int size() {
        return uniqueWords.size();
    }

    public UniqueWords union(UniqueWords other) {
        UniqueWords result = new UniqueWords(this.uniqueWords);
        result.addAll(other.uniqueWords);
        return result;
    }
}