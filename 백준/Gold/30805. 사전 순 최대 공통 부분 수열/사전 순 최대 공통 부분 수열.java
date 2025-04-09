import java.io.*;
import java.util.*;

public class Main {

    static PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o2[0] == o1[0] ? o1[1] - o2[1] : o2[0] - o1[0]);

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));


        int n = Integer.parseInt(br.readLine());
        int[] A = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int m = Integer.parseInt(br.readLine());
        int[] B = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        // 큰 값 순으로 정렬해서 pq에 담음
        for (int i = 0; i < n; i++) {
            pq.add(new int[] {A[i], i});
        }

        // 값이 더 큰 것 대로, 같다면 인덱스가 더 앞인 것으로
        PriorityQueue<int[]> pq2 = new PriorityQueue<>((o1, o2) -> o1[0] == o2[0] ? o1[1] - o2[1] : o2[0] - o1[0]);

        int start = 0;
        int limit = -1;
        while (!pq.isEmpty() ) {
            int[] nextMax = pq.poll();

            for (int i = start; i < m; i++) {
                if (nextMax[0] == B[i] && limit < nextMax[1]) {
                    pq2.add(new int[] {nextMax[0], i});
                    start = i + 1;
                    limit = nextMax[1];
                    break;
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        sb.append(pq2.size()).append("\n");
        while (!pq2.isEmpty()) {
            sb.append(pq2.poll()[0]).append(" ");
        }

        System.out.println(sb.toString());

    }




}