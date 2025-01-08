class Solution {
    // 0 : 다이아, 1 : 철 , 2: 돌
    int[][] fatigue = {{1, 1, 1}, {5, 1, 1}, {25, 5, 1}};
    int minFatigue = Integer.MAX_VALUE;
    
    public int solution(int[] picks, String[] minerals) {
        
        dfs(picks, minerals, 0, 0);
        
        // 5개를 돌리고, 다음으로 넘겨서 곡괭이 선택
        return minFatigue;
    }
    
    // 백트래킹
    private void dfs(int[] picks, String[] minerals, int idx, int sumFatigue) {
        
        // 광물을 모두 캤거나, 곡괭이를 다썼다면 종료
        if (idx - 1 >= minerals.length
            || (picks[0] == 0 && picks[1] == 0 && picks[2] == 0)) {
            
            minFatigue = Math.min(minFatigue, sumFatigue);
            return;
        }

        // 곡괭이 별로 돌아가면서 탐색
        for (int i = 0; i < 3; i++) {
            if (picks[i] == 0) continue;
            int tmp = 0; // 임시 피로도 저장
            picks[i]--; // 곡괭이 사용
            
            for (int j = idx; j < idx + 5; j++) {
                if (minerals.length <= j) break; // 광물을 다 캤다면 멈추기
                
                int mineralIdx = getMineralIdx(minerals[j]);
                tmp += fatigue[i][mineralIdx];
            }
            
            // 다음 광물 캐기
            dfs(picks, minerals, idx + 5, sumFatigue + tmp);
            picks[i]++; // 곡괭이 회복 (백트래킹을 위해
        }
    }
    
    
        // 광물에 따른 인덱스 반환
    private int getMineralIdx(String mineral) {
        switch (mineral) {
            case "diamond": return 0;
            case "iron" : return 1;
            case "stone" : return 2;
        }
            
        return - 1;
    }
}