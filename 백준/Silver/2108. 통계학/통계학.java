import java.io.*;
import java.util.*;

public class Main {

    static int[] dp;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        int avg = 0, median = 0, mode = 0, idx = 0;

        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;

        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[8001];

        for (int i = 0; i < n; i++) {
            int num = Integer.parseInt(br.readLine());
            arr[num + 4000]++;
            avg += num;
            max = Math.max(max, num);
            min = Math.min(min, num);
            mode = Math.max(mode, arr[num + 4000]);
        }


        int count = 0;

        for (int i = min + 4000; i <= max + 4000; i++) {
            if (arr[i] != 0) {
                count+= arr[i];

                if (count >= (n + 1) / 2) {
                    median = i - 4000;
                    break;
                }
            }
        }

        boolean flag = false;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == mode) {
                if (flag) {
                    idx = i - 4000;
                    break;
                }

                idx = i - 4000;
                flag = true;
            }
        }

        sb.append(Math.round((float) avg / n)).append("\n");
        sb.append(median).append("\n");
        sb.append(idx).append("\n");
        sb.append(max - min);

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
}