import java.util.*;

class Solution {
    // 참가자가 기다린 시간은 참가자가 상담 요청했을 때부터 멘토와 상담을 시작할 때까지의 시간입니다.
    // 먼저 상담 요청한 참가자가 우선됩니다.
    // 기다린 시간의 합이 최소가 되도록 각 상담 유형별 멘토 인원 정하기 
    // 각 유형별 적어도 1명 이상
    static class Req {
        int s,e;
        
        public Req(int s, int e) {
            this.s = s;
            this.e = e;
        }
        
    }
    
    static int answer = (int)1e8;
    public int solution(int k, int n, int[][] reqs) {
        
        // 각각 상담원이 몇명일때 기다리는 시간들을 구하자
        
        List<Req>[] types = new ArrayList[k+1];
        
        for (int i = 1; i <= k; i++) {
            types[i] = new ArrayList<>();
        }
        
        for (int[] req : reqs) {
            int reqTime = req[0];
            int duration = req[1];
            int type = req[2];
            
            types[type].add(new Req(reqTime, duration));
        }
        
        // 정렬 
        for (int i = 1; i <= k; i++) {
            Collections.sort(types[i], (a, b) -> a.s - b.s);
        }
        
        
        int[][] arr = new int[k+1][n - k + 2];
        
        
        // 각 유형에 대하여
        for (int i = 1; i <= k; i++) {
            
            // 배치 상담원 j명일 경우에 대하여 값 생성
            for (int j = 1; j <= n - k + 1; j++) {
                
                int[] mentos = new int[j];
                
                for (Req req : types[i]) {
                    
                    int idx = -1;
                    int waitTime = (int)1e7;
                    
                    // 대기해서 들어갈 상담원 부스 선택
                    for (int booth = 0; booth < j; booth++) {
                        
                        // 대기 없음
                        if (req.s >= mentos[booth]) {
                            mentos[booth] = req.s + req.e;
                            idx = -1;
                            break;
                            
                        } else {
                            int wait = mentos[booth] - req.s;
                            if (wait >= waitTime) continue;
                            waitTime = wait;
                            idx = booth;
                            
                        }
                    }
                    
                    // 대기가 있었다면 젤 적은 부스로 이동
                    if (idx != -1) {
                        arr[i][j] += waitTime;
                        mentos[idx] += req.e;
                    }
                    
                    
                }
                
            }
             
        }
        
        dfs(arr, n - k + 1, 1, 0);
        
        return answer;
    }
    
    
    private void dfs(int[][] arr, int n, int type, int sum) {
        // k-1개의 유형에 대하여 인원을 선택했으므로 나머지 k번째 유형은 나머지 인원을 다 받음 
        if (type == arr.length - 1) {
            answer = Math.min(answer, sum + arr[type][n]);
            return;
        }
        
        for (int i = 1; i <= n; i++) {
            dfs(arr, n - (i - 1), type + 1,  sum + arr[type][i]);
        }
    }
}