import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int X = Integer.parseInt(st.nextToken());
        int[] dp = new int[X + 1];
        dp[0] = 1;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int l = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            int[] next = new int[X + 1];

            for (int cur = 0; cur <= X; cur++) {
                if (dp[cur] == 0) continue;

                for (int cnt = 0; cnt <= c; cnt++) {
                    int nx = cur + l * cnt;
                    if (nx > X) break;
                    next[nx] += dp[cur];
                }
            }
            dp = next;
        }

        System.out.println(dp[X]);
    }
}