import java.io.*;
import java.util.*;

public class Main {

    static class Edge{
        int v; // 출발
        int w; // 도착
        int cost; // 비용

        public Edge(int v, int w, int cost) {
            this.v = v;
            this.w = w;
            this.cost = cost;
        }
    }

    static ArrayList<Edge> graph;

    static final int INF = 1000000000;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        int TC = Integer.parseInt(br.readLine());

        while (TC-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            graph = new ArrayList<>();

            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            for (int i = 0; i < m; i++) {
                st = new StringTokenizer(br.readLine());
                int s = Integer.parseInt(st.nextToken());
                int e = Integer.parseInt(st.nextToken());
                int t = Integer.parseInt(st.nextToken());
                graph.add(new Edge(s, e, t));
                graph.add(new Edge(e, s, t));
            }

            for (int i = 0; i < w; i++) {
                st = new StringTokenizer(br.readLine());
                int s = Integer.parseInt(st.nextToken());
                int e = Integer.parseInt(st.nextToken());
                int t = Integer.parseInt(st.nextToken());
                graph.add(new Edge(s, e, -t));
            }

            sb.append(BellmanFord(n) ? "YES\n" : "NO\n");
        }



        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    private static boolean BellmanFord(int n) {
        int[] dist = new int[n + 1];
        Arrays.fill(dist, 0);

        // 정점의 개수가 N일때, 최단경로는 최대 N-1개의 간선을 지난다.
        for (int i = 0; i < n; i++) {
            for (Edge edge : graph) {
                if ( dist[edge.w] > dist[edge.v] + edge.cost) {
                    dist[edge.w] = dist[edge.v] + edge.cost;
                }
            }
        }

        // 음수싸이클 체크
        for (Edge edge : graph) {
            if (dist[edge.w] > dist[edge.v] + edge.cost) {
                return true; // N번째에도 값이 변화된다면 음수싸이클이 존재함을 증명함.
            }
        }

        return false;
    }

}