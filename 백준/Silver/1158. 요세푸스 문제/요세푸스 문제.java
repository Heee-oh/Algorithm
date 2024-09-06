import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        Queue<Integer> q = new LinkedList<>();
        StringBuilder sb = new StringBuilder();
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken()) - 1;

        for (int i = 1; i <= n; i++) {
            q.add(i);
        }

        sb.append("<");

        while (!q.isEmpty()) {
            for (int i = 0; i < k ; i++) {
                int tmp = q.poll();
                q.add(tmp);
            }
            sb.append(q.poll()).append(", ");
        }

        sb.delete(sb.length() - 2, sb.length());

        sb.append(">");
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }



}