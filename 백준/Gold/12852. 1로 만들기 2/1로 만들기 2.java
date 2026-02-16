import java.io.*;
import java.util.*;
import java.util.stream.IntStream;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(br.readLine());

        int[] path = IntStream.range(0, N + 1).toArray();

        int[] dp = new int[N + 1];
        Arrays.fill(dp, (int) 1e6);
        dp[N] = 0;

        for (int i = N; i >= 1; i--) {
            if (i % 3 == 0 && dp[i / 3] > dp[i] + 1) {
                dp[i/3] = dp[i] + 1;
                path[i/3] = i;

            }

            if (i % 2 == 0 && dp[i / 2] > dp[i] + 1) {
                dp[i/2] = dp[i] + 1;
                path[i/2] = i;
            }

            if (dp[i - 1] > dp[i] + 1) {
                dp[i-1] = dp[i] + 1;
                path[i-1] = i;
            }
        }

        sb.append(dp[1]).append("\n");

        List<Integer> list = new ArrayList<>();
        int s = 1;
        while (s != N) {
            list.add(s);
            s = path[s];
        }
        list.add(N);

        for (int i = list.size() - 1; i >= 0; i--) {
            sb.append(list.get(i)).append(" ");
        }

        System.out.println(sb.toString().trim());
    }


}
