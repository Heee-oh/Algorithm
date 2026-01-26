import java.io.*;
import java.util.*;

public class Main {

    static int N, max = 0;
    static boolean[] isCracked;
    static int[][] eggs;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        isCracked = new boolean[N];
        eggs = new int[N][2];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            eggs[i][0] = Integer.parseInt(st.nextToken());
            eggs[i][1] = Integer.parseInt(st.nextToken());
        }


        dfs(0, 0);

        System.out.println(max);
    }


    private static void dfs(int pick, int cnt) {
        if (pick >= N) {
            max = Math.max(max, cnt);
            return;
        }

        // 손에 든 계란이 깨졌다면 넘어감
        if (isCracked[pick]) {
            dfs(pick + 1, cnt);
            return;
        }

        boolean flag = false;

        for (int i = 0; i < N; i++) {
            if (pick != i && !isCracked[i]) {
                int crackCnt = 0;
                flag = true;


                int pickEggHp = eggs[pick][0] - eggs[i][1];
                if (pickEggHp <= 0) {
                    isCracked[pick] = true;
                    crackCnt++;

                } else {
                    eggs[pick][0] = pickEggHp;
                }

                int iEggHp = eggs[i][0] - eggs[pick][1];
                if (iEggHp <= 0) {
                    isCracked[i] = true;
                    crackCnt++;

                } else {
                    eggs[i][0] = iEggHp;
                }

                dfs(pick + 1, cnt + crackCnt);

                if (pickEggHp <= 0) {
                    isCracked[pick] = false;
                } else {
                    eggs[pick][0] += eggs[i][1];
                }

                if (iEggHp <= 0) {
                    isCracked[i] = false;
                } else {
                    eggs[i][0] += eggs[pick][1];
                }
            }

        }

        // 깨지지 않은 다른 계란이 없다면 넘어감
        if (!flag) {
            dfs(pick + 1, cnt);
        }

    }


}
