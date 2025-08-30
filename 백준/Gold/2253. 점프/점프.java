import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static int[][] dp;
    static boolean[] smallRock;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        dp = new int[N + 1][150];
        smallRock = new boolean[N + 1];

        for (int i = 0; i < M; i++) {
            int idx = Integer.parseInt(br.readLine());
            smallRock[idx] = true;
        }

        if (smallRock[2]) {
            System.out.println(-1);
            return;
        }

        for (int i = 0; i <= N; i++) {
            Arrays.fill(dp[i], -1);
        }
        bfs();

        int answer = Integer.MAX_VALUE;

        for (int i = 0; i < dp[N].length; i++) {
            if (dp[N][i] == -1) continue;

            answer = Math.min(answer, dp[N][i]);
        }

        System.out.println(answer == Integer.MAX_VALUE ? -1 : answer);

    }

    private static void bfs() {
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[] {2, 1}); // 현재 돌 번호, 점프해온 거리 x
        dp[2][1] = 1;

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int curN = cur[0];
            int x = cur[1];

            if (curN == N) {
                return;
            }

            int op1 = curN + (x - 1);
            if (x - 1 > 0 && op1 <= N) {
                if (dp[op1][x-1] == -1 && !smallRock[op1]) {
                    dp[op1][x-1] = dp[curN][x] + 1;

                    q.add(new int[]{op1, x - 1});
                }
            }

            int op2 = curN + x;
            if (op2 <= N) {
                if (dp[op2][x] == -1 && !smallRock[op2]) {
                    dp[op2][x] = dp[curN][x] + 1;
                    q.add(new int[]{op2, x});
                }
            }

            int op3 = curN + x + 1;
            if (op3 <= N) {
                if (dp[op3][x+1] == -1 && !smallRock[op3]) {
                    dp[op3][x+1] = dp[curN][x] + 1;
                    q.add(new int[]{op3, x + 1});
                }
            }
        }
    }
}