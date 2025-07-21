import java.io.*;
import java.util.*;

public class Main {

    // 14888번 연산자 끼워넣기

    static int n, max, min;
    static int[] arr, op;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        StringTokenizer opSt = new StringTokenizer(br.readLine());
        arr = new int[n];
        op = new int[4];

        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < 4; i++) {
            op[i] = Integer.parseInt(opSt.nextToken());
        }


        max = Integer.MIN_VALUE;
        min = Integer.MAX_VALUE;

        dfs(arr[0], 1);

        System.out.println(max);
        System.out.println(min);

    }

    private static void dfs(int sum, int depth) {

        if (depth == n) {
            max = Math.max(max, sum);
            min = Math.min(min, sum);
            return;
        }


        for (int i = 0; i < 4; i++) {
            if (op[i] == 0) continue;

            op[i]--;
            if (i == 0) {
                dfs(sum + arr[depth], depth + 1);
            } else if (i == 1) {
                dfs(sum - arr[depth], depth + 1);
            } else if (i == 2) {
                dfs(sum * arr[depth], depth + 1);
            } else{
                dfs(sum / arr[depth], depth + 1);
            }

            op[i]++;
        }
    }



}
