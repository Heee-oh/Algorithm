import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());
        
        int[] tops = new int[n];
        int[] answer = new int[n];
        StringBuilder sb = new StringBuilder();
        Stack<int[]> stack = new Stack<>();
        
        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int i = 0; i < n; i++) {
            tops[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = n - 1; i >= 0; i--) {


            if (stack.isEmpty()) {
                stack.push(new int[] {tops[i], i});
            }

            while (!stack.isEmpty() && stack.peek()[0] <= tops[i]) {
                int[] top = stack.pop();
                answer[top[1]] = i + 1;
            }

            stack.push(new int[]{tops[i], i});
        }

        while (!stack.isEmpty()) {
            answer[stack.pop()[1]] = 0;
        }

        for (int i = 0; i < n; i++) {
            sb.append(answer[i]).append(" ");
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();

    }

}