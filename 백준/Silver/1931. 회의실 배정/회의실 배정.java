import java.io.*;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    static class Time implements Comparable<Time>{
        int start, end;

        public Time(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public int compareTo(Time t) {
            // 끝 시간이 같다면 시작 시간 오름차순, 아니라면 끝시간 오름차순
            return (end == t.end) ? start - t.start : end - t.end;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        PriorityQueue<Time> pq = new PriorityQueue<>();
        int n = Integer.parseInt(br.readLine());
        int lastTime = 0;
        int count = 1;

        // 회의 초기화
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            pq.add(new Time(start, end));
        }

        lastTime = pq.poll().end;

        while (!pq.isEmpty()) {
            Time time = pq.poll();
            if (lastTime <= time.start) {
                lastTime = time.end;
                count++;
            }
        }

        bw.write(count +"");
        bw.flush();
        bw.close();
    }

}