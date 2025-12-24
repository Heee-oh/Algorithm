import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Main {

    static class App {
        int memory, time;

        public App(int memory, int time) {
            this.memory = memory;
            this.time = time;
        }
    }
    static final int MAX_C = 10000;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        App[] apps = new App[N];
        long[] dp = new long[MAX_C + 1]; // 최대 10000초

        StringTokenizer memorySt = new StringTokenizer(br.readLine());
        StringTokenizer timeSt = new StringTokenizer(br.readLine());

        for (int i = 0; i < N; i++) {
            int mem = Integer.parseInt(memorySt.nextToken());
            int time = Integer.parseInt(timeSt.nextToken());
            apps[i] = new App(mem, time);
        }

        Arrays.sort(apps, (a, b) -> {
            if (a.time == b.time) {
                return a.memory - b.memory;
            }

            return a.memory - b.memory;
        });

        // dp[초] = dp[초 - time[i]] +memory[i]
        for (App app : apps) {
            int mem = app.memory;
            int time = app.time;

            for (int t = MAX_C; t >= app.time; t--) {
                dp[t] = Math.max(dp[t], dp[t - time] + mem);
            }
        }

        // 가장 빠른 M을 만족하는 시간대 탐색
        for (int t = 0; t <= MAX_C; t++) {
            if (dp[t] >= M) {
                System.out.println(t);
                break;
            }
        }
    }

}