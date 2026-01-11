import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    // 처음 방문한 나무에서만 바나나 획득 가능
    // 매가 있으면 이동 불가
    // 이동시 H 1씩 감소
    // 같은 나무 최대 2번 방문 가능
    // H 가 0일때 E에 위치해야 바나나 최종적으로 획득
    // 수확한 최대 바나나 개수 구하기

    static List<Integer>[] graph;
    static boolean[] block;
    static int[] banana;
    static int N,  E;

    static int answer = -1;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        int H = Integer.parseInt(st.nextToken()); // hp

        graph = new ArrayList[N + 1];

        st = new StringTokenizer(br.readLine());
        int S = Integer.parseInt(st.nextToken()); // 시작지점
        E = Integer.parseInt(st.nextToken()); // 끝지점

        banana = new int[N + 1];
        block = new boolean[N + 1];

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            banana[i] = Integer.parseInt(st.nextToken());
            graph[i] = new ArrayList<>();
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            block[i] = Integer.parseInt(st.nextToken()) != 0;
        }


        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            graph[u].add(v);
            graph[v].add(u);
        }

        int[] visited = new int[N + 1];
        visited[S]++;
        
        dfs(visited, banana[S], H, S);

        System.out.println(answer);
    }

    private static void dfs(int[] visited, int cnt, int hp, int cur) {
        if (hp == 0) {
            if (cur == E) answer = Math.max(answer, cnt);
            return;
        }


        for (int i = 0; i < graph[cur].size(); i++) {
            int next = graph[cur].get(i);

            if (block[next] || visited[next] >= 2) continue;
            int newCnt = (visited[next] == 0) ? cnt + banana[next] : cnt;
            visited[next]++;
            dfs(visited, newCnt, hp - 1, next);
            visited[next]--;
        }
    }
}
