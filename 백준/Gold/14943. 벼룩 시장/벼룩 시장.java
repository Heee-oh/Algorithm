import java.io.*;
import java.util.*;

public class Main {
    // 계산은 long

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        List<int[]> sellers = new ArrayList<>();
        List<int[]> customers = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            int l = Integer.parseInt(st.nextToken());

            if (l > 0) {
                sellers.add(new int[] {i, l});
            } else {
                customers.add(new int[]{i, l});
            }
        }


        // 가장 가까운 곳에 전부 다 파는게 이득
        int p1 = 0, p2 = 0; // 판매자, 고객 포인터
        long sum = 0;
        while (p1 < sellers.size()
                && p2 < customers.size()) {

            int[] seller = sellers.get(p1);
            int[] customer = customers.get(p2);

            int dist = Math.abs(seller[0] - customer[0]); // 판매자와 구매자의 거리
            int result = seller[1] + customer[1]; // 거래 후 나머지

            // 판매자가 고객보다 더 많이 갖고 있을 경우
            if (result > 0) {
                sum = sum + (long) dist * -1 * customer[1];
                seller[1] = result;
                p2++;

                // 고객이 더 많이 필요하거나 딱 맞는 경우
            } else {
                sum = sum + (long) dist * seller[1];
                customer[1] = result;
                p2 += (result == 0 ? 1 : 0); // 딱 맞을 경우는 p1 p2 둘다 올려줘야함
                p1++;

            }
        }

        System.out.println(sum);

    }
}
