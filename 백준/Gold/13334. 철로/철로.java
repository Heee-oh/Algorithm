import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int[][] arr = new int[n][2];
        boolean[] visited = new boolean[n];

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int h = Integer.parseInt(st.nextToken());
            int o = Integer.parseInt(st.nextToken());

            // 집이 사무실보다 뒤에 있다면 서로 전환
            if (h > o) {
                arr[i][0] = o;
                arr[i][1] = h;
            } else {
                arr[i][0] = h;
                arr[i][1] = o;
            }
        }

        int d = Integer.parseInt(br.readLine());
        // 끝 위치를 기준으로 오름차순 정렬
        Arrays.sort(arr, (o1, o2) -> o1[1] - o2[1]);

        int max = 0;
        // 시작 오름차순
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[0] - o2[0]);

        for (int i = 0; i < n; i++) {
            int startBase = arr[i][1] - d;
            if (arr[i][0] >= startBase) {
                pq.add(arr[i]);

                while (!pq.isEmpty()) {
                    int[] next = pq.peek();
                    if (next[0] < startBase) {
                        pq.poll();
                    } else {
                        break;
                    }
                }
            }

            max = Math.max(max, pq.size());
        }


        System.out.println(max);
    }

}
