import java.io.*;
import java.util.*;

public class Main {

    // 0~N-1까지 분기점(노드) 존재,
    // 0이면 갈 수 있음, 1이면 갈 수 없음, 넥서스는 당연히 1이지만 갈 수 있음
    // 양방향, 한 분기점에서 다른 분긱점으로 가는 간선은 최대 1개

    static int N, M;

    static class Node {
        int next;
        long cost;

        public Node(int next, long cost) {
            this.next = next;
            this.cost = cost;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
         N = Integer.parseInt(st.nextToken());
         M = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        long[] dist = new long[N];
        boolean[] vision = new boolean[N];
        List<Node>[] graph = new List[N];

        for (int i = 0; i < N; i++) {
            vision[i] = st.nextToken().equals("1");
            graph[i] = new ArrayList<>();
        }

        Arrays.fill(dist, Long.MAX_VALUE);
        vision[N-1] = false;
        dist[0] = 0;


        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            long w = Long.parseLong(st.nextToken());

            // 상대 시야 있을 경우 지나갈 수 없음
            if (vision[u] || vision[v]) continue;

            graph[u].add(new Node(v, w));
            graph[v].add(new Node(u, w));
        }

        dijkstra(dist, graph);

        System.out.println((dist[N-1] == Long.MAX_VALUE) ? -1 : dist[N-1]);
    }

    private static void dijkstra(long[] dist, List<Node>[] graph) {
        PriorityQueue<Node> pq = new PriorityQueue<>((a,b) -> Long.compare(a.cost,b.cost));
        boolean[] visited = new boolean[N];
        pq.offer(new Node(0, 0));

        while (!pq.isEmpty()) {
            Node cur = pq.poll();

            if (visited[cur.next]) continue;
            visited[cur.next] = true;

            List<Node> list = graph[cur.next];
            for (int i = 0; i < list.size(); i++) {
                Node node = list.get(i);

                if (dist[node.next] > dist[cur.next] + node.cost) {
                    dist[node.next] = dist[cur.next] + node.cost;

                    pq.offer(new Node(node.next, dist[node.next]));
                }
            }
        }
    }
}
