import java.io.*;
import java.util.*;

public class Main {

    static int N, S;
    static int[] dp;

    static class Mole {
        int r, c, t;

        public Mole(int r, int c, int t) {
            this.r = r;
            this.c = c;
            this.t = t;
        }
    }

    static Mole[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());

        arr = new Mole[N + 1];
        dp = new int[N + 1];


        // 시작 점
        arr[0] = new Mole(0, 0, 0);

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int t = Integer.parseInt(st.nextToken());
            arr[i] = new Mole(r, c, t);
        }

        Arrays.sort(arr, (o1, o2) -> o1.t - o2.t);

        for (int i = 1; i <= N; i++) {
            if (!canMove(arr[i], arr[0], S)) {
                dp[i] = Integer.MIN_VALUE / 2;
            } else {
                dp[i] = 1;
            }
        }


        for (int i = 1; i <= N; i++) {
            for (int j = 0; j < i; j++) {
                if (canMove(arr[j], arr[i], S)) {
                    dp[i] = Math.max(dp[j] + 1, dp[i]);
                }
            }
        }

        System.out.println(Arrays.stream(dp).max().getAsInt());

    }

    private static boolean canMove(Mole a, Mole b, int s) {
        // 시간 체크
        long dt = b.t - a.t;
        if (dt < 0) return false;

        long dc = b.c - a.c;
        long dr = b.r - a.r;

        long dist = dc * dc + dr * dr;
        double reach = (double) s * dt;
        return dist <= reach * reach + 1e-9;

    }


}
