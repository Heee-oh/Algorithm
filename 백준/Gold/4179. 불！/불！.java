import java.io.*;
import java.util.*;

public class Main {
    static int[] dy = {0, 0, 1, -1};
    static int[] dx = {1, -1, 0, 0};
    static boolean[][] visited;
    static boolean[][] fireVisited;
    static char[][] miro;
    static int[] startPos;
    static Queue<int[]> firePos = new LinkedList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int r = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());

        miro = new char[r][c];
        visited = new boolean[r][c];
        fireVisited = new boolean[r][c];
        for (int i = 0; i < r; i++) {
            String line = br.readLine();
            for (int j = 0; j < c; j++) {
                miro[i][j] = line.charAt(j);

                if (miro[i][j] == 'J') {
                    startPos = new int[]{i, j};

                }else if (miro[i][j] == 'F') {
                    firePos.add(new int[]{i, j});
                    fireVisited[i][j] = true;

                    // 벽은 방문표시로 갈 수 없게 만듦
                }else if (miro[i][j] == '#') {
                    fireVisited[i][j] = true;
                    visited[i][j] = true;
                }
            }
        }

        int answer = bfs();
        bw.write(answer == - 1 ? "IMPOSSIBLE" : answer + "");
        bw.flush();
        bw.close();
    }


    // 불 먼저 퍼트리고, 4가지 방향으로 이동
    // 불이 지훈 옆이라면 지훈 자리를 F

    private static int bfs() {
        Queue<int[]> jihoonPosQueue = new LinkedList<>();
        visited[startPos[0]][startPos[1]] = true;
        jihoonPosQueue.add(startPos);
        int time = 0;

        while (!jihoonPosQueue.isEmpty()) {
            fireBfs(); // 불 퍼뜨리기

            int size = jihoonPosQueue.size();
            time++;

            // 1분 후 지훈이 이동 가능한 곳 이동
            while (size--> 0) {
                int[] curPos = jihoonPosQueue.poll();

                // 탈출 가능하면 걸린 시간 반환
                if (escapeCheck(curPos)) {
                    return time;
                }

                for (int i = 0; i < 4; i++) {
                    int nextR = curPos[0] + dy[i];
                    int nextC = curPos[1] + dx[i];

                    // 미로 범위를 넘어서는 지 확인, 방문했는지, 불인지 확인
                    if (boundaryCheck(nextR, nextC, miro)
                            || visited[nextR][nextC]
                            || miro[nextR][nextC] == 'F') continue;

                    jihoonPosQueue.add(new int[]{nextR, nextC});
                    visited[nextR][nextC] = true;
                }
            }
        }
        return -1;
    }

    // 불 이동

    private static void fireBfs() {
        int cnt = firePos.size();

        // 현재 불들을 전파
        while (cnt--> 0 && !firePos.isEmpty()) {
            int[] fire = firePos.poll();

            for (int i = 0; i < 4; i++) {
                int newR = fire[0] + dy[i];
                int newC = fire[1] + dx[i];

                if (boundaryCheck(newR, newC, miro)
                        || miro[newR][newC] == '#'
                        || fireVisited[newR][newC]) continue;

                miro[newR][newC] = 'F';
                fireVisited[newR][newC] = true;
                firePos.add(new int[]{newR, newC});
            }
        }
    }

    // 탈출 체크
    private static boolean escapeCheck(int[] curPos) {
        return curPos[0] == 0 || curPos[0] == miro.length - 1
                || curPos[1] == 0 || curPos[1] == miro[0].length - 1;
    }

    // 미로 범위 체크
    private static boolean boundaryCheck(int newR, int newC, char[][] miro) {
        return newR < 0 || newR >= miro.length
                || newC < 0 || newC >= miro[0].length;
    }


}
