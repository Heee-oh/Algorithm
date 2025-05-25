import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int T = Integer.parseInt(st.nextToken());

        while (T--> 0) {
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken()); // 건물의 개수
            int k = Integer.parseInt(st.nextToken()); // 건설 순서 총 개수

            // 건설 시간 초기화
            int[] topologyCheck = new int[n + 1];
            int[] buildCost = new int[n + 1];
            int[] cost = new int[n + 1];
            List<Integer>[] graph = new List[n + 1];

            st = new StringTokenizer(br.readLine());
            for (int i = 1; i <= n; i++) {
                buildCost[i] = Integer.parseInt(st.nextToken());
                graph[i] = new ArrayList<>();
            }

            // 건설 순서 초기화
            for (int i = 0; i < k; i++) {
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());

                topologyCheck[y]++;
                graph[x].add(y);
            }

            int target = Integer.parseInt(br.readLine());

            Queue<Integer> q = new LinkedList<>();
            // 시작점 찾기
            for (int i = 1; i <= n; i++) {
                if (topologyCheck[i] == 0) {
                    cost[i] = buildCost[i];
                    q.add(i);
                }
            }

            while (!q.isEmpty()) {
                int current = q.poll();
                List<Integer> list = graph[current];

                for (int next : list) {
                    if (cost[next] < cost[current] + buildCost[next]) {
                        cost[next] = cost[current] + buildCost[next];
                        q.add(next);
                    }
                }
            }

            bw.write((cost[target] == 0 ? buildCost[target] : cost[target])+ "\n");
        }
        bw.flush();
        bw.close();
    }

}
