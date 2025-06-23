import java.io.*;
import java.util.*;
import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        List<Integer>[] graph = new List[N + 1];

        for (int i = 1; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }

        int[] topology = new int[N + 1];

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            graph[a].add(b);
            topology[b]++; // 진입차수 증가
        }

        PriorityQueue<Integer> pq = new PriorityQueue<>();
        StringBuilder sb = new StringBuilder();
        // 진입차수가 0인 먼저 풀거나, 그냥 풀어도되는 문제들 저장
        for (int i = 1; i <= N; i++) {
            if (topology[i] == 0) {
                pq.add(i);
            }
        }

        while (!pq.isEmpty()) {
            int num = pq.poll();
            sb.append(num).append(" ");
            for (int problem : graph[num]) {
                topology[problem]--;

                if (topology[problem] == 0) {
                    pq.add(problem);
                }
            }
        }


        System.out.println(sb.toString());
    }



}
