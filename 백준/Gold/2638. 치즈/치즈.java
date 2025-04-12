import java.io.*;
import java.util.*;

public class Main {


    static int[] dy = {1, -1, 0, 0};
    static int[] dx = {0, 0, 1, -1};
    static int[][] graph;
    static Queue<int[]> emtpySpaceQueue = new LinkedList<>();
    static int[][] contactCountCheck;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        graph = new int[n][m];


        int[] firstCheese = {-1, -1};
        int[] lastCheese = {-1, -1};

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                graph[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                // 처음 만나는 치즈 위치를 저장
                if (graph[i][j] == 1) {
                    firstCheese = new int[]{i, j};
                    break;
                }
            }
            if (firstCheese[0] != -1) break;
        }

        for (int i = n - 1; i >= 0; i--) {
            for (int j = m - 1; j >= 0; j--) {
                // 마지막 치즈 위치를 저장
                if (graph[i][j] == 1) {
                    lastCheese = new int[]{i, j};
                    break;
                }
            }
            if (lastCheese[0] != -1) break;
        }


        // 치즈의 외부 공간 좌표를 모두 얻는다.
        getExternalSpace(firstCheese, n, m);
        getExternalSpace(lastCheese, n, m);


        int time = 0;
        while (true) {
            // 모든 치즈가 삭제되었다면 멈춤
            if (bfs(n, m)) break;

            // 시간이 지나고 상한 치즈를 삭제
            time++;
            removeCheese(n,m);
        }





        bw.write(time + "");
        bw.flush();
        bw.close();
    }

    static List<int[]> externalSpace = new ArrayList<>();

    private static void getExternalSpace(int[] cheesePos, int n, int m) {


        // 처음만난 치즈와 접촉하는 4방향중 외부의 한 좌표를 구함
        for (int i = 0; i < 4; i++) {
            int nextR = cheesePos[0] + dy[i];
            int nextC = cheesePos[1] + dx[i];
            if (nextR <0 || nextR >= n || nextC < 0 || nextC >= m || graph[nextR][nextC] == 1) continue;
            externalSpace.add(new int[]{nextR, nextC});
        }
    }

    private static void removeCheese(int n, int m) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (contactCountCheck[i][j] >= 2) {
                    graph[i][j] = 0;
                }
            }
        }
    }

    private static boolean bfs(int n, int m) {
        contactCountCheck = new int[n][m];
        boolean allRemovedCheese = true;

        // 외부로 확정된 위치들을 큐에 넣는다.
        for (int[] pos : externalSpace) {
            emtpySpaceQueue.add(pos);
            contactCountCheck[pos[0]][pos[1]] = -1;
        }

        while (!emtpySpaceQueue.isEmpty()) {
            int[] current = emtpySpaceQueue.poll();

            // 4방향으로 이동
            for (int i = 0; i < 4; i++) {
                int nextR = current[0] + dy[i];
                int nextC = current[1] + dx[i];

                if (nextR < 0 || nextR >= n || nextC < 0 || nextC >= m) {
                    continue;
                }

                // 빈 공간이고 아직 방문하지 않았다면 방문하고 큐에 추가
                if (graph[nextR][nextC] == 0 && contactCountCheck[nextR][nextC] == 0) {
                    contactCountCheck[nextR][nextC] = -1;
                    emtpySpaceQueue.add(new int[]{nextR, nextC});

                    // 치즈 공간이라면 접촉 횟수를 + 1
                } else if (graph[nextR][nextC] == 1) {
                    contactCountCheck[nextR][nextC]++;
                    allRemovedCheese = false;
                }
            }
        }

        return allRemovedCheese;
    }

}