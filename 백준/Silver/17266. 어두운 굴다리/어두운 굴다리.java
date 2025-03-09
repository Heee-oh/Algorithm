import java.io.*;
import java.util.*;

public class Main {
    // 1팀 = 6명 (상위 4명 점수 합)
    // 결승점을 통과한 순서대로 점수 받음
    // 가장 낮은 점수 팀이 우승
    // 6명 주자 참가못한 팀은 제외
    // 동점일 경우 5번째 사람 가장빨리 들어온 팀이 우승

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        Map<Integer, Integer> teamCheck = new HashMap<>();
        StringBuilder sb = new StringBuilder();
        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());

        // 다리 길이
        boolean[] bridge = new boolean[n + 1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            int lampIdx = Integer.parseInt(st.nextToken());
            list.add(lampIdx);
        }


        int front = 0, back = n;
        int min = 100001;

        while (front <= back) {
            int loadStart = 100001;
            int loadEnd = 0;

            int mid = (front + back) >>> 1;

            for (int lamp : list) {
                int left = lamp - mid;
                loadStart = Math.min(loadStart, left); // 맨 처음 시작부분 체크

                // 이전 길 끝부분이 현재 왼쪽 비친부분 안이면 갱신
                if (left <= loadEnd) {
                    loadEnd = lamp + mid;
                }
            }

            // 비치는 부분이 길시작(0)보다 작거나 같고 끝부분보다 더 많이 비추면 모든 길을 비추므로 높이를 줄여서 최솟값을 찾음
            if (loadStart <= 0 && n <= loadEnd ) {
                min =  mid;
                back = mid - 1;
                // 다 못 밝혔다면 높이를 키움
            } else {
                front = mid + 1;
            }
        }




        bw.write(min + "");
        bw.flush();
        bw.close();
    }

}