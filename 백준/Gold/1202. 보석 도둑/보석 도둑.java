import java.io.*;
import java.util.*;
import java.util.stream.IntStream;

public class Main {

    static class Gem {
        int m, v;

        public Gem(int m, int v) {
            this.m = m;
            this.v = v;
        }

    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        Gem[] gems = new Gem[N];
        int[] back = new int[K];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int m = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            gems[i] = new Gem(m, v);
        }

        for (int i = 0; i < K; i++) {
            back[i] = Integer.parseInt(br.readLine());
        }

        // 무게 오름차순으로 정렬
        Arrays.sort(gems, (o1, o2) -> o1.m - o2.m);
        // 가방도 오름차순으로 정렬
        Arrays.sort(back);

        // 가격 내림차순으로 정렬
        PriorityQueue<Gem> pq = new PriorityQueue<>((o1, o2) -> o2.v - o1.v);

        int idx = 0;
        long answer = 0;
        for (int i = 0; i < K; i++) {
            // 해당 가방 무게로 담을 수 있는 모든 보석을 우선순위 큐에 담는다.
            while (idx < N && back[i] >= gems[idx].m) {
                pq.add(gems[idx]);
                idx++;
            }

            // 꺼낸 보석은 해당 가방 무게로 담을 수 있으면서 최대 가격을 갖는 보석이다.
            if (!pq.isEmpty()) {
                Gem target = pq.poll();
                answer += target.v;
            }
        }

        System.out.println(answer);

    }

}
