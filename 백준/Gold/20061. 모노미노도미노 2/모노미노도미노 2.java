import java.io.*;
import java.util.*;
import java.util.stream.IntStream;

public class Main {
    // 20061번 모노미노도미노 2
    // 블록은 한번 보드 위에 놓이면 다른 블록과 합쳐지지 않음

    // 1. 점수를 출력
    // 2. 파란색 보드와 초록 보드에서 타일이 들어 있는 칸의 개수를 출력

    static boolean[][] map;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        map = new boolean[10][10];
        int score = 0;

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int t = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            // 1. 주어진 좌표에서 왼쪽 아래로 도형 이동시키기
            moveBlueArea(t, x, y);
            moveRedArea(t, x, y);



            // 2. 행이나 열이 가득 찬 경우 없을때까지 점수획득

            for (int j = 4; j < 10; j++) {
                boolean isColFullLine = map[0][j] & map[1][j] & map[2][j] & map[3][j];
                if (isColFullLine) {
                    for (int k = j; k >= 4; k--) {
                        map[0][k] = map[0][k - 1];
                        map[1][k] = map[1][k - 1];
                        map[2][k] = map[2][k - 1];
                        map[3][k] = map[3][k - 1];
                    }
                    score++;
                }


                boolean isRowFullLine = map[j][0] & map[j][1] & map[j][2] & map[j][3];
                if (isRowFullLine) {
                    for (int k = j; k >= 4; k--) {
                        map[k][0] = map[k - 1][0];
                        map[k][1] = map[k - 1][1];
                        map[k][2] = map[k - 1][2];
                        map[k][3] = map[k - 1][3];
                    }
                    score++;
                }

            }


            // 3. 연한 칸에 블록이 있는 경우 처리 (한칸 당기기)

            int rowCnt = 0;
            int colCnt = 0;
            for (int j = 4; j <= 5; j++) {
                for (int k = 0; k < 4; k++) {
                    if (map[k][j]) {
                        colCnt++;
                        break;
                    }
                }

                for (int k = 0; k < 4; k++) {
                    if (map[j][k]) {
                        rowCnt++;
                        break;
                    }
                }
            }

            if (colCnt > 0) {
                for (int j = 9; j >= 4; j--) {
                    map[0][j] = map[0][j - colCnt];
                    map[1][j] = map[1][j - colCnt];
                    map[2][j] = map[2][j - colCnt];
                    map[3][j] = map[3][j - colCnt];
                }


            }

            if (rowCnt > 0) {
                for (int j = 9; j >= 4; j--) {
                    map[j][0] = map[j - rowCnt][0];
                    map[j][1] = map[j - rowCnt][1];
                    map[j][2] = map[j - rowCnt][2];
                    map[j][3] = map[j - rowCnt][3];
                }
            }
        }

        int cnt = 0;
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (map[i][j]) {
                    cnt++;
                }
            }
        }

        System.out.println(score);
        System.out.println(cnt);


    }

    private static void moveBlueArea(int t,int x, int y) {
        boolean flag = false;
        for (int c = y; c < 10; c++) {
            if (t == 1) {
                if (map[x][c]) {
                    map[x][c - 1] = true;
                    flag = true;
                    break;
                }

            } else if (t == 2) {
                if (c + 1 >= 10 || map[x][c + 1]) {
                    map[x][c - 1] = map[x][c] = true;
                    flag = true;
                    break;
                }
            } else {
                if (map[x][c] || map[x +1][c]) {
                    map[x][c-1] = map[x +1][c-1] = true;
                    flag = true;
                    break;
                }
            }

        }


        if (!flag) {
            if (t == 1) {
                map[x][9] = true;
            } else if (t == 2) {
                map[x][8] = map[x][9] = true;
            } else {
                map[x][9] = map[x +1][9] = true;
            }
        }
    }

    private static void moveRedArea(int t,int x, int y) {

        boolean flag = false;
        for (int r = x; r < 10; r++) {
            if (t == 1) {
                if (map[r][y]) {
                    map[r-1][y] = true;
                    flag = true;
                    break;
                }

            } else if (t == 2) {
                if (map[r][y] || map[r][y + 1]) {
                    map[r-1][y] = map[r-1][y+1] = true;
                    flag = true;
                    break;
                }
            } else {
                if (r + 1 >= 10 || map[r+1][y]) {
                    map[r - 1][y] = map[r][y] = true;
                    flag = true;
                    break;
                }
            }
        }

        if (!flag) {
            if (t == 1) {
                map[9][y] = true;

            } else if (t == 2) {
                map[9][y] = map[9][y + 1] = true;

            } else {
                map[8][y] = map[9][y] = true;
            }
        }
    }



    private static void printMap() {
        for (int j = 0; j < 10; j++) {

            for (int k = 0; k < 10; k++) {

                if (j >= 4 && k >= 4) {
                    System.out.print("  ");
                    continue;
                }
                if (map[j][k]) {
                    System.out.print(1 + " ");
                }  else {
                    System.out.print(0 + " ");
                }

            }
            System.out.println();
        }
    }


}

