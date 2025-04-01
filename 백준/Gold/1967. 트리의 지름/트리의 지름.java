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
    static List<Node>[] tree;
    static int[] diff;
    static boolean[] visited;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());

        // 루트 노드 혼자 있는 경우 0
        if (n == 1) {
            System.out.println(0);
            return;
        }

        tree = new ArrayList[n + 1];

        for (int i = 1; i <= n; i++) {
            tree[i] = new ArrayList<>();
        }

        // 입력은 n-1개
        for (int i = 1; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int parent = Integer.parseInt(st.nextToken());
            int child = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            tree[parent].add(new Node(child, cost));
            tree[child].add(new Node(parent, cost));
        }

        dijkstra(1, n);

        int maxIdx = 0;
        int max = 0;
        for (int i = 1; i < diff.length; i++) {
            if (max < diff[i]) {
                maxIdx = i;
                max = diff[i];
            }
        }
        dijkstra(maxIdx, n);

        System.out.println(Arrays.stream(diff).max().getAsInt());

    }




    private static void dijkstra(int start, int n) {
        Queue<Node> q = new LinkedList<>();
        visited = new boolean[n + 1];
        diff = new int[n + 1];

        q.add(new Node(start, 0));
        visited[start] = true;
        diff[start] = 0;

        while (!q.isEmpty()) {
            Node node = q.poll();
            List<Node> children = tree[node.idx];

            for (Node child : children) {
                if (!visited[child.idx]) {
                    diff[child.idx] = child.cost + diff[node.idx];
                    q.add(new Node(child.idx, diff[child.idx]));
                    visited[child.idx] = true;
                }
            }
        }
    }



}
