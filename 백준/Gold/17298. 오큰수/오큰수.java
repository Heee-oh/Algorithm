import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        Stack<int[]> stack = new Stack<>();
        
        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];
        int[] answer = new int[n];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        stack.push(new int[] {arr[0], 0});

        for (int i = 1; i < n; i++) {
            while (!stack.isEmpty() && stack.peek()[0] < arr[i]) {
                int[] num = stack.pop();
                answer[num[1]] = arr[i];
            }
            stack.push(new int[] {arr[i], i});
        }


        while (!stack.isEmpty()) {
            int[] num = stack.pop();
            answer[num[1]] = -1;
        }

        for (int i : answer) {
            sb.append(i).append(" ");
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
}