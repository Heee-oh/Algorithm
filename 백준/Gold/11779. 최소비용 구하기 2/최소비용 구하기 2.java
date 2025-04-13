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
    static List<Node>[] graph;
    static int[] dist;
    static boolean[] visited;
    static int[] prev;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());

        visited = new boolean[n + 1];
        graph = new List[n + 1];
        dist = new int[n + 1];
        prev = new int[n + 1];

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

            graph[v1].add(new Node(v2, c));
        }

        StringTokenizer st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken());
        int end = Integer.parseInt(st.nextToken());

        dijkstra(start, end);
        StringBuilder sb = new StringBuilder();
        sb.append(dist[end]).append("\n");

        Stack<Integer> stack = new Stack<>();
        while (end != -1) {
            stack.push(end);
            end = prev[end];
        }
        sb.append(stack.size()).append("\n");
        while (!stack.isEmpty()) sb.append(stack.pop()).append(" ");
        
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }



    private static void dijkstra(int start, int end) {
        // [idx, cost, List<>]
        PriorityQueue<Node> pq = new PriorityQueue<>((o1, o2) -> o1.cost - o2.cost);
        pq.add(new Node(start, 0));

        Arrays.fill(prev, -1);
        dist[start] = 0;

        while (!pq.isEmpty()) {
            Node current = pq.poll();
            if (visited[current.idx]) continue;
            visited[current.idx] = true;

            // 도착지점에 도달했으면 멈춤
            if (current.idx == end) {
                return;
            }

            List<Node> list = graph[current.idx];

            for (Node node : list) {
                StringBuilder sb = new StringBuilder();
                if (dist[node.idx] > dist[current.idx] + node.cost) {
                    dist[node.idx] = dist[current.idx] + node.cost;

                    // 다음 노드의 값과 패스 추가
                    pq.add(new Node(node.idx, dist[node.idx]));
                    prev[node.idx] = current.idx;
                }
            }
        }
    }


}