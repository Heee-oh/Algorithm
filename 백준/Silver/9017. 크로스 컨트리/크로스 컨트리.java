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
        int T = Integer.parseInt(br.readLine());

        for (int i = 0; i < T; i++) {
            int n = Integer.parseInt(br.readLine());
            StringTokenizer st = new StringTokenizer(br.readLine());
            int[][] scores = new int[201][3]; // 팀번호, [점수, 명수, 5번째 사람]
            int[] rank = new int[n];


            // 점수 및 6명 미만 팀 체크
            for (int j = 0; j < n; j++) {
                rank[j] = Integer.parseInt(st.nextToken());
                int value = teamCheck.getOrDefault(rank[j], 0) + 1;
                teamCheck.put(rank[j], value);
            }

            int score = 1;

            // 점수 계산
            for (int j = 0; j < n; j++) {
                if (teamCheck.get(rank[j]) < 6) continue;

                // 상위 4명까지 팀 점수에 합
                if (scores[rank[j]][1] < 4) {
                    scores[rank[j]][0] += score++;
                    scores[rank[j]][1]++;

                    // 5번째 선수 점수 기록
                } else if (scores[rank[j]][1] == 4) {
                    scores[rank[j]][2] = score++;
                    scores[rank[j]][1]++; // 중복 저장 피하기용
                } else {
                    score++;
                }
            }

            int min = Integer.MAX_VALUE;
            int runner5 = Integer.MAX_VALUE; // 5번째 선수의 점수
            int winnerTeam = 0;

            for (int team : teamCheck.keySet()) {
                if (teamCheck.get(team) < 6) continue;

                if (scores[team][0] == min) { // 스코어가 같다면 5번째 선수 비교
                    if (scores[team][2] < runner5) { // 현재 팀의 5번째 선수 점수가 현재 우승 팀 5번재 선수 점수보다 낮다면 우승 팀 변경
                        winnerTeam = team;
                        runner5 = scores[team][2];
                    }

                } else if (scores[team][0] < min) {
                    min = scores[team][0]; // 현재까지 우승 팀 스코어
                    winnerTeam = team; // 현재까지 우승 팀
                    runner5 = scores[team][2]; // 5번째 선수 점수 등록
                }
            }

            sb.append(winnerTeam).append("\n");
            teamCheck.clear();
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

}