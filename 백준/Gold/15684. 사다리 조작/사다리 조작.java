import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, M, H;
    static boolean[][] ladderMap;
    static int answer = -1; // 답을 찾지 못했을 경우 -1
    static boolean found = false; // 최소값을 찾았는지 확인하는 플래그

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());

        // 가로선 연결 정보 배열, 인덱스 편의를 위해 N+1 대신 N+2 사용 가능
        ladderMap = new boolean[H + 1][N + 1];

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            ladderMap[a][b] = true;
        }

        // 추가할 사다리 개수를 0개부터 3개까지 순차적으로 시도
        for (int i = 0; i <= 3; i++) {
            answer = i;
            dfs(1, 0); // (시작 행, 추가한 사다리 개수)
            if (found) { // 답을 찾았으면 루프 종료
                break;
            }
        }

        // 답을 찾았으면 answer, 못찾았으면 -1 출력
        System.out.println(found ? answer : -1);
    }

    /**
     * @param startRow 탐색을 시작할 가로선 행
     * @param count    현재까지 추가한 사다리 개수
     */
    private static void dfs(int startRow, int count) {
        // 이미 최소값을 찾았다면 더 이상 탐색하지 않음
        if (found) {
            return;
        }

        // 목표 개수(answer)만큼 사다리를 다 놓았다면
        if (count == answer) {
            if (checkLadder()) { // i번 -> i번으로 도착하는지 검사
                found = true;
            }
            return;
        }

        // 조합 생성을 위한 탐색
        // startRow 부터 시작하여 이전에 탐색한 행은 다시 보지 않도록 함 (중복 조합 방지)
        for (int i = startRow; i <= H; i++) {
            for (int j = 1; j < N; j++) {
                // 현재 위치, 왼쪽, 오른쪽에 이미 사다리가 놓여있는지 확인
                if (ladderMap[i][j] || ladderMap[i][j - 1] || (j + 1 <= N && ladderMap[i][j + 1])) {
                    continue;
                }
                
                ladderMap[i][j] = true; // 사다리 설치
                dfs(i, count + 1); // 다음 사다리 탐색 (현재 행부터 다시 시작)
                ladderMap[i][j] = false; // 백트래킹: 사다리 제거
            }
        }
    }

    /**
     * 모든 세로선이 자기 자신으로 돌아오는지 확인하는 함수
     * @return 성공 여부
     */
    private static boolean checkLadder() {
        for (int startCol = 1; startCol <= N; startCol++) {
            int currentCol = startCol;
            for (int row = 1; row <= H; row++) {
                if (ladderMap[row][currentCol]) { // 오른쪽으로 이동
                    currentCol++;
                } else if (currentCol > 1 && ladderMap[row][currentCol - 1]) { // 왼쪽으로 이동
                    currentCol--;
                }
            }
            // 최종 도착 위치가 시작 위치와 다르면 실패
            if (currentCol != startCol) {
                return false;
            }
        }
        return true;
    }
}