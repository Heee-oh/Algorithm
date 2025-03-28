import java.io.*;
import java.util.*;

public class Main {

    static int n;
    static int m;
    static StringBuilder sb = new StringBuilder();
    static StringBuilder answer = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        backtracking(1, 0);

        System.out.print(answer.toString());
    }


    private static void backtracking(int num, int depth) {

        if (depth == m) {
            sb.append("\n");
            answer.append(sb.toString());
            sb.deleteCharAt(sb.length() - 1);
            return;
        }
        for (int i = num; i <= n; i++) {

            sb.append(i).append(" ");
            backtracking(i, depth+1);
            sb.delete(sb.length() - 2, sb.length());

        }
    }
}
