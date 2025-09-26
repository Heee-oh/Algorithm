import java.util.*;

class Solution {
    static boolean[][][] map;
    
    public int[][] solution(int n, int[][] build_frame) {
        map = new boolean[n+1][n+1][2]; // 0: 기둥, 1: 보
        
        for (int i = 0; i < build_frame.length; i++) {
            int x = build_frame[i][0];
            int y = build_frame[i][1];
            int a = build_frame[i][2]; // 0: 기둥, 1: 보
            int b = build_frame[i][3]; // 0: 삭제, 1: 설치
            
            if (b == 1) { // 설치
                if (canInstall(x, y, a, n)) {
                    map[y][x][a] = true;
                }
            } else { // 삭제
                map[y][x][a] = false;
                if (!isValidStructure(n)) {
                    map[y][x][a] = true; // 복원
                }
            }
        }
        
        // 결과 수집
        List<int[]> result = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= n; j++) {
                if (map[i][j][0]) { // 기둥
                    result.add(new int[]{j, i, 0});
                }
                if (map[i][j][1]) { // 보
                    result.add(new int[]{j, i, 1});
                }
            }
        }
        
        // 정렬 (x좌표 -> y좌표 -> 구조물 종류 순)
        result.sort((a, b) -> {
            if (a[0] != b[0]) return a[0] - b[0];
            if (a[1] != b[1]) return a[1] - b[1];
            return a[2] - b[2];
        });
        
        return result.toArray(new int[result.size()][]);
    }
    
    // 설치 가능한지 확인
    private boolean canInstall(int x, int y, int type, int n) {
        if (type == 0) { // 기둥 설치
            // 바닥 위이거나, 다른 기둥 위이거나, 보의 한쪽 끝 위
            return y == 0 || 
                   (y > 0 && map[y-1][x][0]) ||
                   (x > 0 && map[y][x-1][1]) ||
                   map[y][x][1];
        } else { // 보 설치
            // 한쪽 끝이 기둥 위이거나, 양쪽 끝이 다른 보와 연결
            return (y > 0 && map[y-1][x][0]) ||
                   (y > 0 && x < n && map[y-1][x+1][0]) ||
                   (x > 0 && x < n && map[y][x-1][1] && map[y][x+1][1]);
        }
    }
    
    // 전체 구조물이 유효한지 확인
    private boolean isValidStructure(int n) {
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= n; j++) {
                if (map[i][j][0] && !canInstall(j, i, 0, n)) {
                    return false;
                }
                if (map[i][j][1] && !canInstall(j, i, 1, n)) {
                    return false;
                }
            }
        }
        return true;
    }
}