import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class Main {
    static int n, m;
    static int[] dy = {1, -1, 0, 0};
    static int[] dx = {0, 0, 1, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        int[] redPos = new int[2];
        int[] bluePos = new int[2];

        // 맵 초기화
        char[][] map = new char[n][m];
        for (int i = 0; i < n; i++) {
            char[] ch = br.readLine().toCharArray();
            for (int j = 0; j < m; j++) {
                map[i][j] = ch[j];
                if (ch[j] == 'R') {
                    redPos[0] = i;
                    redPos[1] = j;

                } else if (ch[j] == 'B') {
                    bluePos[0] = i;
                    bluePos[1] = j;
                }
            }
        }

        dfs(map, redPos, bluePos, 1, -1 );

        System.out.println(answer == 11 ? -1 : answer);
    }

    static int answer = Integer.MAX_VALUE;

    // 같은 행 혹은 같은 열에 있을 때 행은 행에 대한 이동 방향에 대하여 각각 다르게 구슬 이동, 열도 마찬가지
    private static void dfs(char[][] map, int[] red, int[] blue, int cnt, int direction) {
        // 10번을 넘기면 -1 로 불가능 처리
        if (cnt > 10) {
            answer = Math.min(answer, 11); // 11은 불가능한 숫자
            return;
        }

        // 같은  행
        boolean isSameRow  = false;
        // 같은 열
        boolean isSameColumn  = false;
        if (red[0] - blue[0] == 0) {
            isSameRow  = true;

        } else if (red[1] - blue[1] == 0) {
            isSameColumn  = true;
        }

        for (int i = 0; i < 4; i++) {
            // 이전과 같은 방향으로는 진행 X
            if (direction == i) continue;

            char[][] newMap = deepArrCopy(map);
            int[] nextRed, nextBlue;


            if (isSameRow ) {
                // 빨강 구슬 - 파랑 구슬
                if (red[1] < blue[1]) {
                    // 오른쪽 방향으로 기울인다면 파란색 먼저
                    if (i == 2) {
                        nextBlue = move(newMap, blue, i, 'B');
                        nextRed = move(newMap, red, i, 'R');
                        // 왼쪽으로 기울인다면 빨간색 먼저
                    } else if (i == 3) {
                        nextRed = move(newMap, red, i, 'R');
                        nextBlue = move(newMap, blue, i, 'B');
                        //아무거나
                    } else {
                        nextRed = move(newMap, red, i, 'R');
                        nextBlue = move(newMap, blue, i, 'B');
                    }

                    // 파랑 구슬 - 빨강 구승
                } else {
                    // 오른쪽 방향으로 기울인다면 빨간색 먼저
                    if (i == 2) {
                        nextRed = move(newMap, red, i, 'R');
                        nextBlue = move(newMap, blue, i, 'B');
                        // 왼쪽으로 기울인다면 파란색 먼저
                    } else if (i == 3) {
                        nextBlue = move(newMap, blue, i, 'B');
                        nextRed = move(newMap, red, i, 'R');
                        // 아무거나
                    }else {
                        nextRed = move(newMap, red, i, 'R');
                        nextBlue = move(newMap, blue, i, 'B');
                    }
                }
            } else if (isSameColumn ) {
                // 빨강
                // 파랑
                if (red[0] < blue[0]) {
                    // 아래로 기울인다면 파랑 먼저
                    if (i == 0) {
                        nextBlue = move(newMap, blue, i, 'B');
                        nextRed = move(newMap, red, i, 'R');
                        // 위로 기울인다면 빨강 먼저
                    } else if (i == 1) {
                        nextRed = move(newMap, red, i, 'R');
                        nextBlue = move(newMap, blue, i, 'B');
                        // 아무거나
                    } else {
                        nextRed = move(newMap, red, i, 'R');
                        nextBlue = move(newMap, blue, i, 'B');
                    }

                    // 파랑
                    // 빨강
                } else {
                    // 아래로 기울인다면 빨강 먼저
                    if (i == 0) {
                        nextRed = move(newMap, red, i, 'R');
                        nextBlue = move(newMap, blue, i, 'B');
                        // 위로 기울인다면 파랑 먼저
                    } else if (i == 1) {
                        nextBlue = move(newMap, blue, i, 'B');
                        nextRed = move(newMap, red, i, 'R');
                        // 아무거나
                    } else {
                        nextRed = move(newMap, red, i, 'R');
                        nextBlue = move(newMap, blue, i, 'B');
                    }
                }

                //같은 행도 같은 열도 아니면 아무거나 먼저
            } else {
                nextRed = move(newMap, red, i, 'R');
                nextBlue = move(newMap, blue, i, 'B');
            }

            // 범위를 넘어갔다면
//            if (nextRed[0] < 0 || nextRed[0] >= n || nextRed[1] < 0 || nextRed[1] >= m
//                    || nextBlue[0] < 0 || nextBlue[0] >= n || nextBlue[1] < 0 || nextBlue[1] >= m) {
//
//                newMap[nextRed[0] - dy[i]][nextRed[1] - dx[i]] = 'R';
//                newMap[nextBlue[0] - dy[i]][nextBlue[1] - dx[i]] = 'B';
//
//                dfs(newMap, new int[]{nextRed[0] - dy[i] , nextRed[1] - dx[i]}, new int[]{nextBlue[0] - dy[i], nextBlue[1] - dx[i]}, cnt + 1, i);
//                return;
//            }


            // 파랑 구슬이 구멍에 들어간 경우 무조건 -1 이므로 동시에 들어간 경우를 고려할 필요없음
            if (newMap[nextBlue[0]][nextBlue[1]] == 'O') {
                answer = Math.min(answer, 11);
                continue;

                // 빨강 구슬만 구멍에 들어간 경우
            } else if (newMap[nextRed[0]][nextRed[1]] == 'O') {
                answer = Math.min(answer, cnt);
            }


//            if ((red[0] == nextRed[0] && red[1] == nextRed[1]) || (blue[0] == nextBlue[0] && blue[1] == nextBlue[1])) {
//                System.out.println(newMap[nextRed[0] - dy[i]][nextRed[1] - dx[i]]  + " " + newMap[nextBlue[0] - dy[i]][nextBlue[1] - dx[i]]);
//                System.out.println((nextRed[0] - dy[i]) + " " +(nextRed[1] - dx[i])  + " " + (nextBlue[0] - dy[i]) + " " + (nextBlue[1] - dx[i]));
//
//            }


            newMap[nextRed[0] - dy[i]][nextRed[1] - dx[i]] = 'R';
            newMap[nextBlue[0] - dy[i]][nextBlue[1] - dx[i]] = 'B';

            dfs(newMap, new int[]{nextRed[0] - dy[i] , nextRed[1] - dx[i]}, new int[]{nextBlue[0] - dy[i], nextBlue[1] - dx[i]}, cnt + 1, i);


        }



    }

    private static int[] move(char[][] map, int[] marble, int i, char color) {
        int r = marble[0];
        int c = marble[1];
        map[marble[0]][marble[1]] = '.';

        while (map[r][c] == '.') {
            r += dy[i];
            c += dx[i];
        }

        // 겹쳐지는 경우를 없애기 위해 골이 아니라면
        if (map[r][c] != 'O') {
            map[r - dy[i]][c-dx[i]] = color;
        }

        return new int[]{r, c};
    }

    private static char[][] deepArrCopy(char[][] current) {
        char[][] newMap = new char[n][n];
        for (int j = 0; j < n; j++) {
            newMap[j] = current[j].clone();
        }
        return newMap;
    }


}
