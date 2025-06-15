import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.PriorityQueue;

public class Main {

    static class EventTime implements Comparable<EventTime> {
        int startHour, startMinute;
        int endHour, endMinute;

        public EventTime(int startHour, int startMinute, int endHour, int endMinute) {
            this.startHour = startHour;
            this.startMinute = startMinute;
            this.endHour = endHour;
            this.endMinute = endMinute;
        }

        // 가장 빠른 순으로 정렬
        @Override
        public int compareTo(EventTime ev) {
            if (this.startHour != ev.startHour) {
                return this.startHour - ev.startHour;
            }
            return this.startMinute - ev.startMinute;
        }

    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        while (true) {
            int T = Integer.parseInt(br.readLine());
            if (T == 0) break;

            PriorityQueue<EventTime> pq = new PriorityQueue<>();
            for (int i = 0; i < T; i++) {
                String[] split = br.readLine().split("-");
                String[] start = split[0].split(":");
                String[] end = split[1].split(":");

                EventTime eventTime
                        = new EventTime(Integer.parseInt(start[0]), Integer.parseInt(start[1]),
                        Integer.parseInt(end[0]), Integer.parseInt(end[1]));

                pq.add(eventTime);
            }

            int endHour = -1;
            int endMinute = -1;
            boolean isConflict = false;
            while (!pq.isEmpty()) {
                EventTime time = pq.poll();

                if (endHour == -1) {
                    endHour = time.endHour;
                    endMinute = time.endMinute;
                    continue;
                }

                // 끝 시간 보다 다음 시간이 먼저일 경우, 다음 시작 분이 먼저일 경우
                if (time.startHour < endHour
                        || (time.startHour == endHour && time.startMinute < endMinute)) {
                    isConflict = true;
                    break;
                }
            }

            if (isConflict) {
                sb.append("conflict\n");
            } else {
                sb.append("no conflict\n");
            }
        }


        System.out.println(sb.toString());
    }





}
