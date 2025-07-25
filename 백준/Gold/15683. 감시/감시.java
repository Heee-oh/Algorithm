import java.io.*;
import java.util.*;

public class Main {
    //  15683번 감시

    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};
    static int[][] map;

    static int N, M;
    static int size;

    static List<int[]> cctv;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        boolean[][] visited = new boolean[N][M];
        size = N * M;

        cctv = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (0 < map[i][j] && map[i][j] < 6) {
                    cctv.add(new int[]{i, j, map[i][j]});

                } else if (map[i][j] == 6) {
                    size--;
                }
            }
        }

        // cctv 방문처리
        for (int[] cctvInfo : cctv) {
            visited[cctvInfo[0]][cctvInfo[1]] = true;
            size--;
        }

        dfs(0, 0, visited);

        System.out.println(answer);

    }

    static int answer = Integer.MAX_VALUE;
    private static void dfs(int idx, int sum, boolean[][] visited) {
        if (idx >= cctv.size()) {
            answer = Math.min(answer, size - sum);
            return;
        }

        int[] cctvInfo = cctv.get(idx);
        int cctvR = cctvInfo[0];
        int cctvC = cctvInfo[1];
        int cctvN = cctvInfo[2];

        if (cctvN == 2) {
            for (int i = 0; i < 2; i++) {

                int cnt = 0;
                boolean[][] nextVisited = new boolean[N][M];
                for (int j = 0; j < visited.length; j++) {
                    nextVisited[j] = visited[j].clone();
                }

                for (int j = 0; j < 2; j++) {
                    int nextR = cctvR;
                    int nextC = cctvC;

                    while (true) {
                        nextR += dr[(i + (2 * j)) % 4];
                        nextC += dc[(i + (2 * j)) % 4];

                        // 범위 초과 또는 벽일 시 종료
                        if (nextR < 0 || nextR >= N
                                || nextC < 0 || nextC >= M
                                || map[nextR][nextC] == 6) {
                            break;
                        }


                        // 방문하지 않았다면 카운팅
                        if (!nextVisited[nextR][nextC]) {
                            nextVisited[nextR][nextC] = true;
                            cnt++; // 개수 카운트
                        }
                    }
                }

                // 다음 cctv 탐색
                dfs(idx + 1, sum + cnt, nextVisited);


            }

        } else {
            for (int i = 0; i < (cctvN == 5 ? 1 : 4); i++) {

                int cctvType = (cctvN - 1) == 0 ? 1 : cctvN - 1;

                int cnt = 0;
                boolean[][] nextVisited = new boolean[N][M];
                for (int j = 0; j < visited.length; j++) {
                    nextVisited[j] = visited[j].clone();
                }

                // cctv 타입의 방향만큼 진행
                for (int j = 0; j < cctvType; j++) {
                    int nextR = cctvR;
                    int nextC = cctvC;

                    while (true) {
                        nextR += dr[(i + j) % 4];
                        nextC += dc[(i + j) % 4];

                        // 범위 초과 또는 벽일 시 종료
                        if (nextR < 0 || nextR >= N
                                || nextC < 0 || nextC >= M
                                || map[nextR][nextC] == 6) {
                            break;
                        }

                        // 방문하지 않았다면 카운팅
                        if (!nextVisited[nextR][nextC]) {
                            nextVisited[nextR][nextC] = true;
                            cnt++; // 개수 카운트
                        }
                    }
                }


                // 다음 cctv 탐색
                dfs(idx + 1, sum + cnt, nextVisited);
            }
        }



    }




}
