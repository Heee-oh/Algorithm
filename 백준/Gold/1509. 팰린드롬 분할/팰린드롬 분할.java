import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class Main {

    static int len;
    static boolean[][] isPalindrome;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[] ch = br.readLine().toCharArray();
        len = ch.length;

        isPalindrome = new boolean[len][len];
        isPalindrome[0][0] = true;

        // dp 초기값
        for (int i = 1; i < len; i++) {
            isPalindrome[i][i] = true;

            if (ch[i - 1] == ch[i]) {
                isPalindrome[i-1][i] = true;
            }
        }


        // 탐색 범위
        for (int i = 2; i < len; i++) {
            // 시작 위치
            for (int left = 0; left < len - i; left++) {
                int right = left + i;

                // 안쪽이 팰린드롬이며, 양끝이 팰린드롬이라면
                if (isPalindrome[left + 1][right - 1] && ch[left] == ch[right]) {
                    isPalindrome[left][right] = true;
                }
            }
        }

        int[] dp = new int[len + 1];

        for (int i = 1; i <= len; i++) {
            dp[i] = Integer.MAX_VALUE;

            for (int j = 1; j <= i; j++) {

                if (isPalindrome[j - 1][i - 1]) {
                    dp[i] = Math.min(dp[i], dp[j - 1] + 1);
                }
            }

        }

        System.out.println(dp[len]);
    }
}
