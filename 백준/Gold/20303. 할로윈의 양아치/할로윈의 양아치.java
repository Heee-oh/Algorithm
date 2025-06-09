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

        Set<Integer> set = new HashSet<>();
        // 각 집합의 캔디 합을 저장
        for (int i = 1; i <= N; i++) {
            int area = find(i);
            candySum[area] += candies[i];
            childCnt[area]++;
            set.add(area);

        }

        int[][] dp = new int[set.size() + 1][K];
        int[] arr = new int[set.size() + 1];

        int cnt = 1;
        for (int value : set) {
            arr[cnt++] = value;
        }

        for (int k = 1; k < K; k++) {
            for (int i = 1; i < arr.length; i++) {
                int idx = arr[i];

                // 현 k명보다 적다면 이전 값의 최대값을 가짐
                if (k < childCnt[idx]) {
                    dp[i][k] = Math.max(dp[i - 1][k], dp[i][k - 1]);

                    // 이전 최댓값 + 현재 캔디 개수 비교
                } else {
                    dp[i][k] = Math.max(dp[i][k], candySum[idx] + dp[i - 1][k - childCnt[idx]]);
                }

                // 이전값과 비교해서 최댓값 갱신
                dp[i][k] = Math.max(dp[i][k], Math.max(dp[i - 1][k], dp[i][k - 1]));
            }

        }

        System.out.println(dp[arr.length - 1][K-1]);

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
