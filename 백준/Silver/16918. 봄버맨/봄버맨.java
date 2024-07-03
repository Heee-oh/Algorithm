import java.io.*;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int R = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());
        int nSeconds = Integer.parseInt(st.nextToken());
        int[] dx = {0, 0, -1, 1};
        int[] dy = {-1, 1, 0 , 0};
        boolean[][] visited = new boolean[R][C];
        char[][] graph = new char[R][C];




        for (int i = 0; i < R; i++) {
            graph[i] = br.readLine().toCharArray();
        }

//         2의 배수 초에는 항상 모든 폭탄이 가득참
        if (nSeconds % 2 == 0) {
            for (int i = 0; i < R; i++) {
                for (int j = 0; j < C; j++) {
                    bw.write('O');
                }
                bw.write("\n");
                bw.flush();
            }
            bw.close();
            return;
        }

        // 설치 후 무동작이기에 1초를 빼고 시작
        nSeconds--;

        while (nSeconds > 0) {
            
            // 0으로 채우고(1초) + 폭발시키기(1초) 
            nSeconds -= 2;

            // 폭팔한 곳은 0으로 채우기
            for (int i = 0; i < R; i++) {
                for (int j = 0; j < C; j++) {

                    if (graph[i][j] == 'O' && !visited[i][j] ) {
                        visited[i][j] = true;
                        for (int k = 0; k < 4; k++) {
                            int dX = j + dx[k];
                            int dY = i + dy[k];


                            if ((dX >= 0 && dX < C && dY >= 0 && dY < R) && !visited[dY][dX] ) {
                                if (graph[dY][dX] != 'O') {
                                    graph[dY][dX] = 'O';
                                    visited[dY][dX] = true;
                                }
                            }
                        }
                    }


                }

            }

            // 폭발 후 상황으로 만듦
            for (int i = 0; i < R; i++) {

                for (int j = 0; j < C; j++) {

                    if (graph[i][j] == '.') {
                        graph[i][j] = 'O';
                    }else {
                        graph[i][j] = '.';
                    }
                }

            }
            visited = new boolean[R][C];
        }
        
        for (char[] chars : graph) {
            for (char aChar : chars) {
                bw.write(aChar);
            }
            bw.write("\n");
            bw.flush();
        }

        bw.close();
        
    }

}