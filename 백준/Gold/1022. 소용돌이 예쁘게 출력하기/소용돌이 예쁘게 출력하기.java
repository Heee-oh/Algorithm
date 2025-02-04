import java.io.*;
import java.util.*;

public class Main {

    static final int INF = 100000000;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int r1 = Integer.parseInt(st.nextToken());
        int c1 = Integer.parseInt(st.nextToken());
        int r2 = Integer.parseInt(st.nextToken());
        int c2 = Integer.parseInt(st.nextToken());
        
        
        // 소용돌이 순서 오른쪽 - 위 - 왼쪽 - 아래 
        int[] dx = {1,  0, -1, 0};
        int[] dy = {0, -1,  0, 1};
        
        // 소용돌이를 출력할 배열 생성
        int[][] map = new int[r2- r1 + 1][c2 - c1 + 1];
        
        // 현 위치 
        int[] pos = {0, 0};
        int fullCheck = 0;
        int n = 1;
        
        if (checkBoundary(r2, pos, r1, c2, c1)) {
            map[pos[0] - r1][pos[1] - c1] = n;
            fullCheck++;
        }

        int moveDistance = 1; // 이동 길이
        int maxLengthNum = 1; // 최댓 값


        while (true) {

            if (fullCheck >= map.length * map[0].length) break;
            
            for (int j = 0; j < 2; j++) {
                for (int k = 0; k < moveDistance; k++) {
                    pos[0] += dy[j];
                    pos[1] += dx[j];
                    n++;

                    if (checkBoundary(r2, pos, r1, c2, c1)) {
                        map[pos[0] - r1][pos[1] - c1] = n;
                        maxLengthNum =  Math.max(maxLengthNum, n);
                        fullCheck++;
                    }

                }
            }

            moveDistance++;

            for (int j = 2; j < 4; j++) {
                for (int k = 0; k < moveDistance; k++) {
                    pos[0] += dy[j];
                    pos[1] += dx[j];
                    n++;

                    if (checkBoundary(r2, pos, r1, c2, c1)) {
                        map[pos[0] - r1][pos[1] - c1] = n;
                        maxLengthNum =  Math.max(maxLengthNum, n);
                        fullCheck++;
                    }

                }
            }

            moveDistance++;

        }

        int len = String.valueOf(maxLengthNum).length() + 1;



        for (int[] arr : map) {
            boolean flag = true;

            for (int num : arr) {
                bw.write(String.format("%" + (len + ( flag ? -1 : 0)) +"d",num));
                flag = false;

            }
            bw.write("\n");
        }


        bw.flush();
        bw.close();
    }

    //r2 >= 현재 행 >= r1 && c2 >= 현재 열 >= c1
    private static boolean checkBoundary(int r2, int[] pos, int r1, int c2, int c1) {
        return r2 >= pos[0] && r1 <= pos[0] && c2 >= pos[1] && c1  <= pos[1];
    }
}