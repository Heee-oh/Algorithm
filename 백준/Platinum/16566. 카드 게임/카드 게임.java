import java.io.*;
import java.util.*;
import java.util.stream.IntStream;

public class Main {
    static int[] parent;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int[] cards = new int[m];
        boolean[] arr = new boolean[n + 1];
        parent = IntStream.range(0, n + 1).toArray();

        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < m; i++) {
            int num = Integer.parseInt(st.nextToken());
            arr[num] = true;
        }

        int idx = 0;
        for (int i = 1; i <= n; i++) {
            if (idx == m) break;
            if (arr[i]) cards[idx++] = i;
        }


        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < k; i++) {
            int target = Integer.parseInt(st.nextToken());
            // 이분 탐색
            int left = 0, right= m - 1;
            while (left < right) {
                int mid = (left + right) >>> 1;

                if (cards[mid] < target) {
                    left = mid + 1;
                } else {
                    right = mid;
                }
            }


            // 같은 값이면 안되고 더 큰 값중 가장 작은 값이여야하므로 바로 다음 칸의 값을 가리킴
            if (cards[left] == target) {
                left++;
            }

            int current = find(left); // 사용하지 않은 카드 찾기
            sb.append(cards[current]).append("\n"); // 카드 사용

            if (current + 1 < m) {
                int a = find(current);
                int b = find(current + 1);
                parent[a] = b;
            }
        }
        System.out.print(sb.toString());
    }

    // 경로 압축
    private static int find(int x) {
        if (x == parent[x]) return x;
        return parent[x] = find(parent[x]);
    }
}
