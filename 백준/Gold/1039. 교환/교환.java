import java.io.*;
import java.util.*;

public class Main {

    static int N, K;
    static boolean[][] visited = new boolean[1000001][11];

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        Queue<Integer> q = new LinkedList<>();
        q.offer(N);
        visited[N][0] = true;

        int answer = -1;

        for (int depth = 0; depth < K; depth++) {
            int size = q.size();

            if (size == 0) break;

            for (int s = 0; s < size; s++) {
                int cur = q.poll();
                char[] arr = String.valueOf(cur).toCharArray();

                int len = arr.length;

                for (int i = 0; i < len - 1; i++) {
                    for (int j = i + 1; j < len; j++) {

                        // swap
                        char temp = arr[i];
                        arr[i] = arr[j];
                        arr[j] = temp;

                        // 맨 앞이 0이면 skip
                        if (arr[0] != '0') {
                            int next = Integer.parseInt(new String(arr));

                            if (!visited[next][depth + 1]) {
                                visited[next][depth + 1] = true;
                                q.offer(next);
                            }
                        }

                        // 원상복구
                        temp = arr[i];
                        arr[i] = arr[j];
                        arr[j] = temp;
                    }
                }
            }
        }

        while (!q.isEmpty()) {
            answer = Math.max(answer, q.poll());
        }

        System.out.println(answer);
    }
}