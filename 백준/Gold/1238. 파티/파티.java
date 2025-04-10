import java.io.*;
import java.util.*;

public class Main {

    static class Node {
        int idx;
        int cost;

        public Node(int idx, int cost) {
            this.idx = idx;
            this.cost = cost;
        }
    }

    static int n;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int x = Integer.parseInt(st.nextToken());
        int[] xToAllDiff = new int[n + 1];
        int[] AllToXDiff = new int[n + 1];
        
        List<Node>[] graph = new List[n + 1];
        List<Node>[] reverseGraph = new List[n + 1];
        
        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
            reverseGraph[i] = new ArrayList<>();

        }

        for (int i = 1; i <= m; i++) {
            st = new StringTokenizer(br.readLine());

            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            int T = Integer.parseInt(st.nextToken());

            graph[A].add(new Node(B, T));
            reverseGraph[B].add(new Node(A, T));
        }

        //x -> 모든 정점으로 가는 최단거리
        Arrays.fill(xToAllDiff, Integer.MAX_VALUE);
        bfs(x, xToAllDiff, graph);

        // 모든 정점->x로 오는 최단거리
        Arrays.fill(AllToXDiff, Integer.MAX_VALUE);
        bfs(x, AllToXDiff, reverseGraph);

        // 오고 가는데 가장 오래걸리는 소요시간 구하기
        // 모든 정점 -> x 까지 최단거리
        int max = 0;
        for (int i = 1; i <= n; i++) {
            // i -> x + x -> i 까지 가는데 최단거리의 합중 최댓값을 갱신
            if (AllToXDiff[i] + xToAllDiff[i] > max) {
                max = AllToXDiff[i] + xToAllDiff[i];
            }
        }

        System.out.println(max);
    }

    private static void bfs(int start, int[] diff, List<Node>[] graph) {
        PriorityQueue<Node> pq = new PriorityQueue<>((o1, o2) -> o1.cost - o2.cost);
        boolean[] visited = new boolean[n + 1];
        pq.add(new Node(start, 0));
        diff[start] = 0;

        while (!pq.isEmpty()) {
            Node current = pq.poll();
            if (visited[current.idx]) continue;
            visited[current.idx] = true;

            List<Node> list = graph[current.idx];
            for (Node node : list) {

                if (diff[node.idx] > node.cost + diff[current.idx]) {
                    diff[node.idx] = node.cost + diff[current.idx];
                    pq.add(new Node(node.idx, diff[node.idx]));
                }
            }

        }

    }
}