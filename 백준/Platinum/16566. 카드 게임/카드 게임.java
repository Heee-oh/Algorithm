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
        parent = IntStream.range(0, m).toArray();


        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < m; i++) {
            cards[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(cards);


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
                union(current, current + 1); // 사용 후 다음 사용하지 않은 카드를 가리키게 병합
            }
        }
        System.out.print(sb.toString());
    }

    // a 는 현재 선택된 카드, b는 그 다음 카드
    private static void union(int a, int b) {
        a = find(a);
        b = find(b);

        parent[a] = b;
    }

    // 경로 압축
    private static int find(int x) {
        if (x == parent[x]) return x;
        return parent[x] = find(parent[x]);
    }
}
