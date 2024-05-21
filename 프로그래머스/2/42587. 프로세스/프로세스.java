import java.util.Arrays;

class Solution {
    public int solution(int[] priorities, int location) {
        int[] max = priorities.clone();
        boolean[] check = new boolean[max.length];
        Arrays.sort(max);
        // 우선순위를 정렬한 배열을 얻고 max값을 지정
        
        // 해당 우선순위가 max와 같다면 max는 그 다음으로 큰 수를 지정
        int turn = 1, index = max.length-1;
        for(int i = 0; i < priorities.length * priorities.length; i++) {
            // 변수길이 조절
            int tmpIndex = i % priorities.length;
            // 현재 값이 max 우선순위와 같고, 아직 체크되지않은 위치라면
            if(priorities[tmpIndex] == max[index] && !check[tmpIndex]) {
                // 그 위치가 알고싶은 위치라면 탈출
                if (tmpIndex == location) break;
                // 체크 및 턴 +1 max는 그 다음으로 우선순위가 높은값 지정
                check[tmpIndex] = true;
                turn++;
                index--;
            }
        }
        

        return turn;
    }
}