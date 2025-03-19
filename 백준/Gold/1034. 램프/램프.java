import java.io.*;
import java.util.*;
import java.util.stream.IntStream;

public class Main {

    static class Lamp {
        String lamp;
        int cnt;

        public Lamp(String lamp, int cnt) {
            this.lamp = lamp;
            this.cnt = cnt;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        // 내림차순 정렬
        PriorityQueue<Lamp> pq = new PriorityQueue<>( (o1, o2) -> o2.cnt - o1.cnt);


        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        Map<String, Integer> map = new HashMap<>();

        for (int i = 0; i < n; i++) {
            String lamp = br.readLine();
            map.put(lamp, map.getOrDefault(lamp, 0) + 1);

        }

        int k = Integer.parseInt(br.readLine());

        for (String key : map.keySet()) {
            pq.add(new Lamp(key, map.get(key)));
        }

        while (!pq.isEmpty()) {
            Lamp row = pq.poll();

            int turnOffCnt = 0;
            for (int i = 0; i < row.lamp.length(); i++) {
                turnOffCnt += row.lamp.charAt(i) == '0' ? 1 : 0;
            }


            // 1로 다 켜진 행이 최대라는 의미
            if (turnOffCnt == 0) {
                if (k % 2 == 0) {
                    System.out.println(row.cnt);
                    return;
                }
                continue;
            }

            // k 개에서 1개를 제외한 Off상태인 0을 뺀다.
            // 한 열에서 k - off상태인 0의 개수 만큼 다 처리하고 나머지를 하나씩 킨다.
            int tmp = k - turnOffCnt + 1;
            if (tmp % 2 == 1) {
                System.out.println(row.cnt);
                return;
            }

            // 0이면 불가능함
        }

        System.out.println(0);
    }




}
