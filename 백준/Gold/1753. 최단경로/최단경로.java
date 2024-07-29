import java.io.*;
import java.util.*;

public class Main {

    static class Node implements Comparable<Node> {

        int end, cost;

        public Node(int end, int cost) {
            this.end = end;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node n) {
            return this.cost - n.cost;
        }
    }

    static final int INF = Integer.MAX_VALUE;
    static ArrayList<Node>[] list;
    static boolean[] visited;
    static int[] dist;
    static PriorityQueue<Node> pq = new PriorityQueue<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int V = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(br.readLine());

        list = new ArrayList[V + 1];
        visited = new boolean[V + 1];
        dist = new int[V + 1];

        // 리스트 초기화
        for (int i = 1; i <= V; i++) {
            list[i] = new ArrayList<>();
        }

        // 전부 최댓값으로 초기화
        Arrays.fill(dist, INF);

        // 시작지점은 거리값이 0
        dist[k] = 0;

        // 정점 초기화
        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int index = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            list[index].add(new Node(end, cost));
        }

        dijkstra(k);

        for (int i = 1; i <= V; i++) {
            if (dist[i] == INF) {
                bw.write("INF\n");
            } else {
                bw.write(dist[i] + "\n");
            }
        }

        bw.flush();
        bw.close();
    }

    private static void dijkstra(int start) {
        pq.add(new Node(start, 0));

        while (!pq.isEmpty()) {
            Node poll = pq.poll();
            int cur = poll.end;
            if (visited[cur]) continue;

            visited[cur] = true;
            for (Node node : list[cur]) {
                if (dist[node.end] > dist[cur] + node.cost) {
                    dist[node.end] = dist[cur] + node.cost;
                    pq.add(new Node(node.end , dist[node.end]));
                }
            }

        }
    }



}