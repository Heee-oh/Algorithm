import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(br.readLine());
        int[] cards = new int[n];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            cards[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(cards);

        int m = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < m; i++) {
            int left = 0;
            int right = n - 1;
            int checkCard = Integer.parseInt(st.nextToken());
            boolean flag = true;

            while (left <= right) {

                int mid = (left + right) / 2;

                if (cards[mid] == checkCard) {
                    sb.append(1).append(" ");
                    flag = false;
                    break;
                }else if (cards[mid] > checkCard) {
                    right = mid - 1;
                }else {
                    left = mid + 1;
                }
            }

            if (flag) sb.append(0).append(" ");

        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }




}