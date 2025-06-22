import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];
        int[] dp = new int[N];
        int[] prev = new int[N];
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
                prev[i] = size - 1;
                size++;
                continue;
            }

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
            if (left > 0) {
                prev[i] = left - 1;
            }
        }

        Stack<Integer> stack = new Stack<>();
        sb.append(size).append("\n");
        int target = size - 2;

        for (int i = N - 1; i >= 0 ; i--) {
            if (target == prev[i]) {
                stack.push(arr[i]);
                target--;
            }
        }

        while (!stack.isEmpty()) {
            sb.append(stack.pop()).append(" ");
        }

        System.out.println(sb.toString());
    }
}
