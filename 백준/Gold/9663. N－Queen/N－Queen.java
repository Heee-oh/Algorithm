import java.io.*;
import java.util.*;

public class Main {
    // NXN 체스판 위 N개를 놓는 방법 (서로 공격 X)
    // 행, 열, 대각 체크
    static int[] map;
    static int answer = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());
        map = new int[n];

        Arrays.fill(map, -1);
        dfs(n, 0);

        System.out.println(answer);
    }


    private static void dfs(int n, int row) {
        if (n == row) {
            answer++;
            return;
        }

        // 열
        for (int i = 0; i < n; i++) {
            // 이전 퀸들과 공격하는 위치에 있는지 확인
            if (available(n, row, i)) {
                map[row] = i;
                dfs(n, row + 1);
                map[row] = -1;
            }
        }
    }

    // 새로운 퀸을 놓을 수 있는지 검증
    private static boolean available(int n, int y, int x) {
        // 각 행을 돌면서 체크
        for (int j = 0; j < y; j++) {
            // x축이 같거나 ,     x증가량 과 y증가량이 같다면 (이는 대각선을 의미)
            if (map[j] == x || Math.abs(j - y) == Math.abs(map[j] - x)) {
                return false;
            }
        }
        return true;
    }
}