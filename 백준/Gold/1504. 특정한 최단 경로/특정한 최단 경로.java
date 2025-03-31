import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static class Node implements Comparable<Node> {
        int next;
        int cost;

        public Node(int next, int cost) {
            this.next = next;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node o) {
            return this.cost - o.cost;
        }
    }
    static List<Node>[] graph;
    static int[] diff;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int e = Integer.parseInt(st.nextToken());

        if (e == 0) {
            System.out.println(-1);
            return;
        }

        graph = new ArrayList[n + 1];

        for (int i = 0; i < graph.length; i++) {
            graph[i] = new ArrayList<>();
        }

        // 그래프 생성
        for (int i = 0; i < e; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            graph[a].add(new Node(b, c));
            graph[b].add(new Node(a, c));
        }

        st = new StringTokenizer(br.readLine());
        int v1 = Integer.parseInt(st.nextToken());
        int v2 = Integer.parseInt(st.nextToken());



        int v1Tov2Min = 0;
        int v2Tov1Min = 0;
        dijkstra(1); // 1 -> v1, v2

        // 1에서 v1 나 v2, n으로 가는 경로가 없다면 -1 출력
        if (diff[v1] >= INF || diff[v2] >= INF || diff[n] >= INF) {
            System.out.println(-1);
            return;
        }


        // 1로 시작
        v1Tov2Min += diff[v1]; // 1 -> v1
        v2Tov1Min += diff[v2]; // 1 -> v2

        dijkstra(v1); // v1 - v2  두 정점의 길이를 구하는 것이므로
        v1Tov2Min += diff[v2];
        v2Tov1Min += diff[v2]; // 같은 값을 가짐

        dijkstra(v2); // v2 -> N
        v1Tov2Min += diff[n];

        dijkstra(v1); // v1 -> N
        v2Tov1Min += diff[n];

        System.out.println(Math.min(v1Tov2Min, v2Tov1Min));
    }

    static int INF = Integer.MAX_VALUE;
    private static void dijkstra(int start) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        diff = new int[graph.length];
        Arrays.fill(diff, INF);
        diff[start] = 0;
        pq.add(new Node(start, 0));

        while (!pq.isEmpty()) {
            Node current = pq.poll();

            List<Node> list = graph[current.next];
            for (Node node : list) {
                if (diff[node.next] > diff[current.next] + node.cost) {
                    diff[node.next] = diff[current.next] + node.cost;
                    pq.add(new Node(node.next, diff[node.next]));
                }
            }
        }
    }

}