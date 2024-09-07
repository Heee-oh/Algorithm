import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        PriorityQueue<Integer> pq = new PriorityQueue<>();

        pq.add(64);
        int bar = 0;
        int x = Integer.parseInt(br.readLine());

        while (!pq.isEmpty() && x < 64) {
            int left = pq.poll();
            int right = left / 2; // 버릴지 냅둘지 여부
            bar += right;

            if (bar == x){
                pq.add(right);
                break;
            }

            if (bar >= x) {
                bar -= right;
                pq.add(right);
            }else {
                pq.add(right);
                pq.add(right);
            }
        }


        bw.write(pq.size() + "");
        bw.flush();
        bw.close();
    }
}
