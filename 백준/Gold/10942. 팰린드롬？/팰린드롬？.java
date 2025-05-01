import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(br.readLine());
        int[] seq = new int[n + 1];
        boolean[][] dp = new boolean[n + 1][n + 1]; // [s][e] 로 나눔

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            seq[i] = Integer.parseInt(st.nextToken());
        }

        // 길이가 1인 즉 1,1 2,2 3,3 구간의 수열은 항상 펠린드롬
        for (int i = 1; i <= n; i++) {
            dp[i][i] = true;
        }

        // 길이가 2인 즉,  1,2  2,3  3,4 구간의 수열은 두 수가 같으면 펠린드롬
        for (int i = 1; i < n; i++) {
            if (seq[i] == seq[i + 1]) {
                dp[i][i+1] = true;
            }
        }

        // 펠린드롬 길이
        for (int len = 2; len <= n; len++) {
            // 시작 위치, 전체 길이에서 펠린드롬 길이를 뺀 만큼만
            for (int start = 1; start <= n - len; start++) {

                // start + 1, end - 1 이 펠린드롬이고, start,end가 같다면 펠린드롬
                if (dp[start + 1][start + len - 1] && seq[start] == seq[start + len]) {
                    dp[start][start + len] = true;
                }
            }
        }

        int m = Integer.parseInt(br.readLine());
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());

            // 투 포인터로 s, e 지점에서 안쪽으로 모이는 형태로 비교한다.
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            
            if (dp[start][end]) {
                sb.append("1\n");
            } else {
                sb.append("0\n");
            }
        }


        System.out.print(sb.toString());
    }

}