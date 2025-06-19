import java.io.*;
import java.util.*;

public class Main {
    static class Line implements Comparable<Line> {
        int start, end;

        public Line(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public int compareTo(Line o) {
            return Integer.compare(this.start, o.start);
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;
            Line line = (Line) obj;
            return start == line.start && end == line.end;
        }

        @Override
        public int hashCode() {
            return Objects.hash(start, end);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int n = Integer.parseInt(br.readLine());
        Line[] lines = new Line[n];

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            lines[i] = new Line(a, b);
        }

        Arrays.sort(lines);

        Line[] LIS = new Line[n];
        int[] dp = new int[n];         // LIS의 위치 저장
        int[] prev = new int[n];       // LIS 추적용
        Arrays.fill(prev, -1);

        LIS[0] = lines[0];
        dp[0] = 0;
        int len = 1;

        for (int i = 1; i < n; i++) {
            if (LIS[len - 1].end < lines[i].end) {
                prev[i] = dp[len - 1];
                LIS[len] = lines[i];
                dp[len] = i;
                len++;
            } else {
                int left = 0, right = len - 1;
                while (left < right) {
                    int mid = (left + right) >>> 1;
                    if (LIS[mid].end < lines[i].end) left = mid + 1;
                    else right = mid;
                }
                LIS[left] = lines[i];
                dp[left] = i;
                if (left > 0) prev[i] = dp[left - 1];
            }
        }

        // LIS에 포함된 요소 추적
        boolean[] isInLIS = new boolean[n];
        int idx = dp[len - 1];
        while (idx != -1) {
            isInLIS[idx] = true;
            idx = prev[idx];
        }

        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int i = 0; i < n; i++) {
            if (!isInLIS[i]) pq.add(lines[i].start);
        }

        sb.append(pq.size()).append("\n");
        while (!pq.isEmpty()) {
            sb.append(pq.poll()).append("\n");
        }

        System.out.print(sb);
    }
}
