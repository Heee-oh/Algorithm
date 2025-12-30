import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


class Main {

    static final int MAX = 40000;
    // 구슬은 최대 7개, 무게는 4만 이하
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(br.readLine());

        int[] weight = new int[N];
        boolean[] dp = new boolean[MAX + 1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        dp[0] = true;

        for (int i = 0; i < N; i++) {
            weight[i] = Integer.parseInt(st.nextToken());

            for (int j = MAX; j >= weight[i]; j--) {
                dp[j] |= dp[j - weight[i]];
            }
        }

        int B = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        int[] beads = new int[B];
        for (int i = 0; i < B; i++) {
            boolean isEquals = false;
            beads[i] = Integer.parseInt(st.nextToken());

            for (int j = MAX; j >= beads[i]; j--) {
                if (dp[j] && dp[j - beads[i]]) {
                    sb.append("Y ");
                    isEquals = true;
                    break;
                }
            }

            if (!isEquals) {
                sb.append("N ");
            }
        }


        System.out.println(sb.toString());




    }
}