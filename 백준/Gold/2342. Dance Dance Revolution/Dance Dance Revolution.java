import java.io.*;
import java.util.*;

public class Main {

    static int length;
    static int[] arr;
    static int[][][] dp; // level, x,y or x,y

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        length = st.countTokens() - 1;

        arr = new int[length];
        dp = new int[5][5][100001];

        for (int i = 0; i < length; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                Arrays.fill(dp[i][j], -1);
            }
        }

        System.out.println(dfs(0, 0, 0));
    }

    private static int dfs(int idx, int left, int right) {
        if (idx == length) return 0;
        if (dp[left][right][idx] != -1) return dp[left][right][idx];


        dp[left][right][idx] = Math.min(dfs(idx + 1, arr[idx], right) + calcMovePower(left, arr[idx]),
                dfs(idx + 1, left, arr[idx]) + calcMovePower(right, arr[idx]));
        return dp[left][right][idx];
    }

    private static int calcMovePower(int curPos, int target) {
        if (curPos == 0) return 2;
        if (curPos == target) return 1;
        // 반대변
        if ((curPos % 2 == 0 && target % 2 == 0)
                || (curPos % 2 == 1 && target % 2 == 1)) return 4;
        // 중앙으로 이동
        return 3;
    }



}
