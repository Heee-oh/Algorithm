import java.io.*;
import java.util.*;

public class Main {
    // 이중 배열로 [366][1001]을 생성, 1~365일 동안 1000개의 중복 일정을 대비
    // 각 날짜의 0인덱스는 중복일정을 이어붙여서 체크 할 예정
    // 각 날짜의 최대 중복 수를 기록할 배열 생성
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> {
            if (o1[0] - o2[0] == 0) {
                return o2[1] - o1[1];
            }
            return o1[0] - o2[0];
        }); // 날짜들을 시작 날짜 순으로, 같다면 긴 일정 먼저

        boolean[][] calendar = new boolean[1001][367];
        int[] height = new int[366];

        int n = Integer.parseInt(br.readLine());

        // 우선순위 큐에 일정들을 넣는다.
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());

            pq.add(new int[] {start, end});
        }


        // 일정들을 날짜 배열에 넣어 처리
        while (!pq.isEmpty()) {
            int[] day = pq.poll();
            int start = day[0];
            int end = day[1];

            // 일정 채우기
            for (int i = 1; i < calendar.length; i++) {
                // 중첩 일정이 있으면 그 아래에 일정 표시
                if (!calendar[i][start]) {
                    for (int j = start; j <= end; j++) {
                        calendar[0][j] = true; // 0인덱스에 중첩 일정 단위를 기록
                        calendar[i][j] = true;
                        height[j] = i; // 높이 값 기록
                    }

                    break;
                }
            }
        }

        int sum = 0;
        int dayCnt = 0;
        int maxHeight = 1;


        // 0인덱스와 height을 사용하여 중복 일정 넓이와 높이를 구함
        for (int i = 1; i < calendar[0].length; i++) {
            if (calendar[0][i]) {
                maxHeight = Math.max(maxHeight, height[i]);
                dayCnt++;
            } else if (dayCnt != 0){
                sum += dayCnt * maxHeight;
                maxHeight = 1;
                dayCnt = 0;
            }
        }


        bw.write(sum + "");  // 출력 내용 작성
        bw.flush();
        bw.close();
    }
}