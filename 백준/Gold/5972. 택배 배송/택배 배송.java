import java.io.*;
import java.util.*;

public class Main {

    static ArrayList<CowNode>[] graph;
    static int[] diff;

    static class CowNode {
        int next;
        int cost;

        public CowNode(int next, int cost) {
            this.next = next;
            this.cost = cost;
        }
    }

    // 최단경로 다익스트라

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        graph = new ArrayList[n + 1];
        diff = new int[n + 1];

        for (int i = 0; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }

        Arrays.fill(diff, Integer.MAX_VALUE);

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            graph[start].add(new CowNode(end, cost));
            graph[end].add(new CowNode(start, cost));
        }

        // 비용이 적은 순으로 오름차순 정렬
        for (ArrayList<CowNode> cowNodes : graph) {
            Collections.sort(cowNodes, (o1, o2) -> o1.cost - o2.cost);
        }

        diff[1] = 0;
        dijkstra(1);


        bw.write(diff[n] + "");
        bw.flush();
        bw.close();

    }


    private static void dijkstra(int start) {
        PriorityQueue<Integer> q = new PriorityQueue<>();
        q.add(start);

        while (!q.isEmpty()) {
            int currentPos = q.poll();

            ArrayList<CowNode> cowList = graph[currentPos];
            for (CowNode cowNode : cowList) {
                if (diff[cowNode.next] > cowNode.cost + diff[currentPos]) {
                    diff[cowNode.next] = cowNode.cost + diff[currentPos];
                    q.add(cowNode.next);


                }
            }
        }
    }
}
