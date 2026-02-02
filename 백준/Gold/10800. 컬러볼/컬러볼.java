import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(br.readLine());

        int[] answer = new int[N];
        Map<Integer, Integer>[] map = new HashMap[2001]; // size에 대한 색들
        int[] arr = new int[N + 1]; // 각 색의 총 크기
        boolean[] visited = new boolean[N + 1]; // 방문 여부
        int total = 0; // 총 합

        for (int i = 0; i < 2001; i++) {
            map[i] = new HashMap<>();
        }

        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o2[2] - o1[2]); // 무게 내림차순
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int c = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());

            int value = map[s].getOrDefault(c, 0);
            map[s].put(c, value + 1);

            arr[c] += s;
            total += s;

            pq.offer(new int[]{i, c, s});
        }


        while (!pq.isEmpty()) {
            int[] cur = pq.poll();
            int num = cur[0];
            int color = cur[1];
            int size = cur[2];

            // 현재 뽑은 공 처리
            total -= size;
            arr[color] -= size;
            visited[color] = true;

            int sum = total - arr[color]; // 같은 색의 총 합을 뺀다.


            for (Map.Entry<Integer, Integer> entry : map[size].entrySet()) {
                Integer c = entry.getKey();
                if (visited[c]) continue;

                sum -= size * entry.getValue(); // 같은 사이즈의 총합을 뺀다.

            }


            int cnt = map[size].get(color);
            map[size].put(color, cnt - 1);



            visited[color] = false;
            answer[num] = sum;
        }

        for (int i = 0; i < N; i++) {
            sb.append(answer[i]).append("\n");
        }
        System.out.print(sb.toString().trim());
    }

}
