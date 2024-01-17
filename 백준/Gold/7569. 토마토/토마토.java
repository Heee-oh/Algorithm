import java.io.*;
import java.util.*;

public class Main {

    static int[][][] graph;
    static boolean[][][] visited;
    static int M,N,H;

    static int[] updown = {0, 0, 1, -1};
    static int[] leftright = {1, -1, 0, 0};

    static int[] floor = {1, -1, 0};



    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());


        graph = new int[H][N][M];
        visited = new boolean[H][N][M];


        for (int i = 0; i < H; i++) {

            for (int j = 0; j < N; j++) {
                String line = br.readLine();
                line = line.replaceAll("-1", "2").replaceAll(" ", "");

                for (int k = 0; k < M; k++) {

                    int tomato = line.charAt(k) - '0';

                    graph[i][j][k] = (tomato == 2) ? -1 : tomato;

                }

            }
        }

        Queue<int[]> q = new LinkedList<>();

        for (int i = 0; i < H; i++) {
            for (int j = 0; j < N; j++) {
                for (int k = 0; k < M; k++) {

                    if (graph[i][j][k] == 1) {

                        q.add(new int[]{i,j,k});
                    }


                }
            }
        }


        bfs(q);

        int max = 0;
        for (int[][] height : graph) {
            for (int[] length : height) {
                for (int width : length) {

                    if (width == 0) {
                        bw.write("-1");
                        bw.flush();
                        bw.close();
                        return;

                    }
                    max = Math.max(max, width);

                }
            }
        }




        bw.write( max -1 + "");
        bw.flush();
        bw.close();
    }

    public static void bfs(Queue<int[]> q) {

        int h,n,m;


        while (!q.isEmpty()) {
            int[]node = q.poll();


            for (int i = 0; i < 3; i++) {
                h = node[0] + floor[i];
                
                
                
                if (h < 0 || h >= H) {

                    continue;
                }
                
                if (graph[h][node[1]][node[2]] == 0) {
                    graph[h][node[1]][node[2]] = graph[node[0]][node[1]][node[2]] + 1;
                    q.add(new int[]{h, node[1], node[2]});
                }
                

                for (int j = 0; j < 4; j++) {
                    n =  node[1] + updown[j];
                    m =  node[2] + leftright[j];

                    if (n < 0 || m < 0) {
                        continue;
                    }

                    if (n >= N || m >= M) {
                        continue;
                    }


                    if (graph[node[0]][n][m] == 0) {
                        graph[node[0]][n][m] = graph[node[0]][node[1]][node[2]] + 1;

                        q.add(new int[]{node[0], n, m});
                    }





                }
            }

        }


    }

}

