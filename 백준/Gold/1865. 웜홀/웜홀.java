import java.io.*;
import java.util.*;

public class Main {

    static final int INF = 1000000000;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        int TC = Integer.parseInt(br.readLine());

        while (TC-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            int[][] graph = new int[n + 1][n + 1];
            for (int i = 1; i <= n; i++) {
                Arrays.fill(graph[i], INF);
                graph[i][i] = 0;
            }


            // 도로 저장
            for (int i = 1; i <= m; i++) {
                st = new StringTokenizer(br.readLine());
                int s = Integer.parseInt(st.nextToken());
                int e = Integer.parseInt(st.nextToken());
                int t = Integer.parseInt(st.nextToken());  // s -> e, T비용

                // 도로의 이동 시간이 더 작다면 갱신
                if (graph[s][e] > t) {
                    graph[s][e] = graph[e][s] = t;
                }

            }


            // 웜홀 저장
            for (int i = 0; i < w; i++) {
                st = new StringTokenizer(br.readLine());
                int s = Integer.parseInt(st.nextToken());
                int e = Integer.parseInt(st.nextToken());
                int t = Integer.parseInt(st.nextToken());  // s -> e, T비용

                graph[s][e] = -t; // 웜홀은 시간 역행 즉, 음수임
            }

            //플로이드 워샬 알고리즘 
            for (int k = 1; k <= n; k++) {
                for (int a = 1; a <= n; a++) {
                    for (int b = 1; b <= n; b++) {
                        graph[a][b] = Math.min(graph[a][b], graph[a][k] + graph[k][b]);
                    }
                }
            }

            // i,i 즉, 시작정점의 최단거리가 - 이면 시간역행
            boolean flag = false;
            for (int i = 1; i <= n; i++) {
                if (graph[i][i] < 0) {
                    sb.append("YES\n");
                    flag = true;
                    break;
                }
            }

            if (!flag) {
                sb.append("NO\n");
            }

        }
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
}