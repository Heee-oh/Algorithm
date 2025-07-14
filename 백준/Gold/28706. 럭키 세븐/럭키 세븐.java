import java.io.*;
import java.util.*;

public class Main {

    static int[][] num;
    static boolean[][] dp;
    static char[][] op;
    static int n;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());

        while (T-- > 0) {
            n = Integer.parseInt(br.readLine());
            num = new int[n + 1][2];
            op = new char[n + 1][2];
            dp = new boolean[n + 1][7];

            dp[0][1] = true;
            for (int i = 1; i <= n; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                op[i][0] = st.nextToken().charAt(0);
                num[i][0] = Integer.parseInt(st.nextToken());

                op[i][1] = st.nextToken().charAt(0);
                num[i][1] = Integer.parseInt(st.nextToken());
            }

            for (int i = 1; i <= n; i++) {
                for (int j = 0; j < 7; j++) {
                    if (dp[i - 1][j]) {
                        for (int k = 0; k < 2; k++) {
                            if (op[i][k] == '+') {
                                int r = (j + num[i][k]) % 7;
                                dp[i][r] = true;

                            } else {
                                int r = (j * num[i][k]) % 7;
                                dp[i][r] = true;
                            }
                        }
                    }
                }
            }


            sb.append(dp[n][0] ? "LUCKY\n" : "UNLUCKY\n");

        }

        System.out.print(sb.toString());
    }
}
