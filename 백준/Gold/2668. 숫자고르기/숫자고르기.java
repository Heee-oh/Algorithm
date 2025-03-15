import java.awt.image.renderable.ContextualRenderedImageFactory;
import java.io.*;
import java.util.*;

public class Main {
    static TreeSet<Integer> ts = new TreeSet<>();
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(br.readLine());
        int[] graph = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            graph[i] = Integer.parseInt(br.readLine());
        }

        // 사이클이 있다면 가능
        for (int i = 1; i <= n; i++) {
            if (ts.contains(i)) continue;
            visited = new boolean[n + 1];
            visited[i] = true;

            // graph , i, i가 가리키는 것
            dfs(graph, i, graph[i]);
        }

        sb.append(ts.size()).append("\n");
        ts.forEach(x -> sb.append(x).append("\n"));

        System.out.print(sb.toString());
    }

    private static void dfs(int[] graph, int origin, int idx) {
        // 사이클이면 사이클 노드들 저장
        if (origin == idx) {
            int next = origin;

            do {
                ts.add(next);
                next = graph[next];
            } while (next != origin);
        }

        if (!visited[idx]) {
            visited[idx] = true;
            dfs(graph, origin, graph[idx]);
        }
    }


}
