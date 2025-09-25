import java.util.*;


class Solution {
    static int[][] arr;
    static int weakLen;

    // 전부 점검할 수 없는 경우에는 -1
    public int solution(int n, int[] weak, int[] dist) {
        int answer = Integer.MAX_VALUE;
        weakLen = weak.length;
        
        arr = new int[weakLen][weakLen];
        
        for (int i = 0; i < weakLen; i++) {
            for (int j = 0; j < weakLen; j++) {
                int idx = (i + j) % weakLen;
                
                if (j == 0) {
                    arr[i][idx] = 0;
                }else {
                    int prev = (idx - 1 + weakLen) % weakLen;
                    if (idx == 0) {
                        arr[i][idx] = (weak[idx] - weak[prev] + n ) % n + arr[i][prev];
                    } else {
                        arr[i][idx] = (Math.abs(weak[prev] - weak[idx]) % n) + arr[i][prev];
                    }
                }
            }
        }
        
        
        for (int i = 0; i < weakLen; i++) {
            boolean[] visited = new boolean[weakLen];
            boolean[] isMoved = new boolean[dist.length];
            
            int cnt = 0;
            int idx = i;
            int select = i;
            int friend = dist.length - 1;
            int friendCnt = 0;
            
            while (cnt < weakLen) {
                boolean friendCheck = false;
            
                // 이동거리가 큰 친구중 아직 일하지 않은 사람 선택
                for (int j = dist.length - 1; j >= 0; j--) {
                    if (!isMoved[j]) {
                        friend = j;
                        friendCheck = true;
                        break;
                    }
                }
                
                // 모든 친구들이 일했다면 정지 
                if (!friendCheck) {
                    if (cnt != weakLen) {
                        friendCnt = Integer.MAX_VALUE;
                    }
                    break;
                }
                
                friendCnt++;                   
                cnt++;
                
                visited[idx] = true;
                
                // 현재 친구가 얼만큼 커버가 가능한지 
                while (true) {
                    if (cnt == weakLen) break;
                    
                    if (arr[select][(idx + 1) % weakLen] <= dist[friend]) {
                        idx = (idx + 1) % weakLen;
                        visited[idx] = true;
                        cnt++;
                    } else {
                        break;
                    }
                }
       
                
                // 더 작은 이동거리를 갖는 친구가 현 위치에서 최대이동이 가능한지 
                for (int j = friend; j >= 0; j--) {
                    if (!isMoved[j] && arr[i][idx] <= dist[j]) {
                        friend = j;
                    } 
                }
                
                isMoved[friend] = true;
                idx = (idx + 1) % weakLen;
                select = idx;
            }
            
            answer = Math.min(answer, friendCnt);
        }
        
        return answer == Integer.MAX_VALUE ? -1 : answer;
    }
    
//     private void dfs(int friend) {
        
//         if (set.size() == weakLen) {
//             min = Math.min(min, dists.length - (friend + 1));
//             return;
//         }
        
//         if (friend == -1) {
//             return;
//         } 
//         // 시작 지점 
//         for (int s = 0; s < weakLen; s++) {
//             if (set.contains(s)) continue;
//             int idx = s;
            
//             set.add(s);
//             stack.push(s);
//             int cnt = 1;
            
//             while (true) {
//                 if (cnt == weakLen) break;
                
//                 idx = (idx + 1) % weakLen;
//                 if (arr[s][idx] <= dists[friend]) {
//                     set.add(idx);
//                     stack.push(idx);
//                     cnt++;
                    
//                 } else {
//                     break;
//                 }
//             }
            
//             dfs(friend - 1);
            
//             while(cnt-->0) {
//                 int prev = stack.pop();
//                 set.remove(prev);
//             }
            
            
//         }
//     }
}