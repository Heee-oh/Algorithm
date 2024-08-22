import java.io.*;
import java.util.*;

public class Main {

    static boolean[] visited;
    static boolean[][] graph;
    static int max = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());

        graph = new boolean[n + 1][n + 1];
        visited = new boolean[n + 1];
        for (int i = 1; i <= n; i++) {
            String line = br.readLine();
            for (int j = 1; j <= n; j++) {
                graph[i][j] = line.charAt(j - 1) != 'N';
            }
        }


        for (int i = 1; i <= n; i++) {
            bfs(i, n);
            visited = new boolean[n + 1];
        }



        bw.write(max + "");
        bw.flush();
        bw.close();
    }

    private static void bfs(int start, int n) {
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[] {start, 0});
        visited[start] = true;
        int cost = 0;

        while (!q.isEmpty()) {
            int[] poll = q.poll();
            int idx = poll[0];

            if (poll[1] == 2) {
                max = Math.max(cost, max);
                return;
            }

            for (int j = 1; j <= n; j++) {
                if (graph[idx][j] && !visited[j]) {
                    q.add(new int[] {j, poll[1] + 1});
                    visited[j] = true;
                    cost++;
                }
            }

        }

        max = Math.max(cost, max);
    }

}