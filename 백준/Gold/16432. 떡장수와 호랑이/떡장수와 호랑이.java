import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        List<Integer>[][] graph = new List[N + 1][10];

        for (int i = 1; i <= N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int m = Integer.parseInt(st.nextToken());

            for (int j = 0; j < m; j++) {
                int a = Integer.parseInt(st.nextToken());
                graph[i][a] = new ArrayList<>();

                if (i == 1) graph[i][a].add(0);

                for (int k = 1; k < 10; k++) {
                    if (graph[i-1][k] == null) continue;
                    if (a != k && !graph[i - 1][k].isEmpty()) {
                        graph[i][a].add(k);
                    }
                }

            }

        }

        Queue<int[]> q = new LinkedList<>();
        for (int i = 1; i < 10; i++) {
            if (graph[N][i] != null && !graph[N][i].isEmpty()) {
                q.add(new int[] {N, i});
                break;
            }
        }

        // N일까지 갈 수 없다면 바로 -1
        if (q.isEmpty()) {
            System.out.println(-1);
            return;
        }

        while (!q.isEmpty()) {
            int[] info = q.poll();
            int day = info[0];
            int type = info[1];

            sb.append( type).append("\n");
            if (day - 1 == 0) break;

            int next = graph[day][type].get(0);
            q.add(new int[] {day - 1, next});

        }
        System.out.print(sb.reverse().toString().trim());
    }
}
