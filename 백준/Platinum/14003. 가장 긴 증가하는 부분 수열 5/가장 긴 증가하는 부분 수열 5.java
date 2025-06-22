import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];
        int[] dp = new int[N];
        int[] prev = new int[N]; // dp에서 이전 증가하는 수열의 값의 실제 arr 내 idx 저장
        int[] dpIdx = new int[N]; // DP에 저장된 실제 수열 arr에서의 idx 를 저장 

        Arrays.fill(prev, -1);

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        dp[0] = arr[0];
        int size = 1;
        for (int i = 1; i < N; i++) {

            if (dp[size - 1] < arr[i]) {
                dp[size] = arr[i];
                dpIdx[size] = i;
                prev[i] = dpIdx[size - 1];
                size++;
                continue;
            }

            // 이분 탐색으로 증가하는 부분수열에서 적절한 위치를 탐색
            int left = 0, right = size - 1;
            while (left < right) {

                int mid = (left + right) >>> 1;

                if (dp[mid] < arr[i]) {
                    left = mid + 1;
                } else {
                    right = mid;
                }
            }

            dp[left] = arr[i];
            dpIdx[left] = i;
            if (left > 0) {
                prev[i] = dpIdx[left - 1];
            }
        }


        Stack<Integer> stack = new Stack<>();
        sb.append(size).append("\n");


        int idx = dpIdx[size-1];

        while (size > 0) {
            stack.push(arr[idx]);
            idx = prev[idx];
            size--;
        }


        while (!stack.isEmpty()) {
            sb.append(stack.pop()).append(" ");
        }

        System.out.println(sb.toString());
    }
}
