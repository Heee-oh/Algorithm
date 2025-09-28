import java.util.*;

class Solution {
    // a = b일 경우는 어피치가 k점
    // k 점을 여러발 맞춰도 k점만 가져감
    //  최종 점수가 같을 경우 어피치를 우승
    
    //라이언이 가장 큰 점수 차이로 우승이 목표 
    
    static int apeach = 0;
    static int[] arr = new int[11];
    static List<int[]> list = new ArrayList<>();
    static int max = 0;
    static int restArrow = 0;
    
    public int[] solution(int n, int[] info) {
        
        for (int i = 0; i < 11; i++) {
            apeach += info[i];
        }
        
        dfs(info, 0, 0, n);
        
        if (list.isEmpty()) {
            return new int[] {-1};
        }
        
        int[] answer = list.get(0); 
        for (int i = 1; i < list.size(); i++) {
            int[] tmp = list.get(i);
            for (int j = 10; j >= 0; j--) {
                
                if (answer[j] < tmp[j]) {
                    answer = tmp;
                    break;
                } else if (answer[j] > tmp[j]){
                    break;
                }
            }
        }
        
        int cnt = 0;
        int sum = 0, apeachSum = 0;
        for (int i = 0; i < answer.length; i++) {
            cnt += answer[i];
            if (answer[i] > info[i]) {
                sum += 10 - i;
            } else {
                apeachSum += info[i] > 0 ? 10 - i : 0;
            }
        }
        
        if (sum <= apeachSum) {
            return new int[] {-1};
        }
        
        if (cnt < n) {
            answer[10] += n - cnt;
        }
        
        
        return answer;
        // 라이언이 가장 큰 점수 차이로 우승할 수 있는 방법이 여러 가지 일 경우, 가장 낮은 점수를 더 많이 맞힌 경우
        //  라이언이 우승할 수 없는 경우(무조건 지거나 비기는 경우)는 [-1]을 return 
    }
    
    private void dfs(int[] info, int idx, int score, int arrow) {
        
        if (idx == 11) {
            return;
        }
        
        if (apeach < score) {
            if (max < score - apeach) {
                max = score - apeach;
                
                list.clear();
                list.add(arr.clone());
            } else if (max == score - apeach) {
                list.add(arr.clone());
            }
        }
        
        
        // idx 번째 수를 얻는 경우 
        if (info[idx] + 1 <= arrow) {
            
            if (info[idx] > 0) {
                apeach -= (10 - idx);
            }
            
            arr[idx] = info[idx] + 1;
            dfs(info, idx + 1, score + (10 - idx), arrow - info[idx] - 1);
            arr[idx] = 0;
            
            if (info[idx] > 0) {
                apeach += (10 - idx);
            }
        }
        
        // 얻지 않는 경우 
        dfs(info, idx + 1, score, arrow);
        
    }
}