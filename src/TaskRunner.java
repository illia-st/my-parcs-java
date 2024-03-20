import parcs.*;

import java.util.List;
import java.util.Scanner;
import java.io.File;

public class TaskRunner {
    public static void main(String[] args) throws Exception {
        int num_of_threads = 10;

        Scanner sc = new Scanner(new File("input"));
        String buffer = sc.toString();

        Words words = new Words();
        words.addWordsFromBuffer(buffer);
        List<Words> split_words = words.split(num_of_threads);

        task curtask = new task();
        curtask.addJarFile("WordsCounter.jar");
        AMInfo info = new AMInfo(curtask, null);

        point[] P = new point[num_of_threads];
        channel[] C = new channel[num_of_threads];

        for (int i = 0; i < num_of_threads; ++i) {
            P[i] = info.createPoint();
            C[i] = P[i].createChannel();
            P[i].execute("WordsCounter");
            C[i].write(split_words.get(i));
        }

        UniqueWords res = new UniqueWords();

        for (int i = 0; i < num_of_threads; ++i) {
            UniqueWords uniqueWords = (UniqueWords) C[i].readObject();
            res.union(uniqueWords);
        }

        System.out.println("Result found.");
        System.out.println(res.size());

        curtask.end();
    }
}