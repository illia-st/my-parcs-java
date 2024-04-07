import parcs.AM;
import parcs.AMInfo;

import java.util.List;

public class PalindromeCounter implements AM {
    private boolean isPalindrome(String str) {
        str = str.replaceAll("\\s", "").toLowerCase();

        StringBuilder reverseStr = new StringBuilder();

        if (str.isEmpty()) {
            return false;
        }

        for (int i = str.length() - 1; i >= 0; i--) {
            reverseStr.append(str.charAt(i));
        }
        return str.contentEquals(reverseStr);
    }
    public void run(AMInfo info) {
        Words g = (Words)info.parent.readObject();
        List<String> words = g.getWords();
        int palindromeCount = 0;
        for (String word : words) {
            if (!isPalindrome(word)) {
                continue;
            }
            ++palindromeCount;
        }
        System.out.println("Count the palindrome words.");
        System.out.println(palindromeCount);
        info.parent.write(palindromeCount);
    }
}
