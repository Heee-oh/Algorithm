import java.io.*;
import java.util.*;

public class Main {

    static int N, K;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        int len = String.valueOf(N).length();
        boolean[][] visited = new boolean[(int) (1e6 + 1)][K + 1];
        int max = - 1;

        Queue<Integer> q = new ArrayDeque<>();
        q.add(N);

        // k 번 바꿀 경우
        for (int i = 1; i <= K; i++) {
            int size = q.size();

            while (size-- > 0) {
                int cur = q.poll();

                char[] num = String.valueOf(cur).toCharArray();

                // 각 자리를 바꾸는 모든 경우의 수 탐색
                for (int j = 0; j < len-1; j++) {

                    for (int k = j + 1; k < len; k++) {

                        char tmp = num[j];
                        num[j] = num[k];
                        num[k] = tmp;

                        // 0으로 시작하지 않으면 가능한 수
                        if (num[0] != '0') {
                            int res = 0;
                            for (int l = 0; l < len; l++) {
                                res = res * 10 + (num[l] - '0');
                            }

                            // 중복 방지
                            if (visited[res][i]) {
                                num[k] = num[j];
                                num[j] = tmp;
                                continue;
                            }
                            visited[res][i] = true;
                            q.offer(res);

                            // 마지막 K 번째 체인지일때 최대값 갱신
                            if (i == K) {
                                max = Math.max(max, res);
                            }
                        }

                        num[k] = num[j];
                        num[j] = tmp;
                    }
                }


            }


        }


        System.out.println(max);

    }
}