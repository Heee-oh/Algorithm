import java.io.*;
import java.util.*;

public class Main {

    static int[] dr = {1, -1, 0, 0};
    static int[] dc = {0, 0, 1, -1};
    static int N, M;
    static List<int[]> virus;
    static int[][] map;
    static int answer = Integer.MAX_VALUE;
    static List<int[]> selectVirus;
    static boolean[][] baseVisited;
    static int size;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        selectVirus = new ArrayList<>();
        virus = new ArrayList<>();
        baseVisited = new boolean[N][N];
        map = new int[N][N];
        size = N * N;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());

                if (map[i][j] == 2) {
                    virus.add(new int[]{i, j});
                    size--;
                } else if (map[i][j] == 1) {
                    baseVisited[i][j] = true;
                    size--;
                }
            }
        }

        if (size == 0) {
            System.out.println(0);
            return;
        }
        dfs(0, 0);

        System.out.println(answer == Integer.MAX_VALUE ? -1 : answer);

    }

    private static void dfs(int depth, int idx) {
        if (depth == M) {
            boolean[][] visited = new boolean[N][N];
            for (int i = 0; i < N; i++) visited[i] = baseVisited[i].clone();

            int result = bfs(visited);
            answer = Math.min(answer, (result == -1 ? Integer.MAX_VALUE : result));
            return;
        }

        for (int i = idx; i < virus.size(); i++) {
            selectVirus.add(virus.get(i));
            dfs(depth + 1, i + 1);
            selectVirus.remove(selectVirus.size() - 1);
        }
    }

    private static int bfs(boolean[][] visited) {
        Queue<int[]> q = new LinkedList<>();
        int emptySpace = size;
        int time = 0;

        for (int i = 0; i < selectVirus.size(); i++) {
            int[] virusInfo = selectVirus.get(i);
            visited[virusInfo[0]][virusInfo[1]] = true;
            q.add(new int[] {virusInfo[0], virusInfo[1], 0});
        }

        while (!q.isEmpty()) {
            int[] cur = q.poll();


            time = Math.max(time, cur[2]);

            for (int i = 0; i < 4; i++) {
                int nextR = cur[0] + dr[i];
                int nextC = cur[1] + dc[i];

                if (nextR < 0 || nextR >= N
                        || nextC < 0 || nextC >= N
                        || visited[nextR][nextC]) {
                    continue;
                }


                if (map[nextR][nextC] == 2) {
                    visited[nextR][nextC] = true;
                    q.offer(new int[]{nextR, nextC , cur[2] + 1});
                    continue;
                }

                visited[nextR][nextC] = true;
                q.offer(new int[]{nextR, nextC , cur[2] + 1});
                emptySpace--;
                if (emptySpace == 0) {
                    return cur[2] + 1;
                }
            }
        }


        if (emptySpace != 0) {
            return -1;
        }
        return time;
    }
}
