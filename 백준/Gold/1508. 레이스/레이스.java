import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken()) - 1; // 처음 무조건 선택
        int K = Integer.parseInt(st.nextToken());

        int[] arr = new int[K];
        char[] answer = new char[K];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < K; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        // 두 심판과의 거리 탐색
        int start = 0, end = N;
        while (start <= end) {

            int dist = (start + end) >>> 1;

            // 거리 계산 기준 인덱스
            char[] tmp = new char[K];
            Arrays.fill(tmp, '0');
            tmp[0] = '1'; // 처음 위치 선점

            int idx = 0, cnt = 0;
            while (cnt < M) {

                // 적절한 위치 탐색
                int left = idx, right = K - 1;
                while (left < right) {

                    int mid = (left + right) >>> 1;

                    if (arr[mid] - arr[idx] < dist) {
                        left = mid + 1;
                    } else {
                        right = mid;
                    }
                }

                if (arr[left] - arr[idx] < dist) {
                    break;

                } else {
                    tmp[left] = '1';
                    idx = left;
                    cnt++;
                }
            }


            if (cnt == M) {
                start = dist + 1;
                answer = tmp;
            } else {
                end = dist - 1;
            }
        }

        System.out.println(answer);

    }
}
