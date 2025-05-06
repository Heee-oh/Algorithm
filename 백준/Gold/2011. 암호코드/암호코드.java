import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Arrays;

public class Main {
    static final int MOD = 1000000;
    // 자릿수, 수
    static int[][] dp;
    static String code;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        code = br.readLine();
        dp = new int[code.length()][27]; // 암호문 길이, 1~26 알파벳 인덱스


        if (code.startsWith("0") || code.matches(".*00.*") || code.matches(".*[3-9]0.*")) {
            System.out.println(0);
            return;
        }

        // -1로 초기화
        for (int i = 0; i < code.length(); i++) {
            Arrays.fill(dp[i], -1);
        }


        int answer = dfs(0, code.charAt(0) - '0') % MOD;


        if (code.length() >= 2 && code.charAt(0) != '0') {
            answer += dfs(1, (code.charAt(0) - '0') * 10 + code.charAt(1) - '0') % MOD;
        }

        System.out.print(answer % MOD);
    }

    private static int dfs(int idx, int alpha) {
        // 범위 벗어난 것은 0 반환
        if (idx > code.length() || alpha <= 0 || alpha > 26) {
            return 0;
        }

        if (idx == code.length() - 1) {
            return 1;
        }

        // 이미 탐색한 경로라면 경우의 수 반환
        if (dp[idx][alpha] != -1) {
            return dp[idx][alpha];
        }

        // 처음 방문이므로 0으로 초기화
        dp[idx][alpha] = 0;

        if (idx + 1 < code.length()) {
            dp[idx][alpha] += dfs(idx + 1, code.charAt(idx + 1) - '0');
        }
        if (idx + 2 < code.length() && code.charAt(idx + 1) != '0') {
            dp[idx][alpha] += dfs(idx + 2, (code.charAt(idx + 1) - '0') * 10 + code.charAt(idx + 2) - '0');
        }


        return dp[idx][alpha] % MOD;
    }

}