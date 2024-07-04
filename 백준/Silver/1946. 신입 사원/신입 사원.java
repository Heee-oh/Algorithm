import java.io.*;
import java.util.*;

public class Main {


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        int t = Integer.parseInt(br.readLine());

        for (int i = 0; i < t; i++) {
            int n = Integer.parseInt(br.readLine());
            int[] rank = new int[n + 1];

            for (int j = 1; j <= n; j++) {
                st = new StringTokenizer(br.readLine());
                int index = Integer.parseInt(st.nextToken());
                rank[index] = Integer.parseInt(st.nextToken());


            }

            int count = 1;
            int min = rank[1];
            for (int j = 2; j <= n; j++) {
                boolean flag = true;
                if (rank[j] < min) {
                    min = rank[j];
                    count++;
                }

            }


            bw.write(count + "\n");
            bw.flush();


        }
        // 최소값은 1부터
        // 최대값은 9부터
        bw.close();
    }
}