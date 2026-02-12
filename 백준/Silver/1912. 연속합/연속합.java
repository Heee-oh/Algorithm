import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] dp = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        dp[0] = Integer.parseInt(st.nextToken());

        // 이전 값 + 현재 값으로 연속합 vs 현재 값 둘중 가장 큰 값을 가짐
        for (int i = 1; i < N; i++) {
            int a = Integer.parseInt(st.nextToken());
            dp[i] = Math.max(a, dp[i - 1] + a); // 부분 연속 최대 연속합
        }

        System.out.println(Arrays.stream(dp).max().getAsInt());

    }
}
