import parcs.AMInfo;
import parcs.channel;
import parcs.point;
import parcs.task;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class TaskRunner {
    public static void main(String[] args) throws Exception {
        int num_of_threads = 10;

        StringBuilder contentBuilder = new StringBuilder();

        try (BufferedReader br = new BufferedReader(new FileReader("input"))) {
            String line;
            while ((line = br.readLine()) != null) {
                contentBuilder.append(line).append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        String buffer = contentBuilder.toString();
        System.out.println(buffer.length());
        Words words = new Words();
        words.addWordsFromBuffer(buffer);
        List<Words> split_words = words.split(num_of_threads);

        task curtask = new task();
        curtask.addJarFile("PalindromeCounter.jar");
        AMInfo info = new AMInfo(curtask, null);

        point[] P = new point[num_of_threads];
        channel[] C = new channel[num_of_threads];

        for (int i = 0; i < num_of_threads; ++i) {
            P[i] = info.createPoint();
            C[i] = P[i].createChannel();
            P[i].execute("PalindromeCounter");
            C[i].write(split_words.get(i));
        }

        int res = 0;

        for (int i = 0; i < num_of_threads; ++i) {
            res += C[i].readInt();
        }

        System.out.println("Result found.");
        System.out.println(res);

        curtask.end();
    }
}