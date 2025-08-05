import java.io.*;
import java.util.*;
import java.util.stream.IntStream;

public class Main {
    // 17825번 주사위 윷놀이

    static int[] dice = new int[10]; // 주사위 눈
    static int[] horse = new int[4]; // 말의 위치
    static int answer = 0;
    // (위에서 정의한 score, next, blue_next 배열)
    static final int GOAL = 32;
    // 각 칸의 점수
    static int[] score = {
            0, 2, 4, 6, 8, 10, 12, 14, 16, 18, 20, 22, 24, 26, 28, 30, 32, 34, 36, 38, 40, // 0~20
            13, 16, 19, // 21~23 (10에서 분기)
            22, 24,     // 24~25 (20에서 분기)
            28, 27, 26, // 26~28 (30에서 분기)
            25, 30, 35  // 29~31 (중앙 교차점)
    };

    // 다음 칸으로 이동 경로 (빨간 화살표)
    static int[] next = new int[32];

    // 파란 화살표 분기 경로
    static int[] blue_next = new int[32]; // 0이면 분기 없음


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int i = 0; i < 10; i++) {
            dice[i] = Integer.parseInt(st.nextToken());
        }

        buildMap();
        dfs(0, 0);

        System.out.println(answer);

    }

    // 경로 설정
    private static void buildMap() {
        // 1. 바깥쪽 경로 (0 -> 2 -> ... -> 38 -> 40)
        for (int i = 0; i < 20; i++) {
            next[i] = i + 1;
        }
        // 40(20) -> 도착(32, 임의의 도착 인덱스)
        next[20] = 32;

        // 2. 10(5)에서 분기하는 경로 (13 -> 16 -> 19 -> 25)
        blue_next[5] = 21;
        next[21] = 22; next[22] = 23; next[23] = 29;

        // 3. 20(10)에서 분기하는 경로 (22 -> 24 -> 25)
        blue_next[10] = 24;
        next[24] = 25; next[25] = 29;

        // 4. 30(15)에서 분기하는 경로 (28 -> 27 -> 26 -> 25)
        blue_next[15] = 26;
        next[26] = 27; next[27] = 28; next[28] = 29;

        // 5. 중앙 경로 (25 -> 30 -> 35 -> 40)
        next[29] = 30; next[30] = 31; next[31] = 20;

        // 도착 인덱스(32)는 더 이상 경로가 없음
    }
    private static void dfs(int turn, int currentScore) {
        // 1. 종료 조건: 10번의 턴을 모두 마쳤을 때
        if (turn == 10) {
            answer = Math.max(answer, currentScore);
            return;
        }

        // 2. 재귀: 4개의 말 각각에 대해 이동 시도
        for (int i = 0; i < 4; i++) {
            int startPos = horse[i];

            // 이미 도착한 말은 움직일 수 없음
            if (startPos == GOAL) continue;

            // 이동할 위치 계산
            int moveCount = dice[turn];
            int currentPos = startPos;

            // 첫 번째 칸 이동: 파란색 칸에서 시작하는지 확인
            if (blue_next[currentPos] != 0) {
                currentPos = blue_next[currentPos];
            } else {
                currentPos = next[currentPos];
            }

            // 나머지 칸 이동
            for (int j = 1; j < moveCount; j++) {
                // 이동 중에 도착하면 멈춤
                if (currentPos == GOAL) break;
                currentPos = next[currentPos];
            }

            int endPos = currentPos;

            // 도착지가 아닌데 다른 말이 이미 있다면, 그 움직임은 불가능
            boolean isOccupied = false;
            if (endPos != GOAL) {
                for (int j = 0; j < 4; j++) {
                    if (horse[j] == endPos) {
                        isOccupied = true;
                        break;
                    }
                }
            }

            if (isOccupied) continue;

            // 3. 상태 업데이트 및 재귀 호출 (백트래킹)
            horse[i] = endPos; // 말 위치 이동
            int nextScore = (endPos == GOAL) ? 0 : score[endPos];
            dfs(turn + 1, currentScore + nextScore);
            horse[i] = startPos; // 말 위치 원상복구 (백트래킹)
        }
    }


}

