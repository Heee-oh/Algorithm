import java.io.*;
import java.util.*;

public class Main {

    static int[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();

        TreeSet<Character> ts = new TreeSet<>();

        for (int i = 0; i < s.length(); i++) {
            ts.add(s.charAt(i));
        }

        List<Character> list = new ArrayList<>();
        while (!ts.isEmpty()) {
            list.add(ts.pollFirst());
        }

        // idx = 숫자, 값 = 실제 s에서의 idx
        int[] left = new int[list.size()];
        int[] right = new int[list.size()];
        dp = new int[list.size()][2];

        Arrays.fill(left, Integer.MAX_VALUE);

        for (int i = 0; i < s.length(); i++) {
            int alphaIdx = list.indexOf(s.charAt(i));

            left[alphaIdx] = Math.min(left[alphaIdx], i);
            right[alphaIdx] = Math.max(right[alphaIdx], i);
        }

        int dist = right[0] - left[0];
        dp[0][0] = left[0] + dist;
        dp[0][1] = right[0] + dist;


        for (int i = 1; i < list.size(); i++) {
            int prevL = left[i - 1];
            int prevR = right[i - 1];

            int curL = left[i];
            int curR = right[i];

            dist = curR - curL;

            dp[i][0] = Math.min(
                    dp[i - 1][0] + Math.abs(curL - prevR),
                    dp[i - 1][1] + Math.abs(curL - prevL)
            ) + dist;

            dp[i][1] = Math.min(
                    dp[i - 1][0] + Math.abs(curR - prevR),
                    dp[i - 1][1] + Math.abs(curR - prevL)
            ) + dist;
        }


        System.out.println(
                Math.min(
                        dp[dp.length - 1][0],
                        dp[dp.length - 1][1]
                ) + s.length());

    }
}
