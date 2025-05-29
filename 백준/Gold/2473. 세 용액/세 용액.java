import java.io.*;
import java.util.*;

public class Main {
    static int[] solutions;
    static int[] answer;
    static long min;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int n = Integer.parseInt(br.readLine());
        solutions = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());

        // 용액 특성값 초기화
        for (int i = 0; i < n; i++) {
            solutions[i] = Integer.parseInt(st.nextToken());
        }

        // 특성값을 오름차순 정렬
        Arrays.sort(solutions);

        answer = new int[3];
        min = Long.MAX_VALUE;

        for (int i = 0; i < n - 2; i++) {

            // 투포인터 + 이분탐색
            int left = i + 1, right = n - 1;
            while (left < right) {
                // 먼저 두개의 용액을 섞는다.
                long sum = (long)solutions[i] + solutions[left] + solutions[right];

                if (min > Math.abs(sum)) {
                    answer[0] = solutions[i];
                    answer[1] = solutions[left];
                    answer[2] = solutions[right];
                    min = Math.abs(sum);
                }

                if (sum == 0) {
                    break;
                }
                
                if (sum < 0) left++;
                else right--;
            }
        }

        for (int val : answer) {
            sb.append(val).append(" ");
        }

        System.out.println(sb.toString());

    }



}
