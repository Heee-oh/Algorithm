import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static long[][][][][][][] dp;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] color = new int[5];

        for (int i = 0; i < N; i++) {
            color[i] = Integer.parseInt(br.readLine());
        }

        // 1,2,3,4,5번 구슬의 개수, 이전, 이전전 색깔
        dp = new long[11][11][11][11][11][6][6];

        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < 11; j++) {

                for (int k = 0; k < 11; k++) {
                    for (int l = 0; l < 11; l++) {
                        for (int m = 0; m < 11; m++) {
                            for (int n = 0; n < 6; n++) {
                                Arrays.fill(dp[i][j][k][l][m][n], -1);
                            }
                        }
                    }
                }
            }
        }

        long result = dfs(color, 0, 0);

        System.out.println(result);

    }

    private static long dfs(int[] color, int p1, int p2) {
        int total = 0;
        for (int i = 0; i < 5; i++) total += color[i];

        if (total == 0) return 1L; // 모든 구슬을 섰으므로 한개의 목걸이 생성

        // 메모이제이션 확인
        if (dp[color[0]][color[1]][color[2]][color[3]][color[4]][p1][p2] != -1) {
            return dp[color[0]][color[1]][color[2]][color[3]][color[4]][p1][p2];
        }

        long currentCases = 0;

        // 1번 색부터 5번 색까지 시도
        for (int i = 0; i < 5; i++) {
            int colorNum = i + 1; // 실제 색상 번호는 1, 2, 3, 4, 5

            if (color[i] > 0) { // 남은 구슬이 있고
                if (colorNum != p1 && colorNum != p2) { // 규칙 위반이 아니면
                    color[i]--;
                    currentCases += dfs(color, colorNum, p1);
                    color[i]++;
                }
            }
        }

        return dp[color[0]][color[1]][color[2]][color[3]][color[4]][p1][p2] = currentCases;
    }
}
