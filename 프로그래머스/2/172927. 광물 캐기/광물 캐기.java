class Solution {
    
    /** 최소 피로도, 완전탐색
    * 선택한 곡괭이는 터질때까지 사용
    * 광물 5개 캘시 곡괭이는 터짐
    * 광물은 주어진 순서대로
    * 광물 모두 캐거나, 곡괭이를 다 쓸때까지 
    **/ 
    
    // 0 : 다이아, 1 : 철 , 2: 돌
    int[][] fatigue = {{1, 1, 1}, {5, 1, 1}, {25, 5, 1}};
    int minFatigue = Integer.MAX_VALUE;
    
    public int solution(int[] picks, String[] minerals) {
        
        dfs(picks, minerals, 0, 0);
        
        // 5개를 돌리고, 다음으로 넘겨서 곡괭이 선택
        return minFatigue;
    }
    
    private void dfs(int[] picks, String[] minerals, int idx, int sumFatigue) {
        
        if (idx - 1 >= minerals.length
            || (picks[0] == 0 && picks[1] == 0 && picks[2] == 0)) {
            
            minFatigue = Math.min(minFatigue, sumFatigue);
            return;
        }
        
        for (int i = 0; i < 3; i++) {
            if (picks[i] == 0) continue;
            picks[i]--;
            int tmp = 0;
            
            for (int j = idx; j < idx + 5; j++) {
                if (minerals.length <= j) break;
                
                int mineralIdx = getMineralIdx(minerals[j]);
                tmp += fatigue[i][mineralIdx];
            }
            
            dfs(picks, minerals, idx + 5, sumFatigue + tmp);
            
            picks[i]++;
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