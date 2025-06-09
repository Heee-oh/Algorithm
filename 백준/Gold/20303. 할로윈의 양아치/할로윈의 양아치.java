import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.IntStream;

public class Main {
    // k 명 이상 뺏으면 안됨

    static int[] parent;
    static int[] candySum;
    static int[] childCnt; // 소속된 집합의 아이들 수

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()); // 아이들 수
        int M = Integer.parseInt(st.nextToken()); // 친구 관계 수
        int K = Integer.parseInt(st.nextToken()); // 울음소리가 공명하기 위한 최소 아이 수

        parent = IntStream.range(0, N + 1).toArray();
        candySum = new int[N + 1];
        childCnt = new int[N + 1];

        st = new StringTokenizer(br.readLine());
        int[] candies = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            candies[i] = Integer.parseInt(st.nextToken());
        }


        // 유니온 집합
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            union(a, b);
        }

        // 각 집합의 캔디 합을 저장
        for (int i = 1; i <= N; i++) {
            int area = find(i); // 최상위 부모를 찾을때는 항상 find로 찾자
            candySum[area] += candies[i];
            childCnt[area]++;
        }

        int[] dp = new int[K];

        for (int i = 1; i <= N; i++) {
            int w = childCnt[i];
            if (w == 0 || w >= K) continue;
            int v = candySum[i];

            for (int c = K - 1 ; c >= w; c--) {
                dp[c] = Math.max(dp[c], dp[c - w] + v);
            }
        }

        int answer = Arrays.stream(dp).max().getAsInt();
        System.out.println(answer);


    }

    private static void union(int a, int b) {
        a = find(a);
        b = find(b);

        if (a < b) {
            parent[b] = a;
        } else {
            parent[a] = b;
        }
    }

    private static int find(int x) {
        if (parent[x] == x) return x;
        return parent[x] = find(parent[x]);
    }
}
