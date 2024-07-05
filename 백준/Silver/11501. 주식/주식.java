import java.io.*;
import java.util.*;


public class Main {
//    static boolean visited[][];
//    static int[] dx = {0, 0, -1, 1};
//    static int[] dy = {-1, 1, 0, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        HashMap<Integer, Integer> map = new HashMap<>();
        // 테스트 케이스
        int T = Integer.parseInt(br.readLine());

        while (T-- > 0) {
            int days = Integer.parseInt(br.readLine());
            StringTokenizer st = new StringTokenizer(br.readLine());
            int[] prices = new int[days];
            long sum = 0;
            int max = 0;

            // 날짜별 주가를 배열에 저장 하고 pq에 값을 저장하여 가장 큰 값이 먼저오게 만든다.
            for (int i = 0; i < days; i++) {
                prices[i] = Integer.parseInt(st.nextToken());
                map.put(prices[i], map.getOrDefault(prices[i], 0) + 1);
                pq.add(prices[i]);
            }

            max = pq.poll();

            for (int i = 0; i < days; i++) {

                if (prices[i] == max) {
                    if(!pq.isEmpty()) {
                        max = pq.poll();
                        map.put(prices[i], map.get(prices[i]) - 1);
                    }

                    while (!pq.isEmpty() && map.get(max) <= 0) {
                        max = pq.poll();
                    }


                } else if (max >= prices[i]) {

                    sum += (max - prices[i]);
                    map.put(prices[i], map.get(prices[i]) - 1);
                }
            }

            pq.clear();
            map.clear();
            bw.write(sum+"\n");
            bw.flush();

        }

        bw.close();

    }



}

