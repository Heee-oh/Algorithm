import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        PriorityQueue<Integer> pq = new PriorityQueue<>();

        int max = 0;

        for (int i = 0; i < n; i++) {
            int loop = Integer.parseInt(br.readLine());
            pq.add(loop);
        }

        while (!pq.isEmpty()) {
            Integer loop1 = pq.poll();
            max = Math.max(loop1 * (pq.size() + 1), max);
        }

        System.out.println(max);



    }
}