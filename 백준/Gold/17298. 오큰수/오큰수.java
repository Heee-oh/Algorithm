import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        ArrayList<Integer> list = new ArrayList<>();
        int n = Integer.parseInt(br.readLine());
        Stack<int[]> stack = new Stack<>();
        int[] arr = new int[n];
        int[] answer = new int[n];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        stack.push(new int[] {arr[0], 0});

        int idx = 0;
        for (int i = 1; i < n; i++) {
            while (!stack.isEmpty() && stack.peek()[0] < arr[i]) {
                int[] pop = stack.pop();
                list.add(arr[i]);

                answer[pop[1]] = arr[i];

                idx++;
            }
            stack.push(new int[] {arr[i], i});

        }


        while (!stack.isEmpty()) {
            int[] pop = stack.pop();
            answer[pop[1]] = -1;
        }

        for (int i : answer) {
            sb.append(i).append(" ");
        }

        bw.write(sb.deleteCharAt(sb.length() - 1).toString());
        bw.flush();
        bw.close();
    }
}