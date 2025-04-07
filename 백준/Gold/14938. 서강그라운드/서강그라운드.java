import java.io.*;
import java.util.*;

public class Main {

    static int INF = 100000000;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int r = Integer.parseInt(st.nextToken());

        int[][] graph = new int[n + 1][n + 1];


        st = new StringTokenizer(br.readLine());
        int[] items = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            items[i] = Integer.parseInt(st.nextToken());
            Arrays.fill(graph[i], INF);
            graph[i][i] = 0;
        }

        for (int i = 0; i < r; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int l = Integer.parseInt(st.nextToken());
            graph[a][b] = graph[b][a] = l;
        }


        // 플로이드 워샬로 모든 정점에 대한 최단경로를 구한다.
        for (int k = 1; k <= n; k++) {
            for (int a = 1; a <= n; a++) {
                for (int b = 1; b <= n; b++) {
                    graph[a][b] = Math.min(graph[a][b], graph[a][k] + graph[k][b]);
                }
            }
        }


        int max = 0;
        for (int i = 1; i <= n; i++) {
            int sum = 0;

            for (int j = 1; j <= n; j++) {
                if (graph[i][j] <= m) {
                    sum += items[j];
                }
            }

            max = Math.max(max, sum);
        }

        bw.write( max + "");
        bw.flush();
        bw.close();
    }
}