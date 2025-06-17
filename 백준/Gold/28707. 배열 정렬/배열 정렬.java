import java.io.*;
import java.util.*;

public class Main {
    // 28707번 배열 정렬

    static class Arr {
        int[] arr;
        int cost;
        public Arr(int[] arr, int cost) {
            this.arr = arr;
            this.cost = cost;
        }
    }

    static int N, M;
    static String answer;
    static List<int[]> sortOperations = new ArrayList<>();


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        int[] arr = new int[N + 1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int[] sorted = arr.clone();
        Arrays.sort(sorted);

        answer = Arrays.toString(sorted);

        M = Integer.parseInt(br.readLine());
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int l = Integer.parseInt(st.nextToken());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            sortOperations.add(new int[]{l, r, c});
        }

        System.out.println(bfs(arr));
    }

    static Map<String, Integer> map = new HashMap<>();

    private static int bfs(int[] arr) {
        PriorityQueue<Arr> pq = new PriorityQueue<>((o1, o2) -> o1.cost - o2.cost);
        pq.add(new Arr(arr, 0));

        while (!pq.isEmpty()) {
            Arr current = pq.poll();
            String curKey = Arrays.toString(current.arr);
            
            if (curKey.equals(answer)) {
                return current.cost;
            }

            for (int[] operation : sortOperations) {
                int l = operation[0];
                int r = operation[1];
                int c = operation[2];

                int[] nArr = current.arr.clone();
                int tmp = nArr[l];
                nArr[l] = nArr[r];
                nArr[r] = tmp;

                String key = Arrays.toString(nArr);

                if (!map.containsKey(key) || map.get(key) > current.cost + c) {
                    map.put(key, current.cost + c);
                    pq.add(new Arr(nArr, current.cost + c));
                }
            }
        }

        return -1;
    }

}
