import java.io.*;
import java.util.*;

public class Main {

    static boolean[][] kinship;
    static boolean[] visited;
    static StringBuilder sb = new StringBuilder();
    static ArrayList<String> list = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        // 관계를 찾는 번호
        int x = Integer.parseInt(st.nextToken());
        int y = Integer.parseInt(st.nextToken());

        // 부모 자식들 간의 관계의 개수
        int m = Integer.parseInt(br.readLine());

        kinship = new boolean[n + 1][n + 1];
        visited = new boolean[n + 1];

        for (int i = 1; i <= m; i++) {
            st = new StringTokenizer(br.readLine());
            int parent = Integer.parseInt(st.nextToken());
            int child = Integer.parseInt(st.nextToken());

            kinship[parent][child] = true;
            kinship[child][parent] = true;
        }







        // 입력 차수가 0인 부모를 찾아서 리스트에 담기.
        // 최상위 부모가 다르면 -1
        // 같으면 무시하고 다음 번호로 , 번호가 다르면 그 위치부터 길이만큼 더하기

        int answer = bfs(x, y);
        bw.write((answer == 0 ? -1 : answer) + "\n");
        bw.flush();
        bw.close();

    }

    private static int bfs(int dest, int idx) {
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[] {idx, 0});
        visited[idx] = true;

        while (!q.isEmpty()) {

            int[] poll = q.poll();
            int num = poll[0];
            int level = poll[1];

            if (num == dest) {
                return level;
            }

            for (int i = 1; i < kinship.length; i++) {
                if (kinship[num][i] && !visited[i]) {
                    visited[i] = true;
                    q.add(new int[] {i, level + 1});
                }
            }


        }
        return 0;
    }
}