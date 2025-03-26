import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        boolean[][] arr = new boolean[n + 1][n + 1];

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());

            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());

            arr[A][B] = arr[B][A] = true;
        }

        int min = Integer.MAX_VALUE;
        int answer = 0;

        for (int i = 1; i <= n; i++) {
            boolean[] visited = new boolean[n + 1];
            int degree = bfs(arr, visited, i);

            // 케빈 베이컨 지수 최솟값 갱신
            if (min > degree) { // 같을때는 갱신하지 않으므로 번호가 가장 낮은 사람을 보장
                min = degree;
                answer = i; // 번호가 가장 낮은 사람 보장
            }
        }


        bw.write(answer + "");
        bw.flush();
        bw.close();

    }


    private static int bfs(boolean[][] graph, boolean[] visited, int start) {
        Queue<int[]> q = new LinkedList<>();
        visited[start] = true;
        q.add(new int[] {start, 1});

        int sum = 0;

        while (!q.isEmpty()) {
            int[] node =  q.poll();

            for (int i = 1; i < graph.length; i++) {
                if (graph[node[0]][i] && !visited[i]) {
                    visited[i] = true;
                    sum += node[1];

                    q.add(new int[]{i, node[1] + 1});
                }
            }

        }

        return sum;
    }

}
