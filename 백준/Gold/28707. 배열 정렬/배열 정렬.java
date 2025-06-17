import java.io.*;
import java.util.*;

public class Main {
    // 28707번 배열 정렬

    static class Arr implements Comparable<Arr> {
        int[] arr;
        int cost;

        public Arr(int[] arr, int cost) {
            this.arr = arr;
            this.cost = cost;
        }

        @Override
        public int compareTo(Arr o) {
            return this.cost - o.cost;
        }
    }

    static int N, M;
    // 정렬 조작 리스트 (l, r, cost)
    static List<int[]> sortOperations = new ArrayList<>();
    // 방문한 상태의 최소 비용 저장 (키: Arrays.toString(arr))
    static Map<String, Integer> visited = new HashMap<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        // 1-based index 편의를 위해 길이 N+1
        int[] arr = new int[N + 1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        M = Integer.parseInt(br.readLine());
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int l = Integer.parseInt(st.nextToken());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            sortOperations.add(new int[]{l, r, c});
        }

        System.out.println(dijkstra(arr));
    }

    // 다익스트라로 최소 비용 탐색
    private static int dijkstra(int[] startArr) {
        PriorityQueue<Arr> pq = new PriorityQueue<>();
        String startKey = Arrays.toString(startArr);
        visited.put(startKey, 0);
        pq.add(new Arr(startArr, 0));

        while (!pq.isEmpty()) {
            Arr cur = pq.poll();
            String curKey = Arrays.toString(cur.arr);

            // 이미 더 좋은 경로가 기록되어 있으면 무시
            if (cur.cost > visited.get(curKey)) continue;

            // 정렬 완료 상태면 비용 반환
            if (isSorted(cur.arr)) {
                return cur.cost;
            }

            // 모든 swap 연산 시도
            for (int[] op : sortOperations) {
                int l = op[0], r = op[1], c = op[2];
                int[] nextArr = cur.arr.clone();

                // swap
                int tmp = nextArr[l];
                nextArr[l] = nextArr[r];
                nextArr[r] = tmp;

                int nextCost = cur.cost + c;
                String nextKey = Arrays.toString(nextArr);

                // 방문하지 않았거나 더 싼 비용일 때만 갱신
                if (!visited.containsKey(nextKey) || visited.get(nextKey) > nextCost) {
                    visited.put(nextKey, nextCost);
                    pq.add(new Arr(nextArr, nextCost));
                }
            }
        }

        // 도달 불가능
        return -1;
    }

    // 배열이 비내림차순인지 확인 (1..N)
    private static boolean isSorted(int[] arr) {
        for (int i = 1; i < N; i++) {
            if (arr[i] > arr[i + 1]) return false;
        }
        return true;
    }
}
