import java.io.*;
import java.util.*;

public class Main {

    static class Node {
        int idx, cost;

        public Node(int idx, int cost) {
            this.idx = idx;
            this.cost = cost;
        }
    }
    static ArrayList<Node>[] graph;
    static int[] dist;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());

        graph = new ArrayList[n + 1];
        for (int i = 0; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int v1 = Integer.parseInt(st.nextToken());

            while (true) {
                int v2 = Integer.parseInt(st.nextToken());
                if (v2 == -1) break;

                int cost = Integer.parseInt(st.nextToken());
                graph[v1].add(new Node(v2, cost));
            }
        }


        dijkstra(n, 1);

        int maxIdx = 1;
        int max = 0;
        for (int i = 1; i <= n; i++) {
            if (max < dist[i]) {
                max = dist[i];
                maxIdx = i;

            }
        }

        dijkstra(n, maxIdx);
        System.out.println(Arrays.stream(dist).filter(x -> x != Integer.MAX_VALUE).max().getAsInt());
    }


    private static void dijkstra(int n, int start) {
        PriorityQueue<Node> pq = new PriorityQueue<>((o1, o2) -> o1.cost - o2.cost);
        pq.add(new Node(start, 0));

        boolean[] visited = new boolean[n + 1];
        dist = new int[n + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[start] = 0;

        while (!pq.isEmpty()) {
            Node current = pq.poll();

            // 먼저 나온것은항상 최단경로이므로 방문 처리
            if (visited[current.idx]) continue;
            visited[current.idx] = true;


            ArrayList<Node> list = graph[current.idx];

            for (Node next : list) {
                if (dist[next.idx] > dist[current.idx] + next.cost) {
                    dist[next.idx] = dist[current.idx] + next.cost;
                    pq.add(new Node(next.idx, dist[next.idx]));
                }
            }
        }

    }



}