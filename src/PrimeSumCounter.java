import parcs.AM;
import parcs.AMInfo;

import java.util.List;

public class PrimeSumCounter implements AM {

    public boolean isPrime(int number) {
        if (number <= 1) {
            return false;
        }
        for (int i = 2; i <= Math.sqrt(number); i++) {
            if (number % i == 0) {
                return false;
            }
        }
        return true;
    }
    public void run(AMInfo info) {
        Interval g = (Interval)info.parent.readObject();
        long sum = 0;
        for (int num = g.getStart(); num <= g.getEnd(); num++) {
            if (isPrime(num)) {
                sum += num;
            }
        }
        System.out.println("The sum of interval: [" + g.getStart() + ", " + g.getEnd() + "] = " + sum);
        info.parent.write(sum);
    }
}
