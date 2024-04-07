import parcs.AMInfo;
import parcs.channel;
import parcs.point;
import parcs.task;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TaskRunner {
    public static void main(String[] args) throws Exception {
        int N = 100000;

        int num_of_threads = 10;

        task curtask = new task();
        curtask.addJarFile("PrimeSumCounter.jar");
        AMInfo info = new AMInfo(curtask, null);

        point[] P = new point[num_of_threads];
        channel[] C = new channel[num_of_threads];

        for (int i = 0; i < num_of_threads; ++i) {
            final int startRange = i * (N / num_of_threads) + 1;
            final int endRange = (i == num_of_threads - 1) ? N : (i + 1) * (N / num_of_threads);

            P[i] = info.createPoint();
            C[i] = P[i].createChannel();
            P[i].execute("PrimeSumCounter");
            C[i].write(new Interval(startRange, endRange));
        }

        long res = 0;

        for (int i = 0; i < num_of_threads; ++i) {
            res += C[i].readLong();
        }

        System.out.println("Result found.");
        System.out.println(res);

        curtask.end();
    }
}