import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static int[] populations;
    static int[][] dp;
    static List<Integer>[] graph;
    static boolean[] visited;

    static int n;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        populations = new int[n + 1];
        graph = new ArrayList[n+1];
        visited = new boolean[n + 1];
        dp = new int[n + 1][2]; // 마을, 우수 선정 여부 (선정, 미선정)

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            populations[i] = Integer.parseInt(st.nextToken());
            graph[i] = new ArrayList<>();
        }


        for (int i = 1; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int village1 = Integer.parseInt(st.nextToken());
            int village2 = Integer.parseInt(st.nextToken());


            graph[village1].add(village2);
            graph[village2].add(village1);
        }

        for (int i = 1; i <= n; i++) {
            Arrays.fill(dp[i], -1);
        }


        System.out.println(Math.max(dfs(1,0, 1), dfs(1,0,0)));

    }

    private static int dfs(int curVillage, int preVillage, int isBest) {
        if (dp[curVillage][isBest] != -1) {
            return dp[curVillage][isBest];
        }

        int sum = 0;

        if (isBest == 1) {
            sum = populations[curVillage];
            for (int next : graph[curVillage]) {
                if (next == preVillage) continue;
                sum += dfs(next, curVillage, 0);
            }
        } else {
            for (int next : graph[curVillage]) {
                if (next == preVillage) continue;
                sum += Math.max(dfs(next, curVillage, 0), dfs(next, curVillage, 1));
            }
        }

        dp[curVillage][isBest] = sum;

        return dp[curVillage][isBest];
    }

}
