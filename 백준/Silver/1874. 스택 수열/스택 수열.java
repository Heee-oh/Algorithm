import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;


public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        Stack<Integer> stack = new Stack<>();
        int n = Integer.parseInt(br.readLine());


        // 빼는 값에 대한 비교
        // top >= 수열 순번 값
        // sequence == 현재 빠진 값


        int currentN = 0;

        for (int i = 1; i < n + 1; i++) {
            int num = Integer.parseInt(br.readLine());

            if (currentN < num) {
                for (int j = currentN + 1; j <= num; j++) {
                    sb.append("+\n");
                    stack.push(j);
                }
                currentN = num;
            }

            if (stack.peek() == num) {
                sb.append("-\n");
                stack.pop();
            } else {
                bw.write("NO");
                bw.flush();
                bw.close();
                return;
            }

        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();

    }
}
