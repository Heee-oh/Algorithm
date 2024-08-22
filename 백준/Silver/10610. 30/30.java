import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        StringBuilder sb = new StringBuilder();
        String num = br.readLine();

        int sum = 0;
        boolean flag = false;
        for (int i = 0; i < num.length(); i++) {
            int n = num.charAt(i) - '0';
            if (n == 0) flag = true;
            pq.add(n);
            sum += n;
        }



        if (sum % 3 == 0 && flag) {
            while (!pq.isEmpty()) {
                sb.append(pq.poll());
            }

            bw.write(sb.toString());
        } else {
            bw.write("-1\n");
        }

        bw.flush();
        bw.close();
    }

}