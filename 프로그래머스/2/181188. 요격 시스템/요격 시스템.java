import java.util.Arrays;

class Solution {
    // 2차원 
    // A 공격 : x 축 평행 직선 형태 정수 쌍(s,e) 형태
    // B: 특정 x 좌표에서 y축에 수평 발사, 관통 (s, e) 인 s, e 는 요격 불가
      // 요격 미사일은 실수인 x좌표에서도 발사 가능 -> s, e 도 포함해서 그 사이값으로 요격

    // 문제 : 모든 폭격 미사일을 요격하기 위해 필요한 요격 미사일 수의 최솟값
    public int solution(int[][] targets) {
        boolean[] check = new boolean[targets.length];
        int answer = 0;

        // s 기준으로 오름차순 정렬, 같다면 e 기준으로 오름차순 정렬
        Arrays.sort(targets, (o1, o2) -> o1[0] - o2[0]);

        for (int i = 0; i < targets.length; i++) {
            // 요격당했다면 다음 미사일로
            if (check[i]) continue;

            int cutLine = targets[i][1];
            // 요격할 미사일들 선택
            for (int j = i + 1; j < targets.length; j++) {

                if (cutLine > targets[j][0]) {
                    cutLine = Math.min(cutLine, targets[j][1]);
                    check[j] = true;
                }else {
                    break;
                }   
            }

            answer++;
        }

        return answer;
    }
}