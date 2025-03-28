import java.io.*;
import java.util.*;

public class Main {

    static int[] visited;
    static List<Integer>[] graph;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());

        graph = new List[n + 1];
        visited = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < n - 1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int v1 = Integer.parseInt(st.nextToken());
            int v2 = Integer.parseInt(st.nextToken());

            graph[v1].add(v2);
            graph[v2].add(v1);
        }

        bfs();

        for (int i = 2; i <= n; i++) {
            sb.append(visited[i]).append("\n");
        }

        System.out.print(sb.toString());

    }



    private static void bfs() {
        Queue<Integer> q = new LinkedList<>();
        visited[1] = 1;
        q.add(1);

        while (!q.isEmpty()) {
            int parent = q.poll();
            List<Integer> list = graph[parent];
            for (int i = 0; i < list.size(); i++) {
                int idx = list.get(i);
                if (visited[idx] == 0) {
                    visited[idx] = parent;
                    q.add(idx);
                }
            }
        }
    }




}
