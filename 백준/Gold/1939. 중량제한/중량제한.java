import java.io.*;
import java.util.*;

public class Main {


    static class Island {
        int next;
        int cost;
        public Island (int next, int cost) {
            this.next = next;
            this.cost = cost;
        }

    }
    static ArrayList<Island>[] graph;
    static boolean[] visited;
    static int max = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken()); // 섬 개수
        int m = Integer.parseInt(st.nextToken()); // 다리 개수

        graph = new ArrayList[n + 1];
        visited = new boolean[n + 1];

        // 섬 그래프로 초기화
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken()); // 섬1
            int B = Integer.parseInt(st.nextToken()); // 섬2
            int C = Integer.parseInt(st.nextToken()); // 비용

            if (graph[A] == null) graph[A] = new ArrayList<>();
            if (graph[B] == null) graph[B] = new ArrayList<>();
            graph[A].add(new Island(B, C));
            graph[B].add(new Island(A, C));
            max = Math.max(max, C); // 탐색 범위 max 값
        }

        // 비용 내림차순 정렬
        for (ArrayList<Island> list : graph) {
            if (list != null) {
                Collections.sort(list, (o1, o2) -> o2.cost - o1.cost);
            }
        }

        // 공장이 있는 두 섬
        st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken());
        int end = Integer.parseInt(st.nextToken());


        int front = 1, back = max;
        int max = 0;

        while (front <= back) {

            int mid = (front + back) >>> 1;

            if (bfs(start, end, mid)) {
                front = mid + 1;
                max = mid;
            } else {
                back = mid - 1;
            }

            visited = new boolean[n + 1];
        }

        System.out.println(max);

    }

    // bfs로 공장이 있는 A -> B로 가는 길에 mid값으로 갈 수 있는 지 확인
    private static boolean bfs(int start, int end, int cost) {
        Queue<Island> q = new LinkedList<>();
        q.add(new Island(start, 0));
        visited[start] = true;

        while (!q.isEmpty()) {
            Island curIsland = q.poll();

            // 반대편 공장이 있는 섬에 도착했다면 true 반환
            if (curIsland.next == end) {
                return true;
            }

            ArrayList<Island> list = graph[curIsland.next];
            for (int i = 0; i < list.size(); i++) {

                Island nextIsland = list.get(i);
                // 방문하지 않았으면서, cost로 이동 가능하면 큐에 추가
                if (!visited[nextIsland.next] && nextIsland.cost >= cost) {
                    q.add(nextIsland);

                    visited[nextIsland.next] = true;
                }
            }
        }

        return false;
    }

}