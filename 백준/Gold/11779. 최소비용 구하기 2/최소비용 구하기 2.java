import java.io.*;
import java.util.*;

public class Main {

    static class Node {
        int idx, cost;
        String path;

        public Node(int idx, int cost, String path) {
            this.idx = idx;
            this.cost = cost;
            this.path = path;
        }
    }
    static List<Node>[] graph;
    static int[] dist;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());

        visited = new boolean[n + 1];
        graph = new List[n + 1];
        dist = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
            dist[i] = Integer.MAX_VALUE;
        }

        // 그래프 초기화
        for (int i = 0; i < m; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int v1 = Integer.parseInt(st.nextToken());
            int v2 = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            graph[v1].add(new Node(v2, c, String.valueOf(v1)));
        }

        StringTokenizer st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken());
        int end = Integer.parseInt(st.nextToken());

        bw.write(dijkstra(start, end));
        bw.flush();
        bw.close();
    }



    private static String dijkstra(int start, int end) {
        // [idx, cost, List<>]
        PriorityQueue<Node> pq = new PriorityQueue<>((o1, o2) -> o1.cost - o2.cost);
        pq.add(new Node(start, 0, String.valueOf(start)));
        dist[start] = 0;

        while (!pq.isEmpty()) {
            Node current = pq.poll();
            if (visited[current.idx]) continue;
            visited[current.idx] = true;

            if (current.idx == end) {
                return getAnswer(end, current);
            }

            List<Node> list = graph[current.idx];

            for (Node node : list) {
                if (dist[node.idx] > dist[current.idx] + node.cost) {
                    dist[node.idx] = dist[current.idx] + node.cost;

                    // 다음 노드의 값과 패스 추가
                    pq.add(new Node(node.idx, dist[node.idx], current.path + " " + node.idx));
                }
            }
        }


        return "";
    }

    private static String getAnswer(int end,Node current) {
        StringBuilder sb = new StringBuilder();
        sb.append(dist[end]).append("\n");
        String[] split = current.path.split(" ");
        sb.append(split.length).append("\n");
        Arrays.stream(split).forEach(x -> sb.append(x).append(" "));

        return sb.toString();
    }

}