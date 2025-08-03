import java.io.*;
import java.util.*;

public class Main {


    // 말 중첩 가능 , 위의 전부 같이 이동
    // 4개이상 쌓이면 종료

    // 현재 말이 이동하려는 칸에 따른 처리
    // 흰색 : 이동, 말이 있다면 가장 위에 현재 말을 올려놓음 (이때 현재 말 위에 애들이 있다면 같이 이동)
    // 빨강 : 이동 후 A번 말과 그 위에 있는 모든 말의 쌓여있는 순서를 반대로 바꿈 (역순으로됨)
    // 파랑 : A번 말의 이동방향을 반대로 하고 한 칸 이동, 방향을 반대로 바꾼 후 이동하려는 칸이 파란색인 경우 무행동
    //  - 체스판을 벗어나는 경우도 파랑과 같이 적용


    // 0 흰색, 1 빨강, 2 파랑

    // 게임 종료되는 턴 번호를 출력
    // 1000보다 크거나 절대 종료불가시 -1 출력

    static int N, K;
    static int[][] map;

    static int[] dr = {0, 0, -1, 1};
    static int[] dc = {1, -1, 0, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        map = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        List<Integer>[][] list = new List[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                list[i][j] = new ArrayList<>();
            }
        }


        List<int[]> pawns = new ArrayList<>();
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken()) - 1;
            int c = Integer.parseInt(st.nextToken()) - 1;
            int d = Integer.parseInt(st.nextToken()) - 1;
            pawns.add(new int[]{r, c, d});
            list[r][c].add(i);
        }



        int turn = 1000;
        while (turn-- > 0) {
            for (int num = 0; num < K; num++) {
                int[] pawn = pawns.get(num);
                int d = pawn[2];


                int nextR = pawn[0] + dr[d];
                int nextC = pawn[1] + dc[d];

                List<Integer> curPos = list[pawn[0]][pawn[1]];

                // 파랑인 경우
                if (nextR < 0 || nextR >= N
                        || nextC < 0 || nextC >= N
                        || map[nextR][nextC] == 2) {

                    // 반대 방향으로 전환
                    d = changeDir(d);

                    // 전환후 이동 방향 체크
                    nextR = pawn[0] + dr[d];
                    nextC = pawn[1] + dc[d];


                    // 전환후 이동할 칸이 파랑색이면 정지 (행동 X)
                    if (nextR < 0 || nextR >= N
                            || nextC < 0 || nextC >= N) {
                        pawn[2] = d;
                        continue;
                    }


                    if (map[nextR][nextC] == 2) {
                        pawn[2] = d;

                        if (list[nextR][nextC].size() >= 4) {
                            System.out.println(1000 - turn);
                            return;
                        }
                        continue;
                    }

                    // 아니라면 이동
                    List<Integer> nextPos = list[nextR][nextC];

                    if (map[nextR][nextC] == 1) {

                        for (int j = 0; j < curPos.size(); j++) {

                            // num 번의 말 이동
                            if (num == curPos.get(j)) {
                                // 역순으로 추가
                                for (int i = curPos.size() - 1; i >= j; i--) {
                                    int number = curPos.remove(i);
                                    nextPos.add(number);

                                    int[] nextPawn = pawns.get(number);
                                    nextPawn[0] = nextR;
                                    nextPawn[1] = nextC;

                                    if (num == number) {
                                        nextPawn[2] = d;
                                    }
                                }
                                break;
                            }
                        }

                    } else {

                        for (int j = 0; j < curPos.size(); j++) {

                            // num 번의 말 이동
                            if (num == curPos.get(j)) {
                                for (int k = j; k < curPos.size(); k++) {
                                    int number = curPos.get(k);
                                    nextPos.add(number);

                                    int[] nextPawn = pawns.get(number);
                                    nextPawn[0] = nextR;
                                    nextPawn[1] = nextC;

                                    if (num == number) {
                                        nextPawn[2] = d;
                                    }
                                }

                                // 옮긴거 삭제
                                for (int i = curPos.size() - 1; i >= j; i--) {
                                    curPos.remove(i);

                                }

                                break;
                            }
                        }
                    }




                    if (nextPos.size() >= 4) {
                        System.out.println(1000 - turn);
                        return;
                    }

                    // 흰색인 경우
                } else if (map[nextR][nextC] == 0) {
                    List<Integer> nextPos = list[nextR][nextC];

                    for (int j = 0; j < curPos.size(); j++) {

                        // num 번의 말 이동
                        if (num == curPos.get(j)) {

                            for (int k = j; k < curPos.size(); k++) {
                                int number = curPos.get(k);
                                nextPos.add(number);

                                int[] nextPawn = pawns.get(number);
                                nextPawn[0] = nextR;
                                nextPawn[1] = nextC;

                            }

                            // 옮긴거 삭제
                            for (int i = curPos.size() - 1; i >= j; i--) {
                                curPos.remove(i);
                            }
                            break;
                        }
                    }

                    if (nextPos.size() >= 4) {
                        System.out.println(1000 - turn);
                        return;
                    }

                    // 빨강인 경우
                } else {
                    List<Integer> nextPos = list[nextR][nextC];

                    for (int j = 0; j < curPos.size(); j++) {

                        // num 번의 말 이동
                        if (num == curPos.get(j)) {
                            // 역순으로 추가
                            for (int i = curPos.size() - 1; i >= j; i--) {
                                int number = curPos.remove(i);
                                nextPos.add(number);

                                int[] nextPawn = pawns.get(number);
                                nextPawn[0] = nextR;
                                nextPawn[1] = nextC;
                            }
                            break;
                        }
                    }

                    if (nextPos.size() >= 4) {
                        System.out.println(1000 - turn);
                        return;
                    }
                }
            }
        }

        System.out.println(-1);
    }

    private static int changeDir(int d) {
        switch (d) {
            case 0 : return  1;
            case 1 : return  0;
            case 2 : return  3;
            case 3 : return  2;
        }

        return - 1;
    }
}

