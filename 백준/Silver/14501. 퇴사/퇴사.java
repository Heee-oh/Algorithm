import java.io.*;
import java.util.*;
import java.util.stream.IntStream;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());

        int[][] schedule = new int[n][2];

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            schedule[i][0] = Integer.parseInt(st.nextToken());
            schedule[i][1] = Integer.parseInt(st.nextToken());
        }

        int[] dp = new int[n];
        int max = 0;

        for (int i = 0; i < n; i++) {
            if (i + schedule[i][0] <= n) { // 상담기간이 n + 1일을 넘지 않는다면 그날은 상담 가능
                dp[i] = schedule[i][1];
            }

            for (int j = i - 1; j >= 0; j--) {
                if (i - j >= schedule[j][0]) { // i번째 일에 j번째 상담 상담을 한다 가졍했을때 j번째 상담의 걸리는 시간보다 크거나 같다면
                    if (i + schedule[i][0] <= n) { // i번째에 상담을 했을 때 n + 1을 넘지 않는다면
                        dp[i] = Math.max(dp[i], dp[j] + schedule[i][1]); // i번째 상담도 하면서 j번째 상담도 한다.
                    } else {
                        dp[i] = Math.max(dp[i], dp[j]); // j번째 상담과 i번째 상담했을때 최댓값
                    }
                    max = Math.max(dp[i], max);
                }
            }
        }
        // 0이라면 이전 상담의 최댓값이 없다는 것, 따라서 i번째 상담중 최댓값을 찾음
        System.out.println(max == 0 ? Arrays.stream(dp).max().getAsInt() : max);

    }
}
