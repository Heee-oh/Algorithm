import java.io.*;
import java.util.*;

public class Main {

    static long[] lanCables;
    static int n;
    static long maxCableLen = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int k = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());
        lanCables = new long[k];
        long max = 0;

        for (int i = 0; i < k; i++) {
            lanCables[i] = Integer.parseInt(br.readLine());
            max = Math.max(lanCables[i], max);
        }

        binarySearch(1, Integer.MAX_VALUE);

        bw.write(maxCableLen + "");
        bw.flush();
        bw.close();
    }

    private static void binarySearch(long left, long right) {


        if (left <= right) {
            long count = 0;
            long mid = (left + right) / 2;
            if (mid < 1) return;

            for (int i = 0; i < lanCables.length; i++) {
                count += (lanCables[i] / mid);
            }

            if (count >= n) {
                maxCableLen = Math.max(maxCableLen, mid);
                binarySearch(mid + 1, right);
            }else {
                binarySearch(left, mid - 1);
            }
        }
    }
}