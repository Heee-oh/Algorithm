import java.io.*;
import java.util.*;

public class Main {
    // 28707번 배열 정렬

    static class Arr {
        int arr;
        int cost;
        public Arr(int arr, int cost) {
            this.arr = arr;
            this.cost = cost;
        }
    }

    static int N, M;
    static int answer;
    static List<int[]> sortOperations = new ArrayList<>();


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        int[] arr = new int[N + 1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            arr[i] = Integer.parseInt(st.nextToken()) - 1;
        }

        int[] sorted = arr.clone();
        Arrays.sort(sorted);

        answer = getNumber(sorted);

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

    static Map<Integer, Integer> map = new HashMap<>();

    private static int bfs(int[] arr) {
        PriorityQueue<Arr> pq = new PriorityQueue<>((o1, o2) -> o1.cost - o2.cost);
        pq.add(new Arr(getNumber(arr), 0));

        while (!pq.isEmpty()) {
            Arr current = pq.poll();

            if (answer == current.arr) {
                return current.cost;
            }

            for (int[] operation : sortOperations) {
                int l = operation[0];
                int r = operation[1];
                int c = operation[2];

                int num = swap(current.arr, l, r);


                if (!map.containsKey(num) || map.get(num) > current.cost + c) {
                    map.put(num, current.cost + c);
                    pq.add(new Arr(num, current.cost + c));
                }
            }
        }

        return -1;
    }
    private static int getNumber(int[] arr) {
        int n = 0;
        for (int i = 1; i < N; i++) {
            n = (n + arr[i]) * 10;
        }

        return n + arr[N];
    }

    private static int swap(int num, int l, int r) {
        int leftMult = (int) Math.pow(10, N - l);
        int rightMult = (int) Math.pow(10, N - r);

        // left 인덱스의 수를 가져온 다음 * leftMult (자릿수 맞추기)
        int leftNum = num / leftMult % 10;
        int rightNum = num / rightMult % 10;

        int left = leftNum * leftMult;
        int right = rightNum * rightMult;

        int newNum = num - (left + right);

        newNum += (rightNum * leftMult + leftNum * rightMult);
        return newNum;
    }

}
