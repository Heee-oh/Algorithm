import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class Main {
    // 연결된 커넥티드 카와 같은 위치에 있는 연결안된 카도 연결됨
    // 1연료 1칸, 모두 소진시 이동 불가
    // 연결가능한 모든 커넥티드 카 구하기
    // 초기에는 모두 연결 X, S 번 커넥티드 카 먼저 연결

    static int[] pos;
    static int[] h;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(st.nextToken());
        int S = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        pos = new int[N+1];
        for (int i = 1; i <= N; i++) {
            pos[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        h = new int[N+1];
        for (int i = 1; i <= N; i++) {
            h[i] = Integer.parseInt(st.nextToken());
        }

        int s = S, e = S; // 범위 지정
        boolean[] visited = new boolean[N+1];
        Queue<Integer> q = new LinkedList<>();
        visited[S] = true;
        q.add(S);

        while(!q.isEmpty()) {
            int next = q.poll();

            // 왼쪽 이동 거리가 시작 범위보다 더 작다면, 즉, 범위가 넓어질 수 있다면 진행
            if (Math.max(pos[next] - h[next], pos[1]) < pos[s]) {
                int tmp = s;

                for (int i = s; i >= 1; i--) {
                    if (pos[next] - h[next] > pos[i]) break;
                    tmp = i; // 위치 갱신
                    visited[i] = true;
                    q.add(i); // 다음 탐색에 저장

                }

                s = tmp;
            }

            // 오른쪽 이동 거리가 시작 범위보다 더 크다면, 즉, 범위가 넓어질 수 있다면 진행
            if (Math.min(pos[next] + h[next], pos[N]) > pos[e]) {
                int tmp = e;
                for (int i = e; i <= N; i++) {
                    if (pos[next] + h[next] < pos[i]) break;
                    tmp = i; // 위치 갱신
                    visited[i] = true;
                    q.add(i); // 다음 탐색에 저장
                }

                e = tmp;
            }

            h[next] = 0; // 이동 끝났으면 연료 0으로 저장
        }

        for (int i = 1; i <= N; i++) {
            if (visited[i]) {
                sb.append(i).append(" ");
            }

        }

        System.out.println(sb.toString().trim());

    }


}
