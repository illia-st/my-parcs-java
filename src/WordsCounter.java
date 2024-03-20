import parcs.AM;
import parcs.AMInfo;
import java.util.HashSet;
import java.util.Set;
import java.util.List;

public class WordsCounter implements AM {
    public void run(AMInfo info) {
        Words g = (Words)info.parent.readObject();
        List<String> words = g.getWords();
        Set<String> uniqueWords = new HashSet<>(words);
        System.out.println("Count the unique words.");
        System.out.println(uniqueWords.size());
        info.parent.write(new UniqueWords(uniqueWords));
    }
}
