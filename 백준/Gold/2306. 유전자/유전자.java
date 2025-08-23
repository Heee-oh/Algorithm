import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[] dna = br.readLine().toCharArray();
        int n = dna.length;

        int[][] dp = new int[n][n];


        for (int dist = 2; dist <= n; dist++) {
            for (int l = 0; l + dist  - 1 < n; l++) {
                int r = l + dist - 1;

                // 중간지점 나누기
                for (int k = l; k < r; k++) {
                    dp[l][r] = Math.max(dp[l][r], dp[l][k] + dp[k + 1][r]);
                }


                if (isPair(dna[l], dna[r])) {
                    dp[l][r] = Math.max(dp[l][r], dp[l + 1][r - 1] + 2);
                }


            }
        }

        System.out.println(dp[0][n - 1]);


    }

    private static boolean isPair(char a, char b) {
        if (a == 'a' && b == 't' || a == 'g' && b == 'c') {
            return true;
        }

        return false;
    }

}
