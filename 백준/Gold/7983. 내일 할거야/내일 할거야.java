import java.io.*;
import java.util.*;

public class Main {
    // 계산은 long

    static class Assigment implements Comparable<Assigment> {
        int d, t;

        public Assigment(int d, int t) {
            this.d = d;
            this.t = t;
        }

        @Override
        public int compareTo(Assigment o) {
            if (t == o.t) {
                return d - o.d;
            }

            return o.t - t;
        }
    }

    // A < C < B
    // 머리 배 가슴
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        PriorityQueue<Assigment> pq = new PriorityQueue<>();
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int d = Integer.parseInt(st.nextToken());
            int t = Integer.parseInt(st.nextToken());

            pq.add(new Assigment(d, t));
        }


        int preWorkstartDay = -1;
        while (!pq.isEmpty()) {
            Assigment current = pq.poll();

            // 작업 시작 시간 구하기
            int workday = current.t - current.d + 1;

            // 처음
            if (preWorkstartDay == -1) {
                preWorkstartDay = workday;
                continue;
            }

            // 이전 과제 시작 날이 현재 과제 마감일과 같거나 작다면
            if (preWorkstartDay <= current.t) {
                 preWorkstartDay = preWorkstartDay - 1 - current.d + 1;

            } else {
                preWorkstartDay = workday;
            }
        }


        System.out.println(preWorkstartDay - 1);


    }

}
