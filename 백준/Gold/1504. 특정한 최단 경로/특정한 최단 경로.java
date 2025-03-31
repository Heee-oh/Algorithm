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
    static int INF = Integer.MAX_VALUE;
    static List<Node>[] graph;

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

        int v1ToV2Min = 0;
        int v2ToV1Min = 0;

        // 1로 시작
        int[] startDiff = dijkstra(1);// 1 -> v1, v2,N까지 
        int[] v1Diff = dijkstra(v1);// v1 -> N까지
        int[] v2Diff = dijkstra(v2);// v2 -> N까지

        // 1에서 v1 나 v2, n으로 가는 경로가 없다면 -1 출력
        if (impossibleCheck(startDiff, v1, v2, n)) {
            System.out.println(-1);
            return;
        }
        // 1 -> v1 -> v2 -> n
        v1ToV2Min = startDiff[v1] + v1Diff[v2] + v2Diff[n];
        // 1 -> v2 -> v1 -> n
        v2ToV1Min = startDiff[v2] + v2Diff[v1] + v1Diff[n];


        System.out.println(Math.min(v1ToV2Min, v2ToV1Min));
    }

    private static boolean impossibleCheck(int[] diff, int v1, int v2, int n) {
        return diff[v1] >= INF || diff[v2] >= INF || diff[n] >= INF;
    }


    // 다익스트라로 start 정점부터 N까지 최단경로를 구함
    private static int[] dijkstra(int start) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        int[] diff = new int[graph.length];
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

        return diff;
    }

}