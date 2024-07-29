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
        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());

        StringTokenizer st;


        list = new ArrayList[N + 1];
        visited = new boolean[N + 1];
        dist = new int[N + 1];

        // 리스트 초기화
        for (int i = 1; i <= N; i++) {
            list[i] = new ArrayList<>();
        }

        // 전부 최댓값으로 초기화
        Arrays.fill(dist, INF);

        // 정점 초기화
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            list[start].add(new Node(end, cost));
        }

        // 구하고자 하는 구간
        st = new StringTokenizer(br.readLine());
        int startPoint = Integer.parseInt(st.nextToken());
        int endPoint = Integer.parseInt(st.nextToken());
        dist[startPoint] = 0;



        dijkstra(startPoint);

        bw.write(dist[endPoint] + "\n");

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