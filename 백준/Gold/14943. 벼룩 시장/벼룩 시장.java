import java.io.*;
import java.sql.SQLOutput;
import java.util.*;

public class Main {


    // 계산은 long

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        List<int[]> seller = new ArrayList<>();
        List<int[]> customer = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            int l = Integer.parseInt(st.nextToken());

            if (l > 0) {
                seller.add(new int[] {i, l});
            } else {
                customer.add(new int[]{i, l});
            }
        }


        // 같은 거리라면 더 큰 곳에 다 투자
        int p1 = 0, p2 = 0;
        long sum = 0;
        while (p1 < seller.size() && p2 < customer.size()) {

            int[] sell = seller.get(p1);
            int[] custom = customer.get(p2);

            int dist = Math.abs(sell[0] - custom[0]);
            int result = sell[1] + custom[1];

            if (result > 0) {
                sum = sum + (long) dist * -1 * custom[1];
                seller.get(p1)[1] = result;
                p2++;

            } else if (result < 0){
                sum = sum + (long) dist * sell[1];
                customer.get(p2)[1] = result;
                p1++;

            } else {
                sum = sum + (long) dist * sell[1];
                p1++;
                p2++;
            }
        }

        System.out.println(sum);

    }




}
