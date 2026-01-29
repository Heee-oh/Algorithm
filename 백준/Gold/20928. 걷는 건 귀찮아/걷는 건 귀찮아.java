import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[] p = new int[N];
        int[] x = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            p[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            x[i] = Integer.parseInt(st.nextToken());
        }

        int end = p[0] + x[0];
        int idx = 0;
        int cnt = 0;

        while (end <= M) {
            boolean change = false;
            int max = -1;

            for (int i = idx + 1; i < N; i++) {
                if (end < p[i]) break;

                if (p[i] <= end
                        && end < p[i] + x[i]
                        && max < p[i] + x[i]) {

                    max = p[i] + x[i];
                    change = true;
                    idx = i;
                }
            }

            if (max != -1) {
                end = max;
                cnt++;
            }


            if (end >= M) {
                System.out.println(cnt);
                return;
            }

            if (!change) {
                System.out.println(-1);
                return;
            }
        }

        System.out.println(cnt);

    }

}
