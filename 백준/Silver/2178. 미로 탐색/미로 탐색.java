import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.StringTokenizer;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {

    static int[][] graph;

    static boolean cheked[][];
    static int N,M;

    static int[] leftright = {1, -1, 0, 0};
    static int[] updown = {0, 0, -1, 1};


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());



        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        graph = new int[N][M];
        cheked = new boolean[N][M];



        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            for (int j = 0; j < M; j++) {
                graph[i][j] = line.charAt(j) - '0';
            }
        }


        cheked[0][0] = true;


        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{0,0});


        while (!queue.isEmpty()) {
            int[] node = queue.poll();
            for (int i = 0; i < 4; i++) {


                int pre = graph[node[0]][node[1]];
                int x = node[0] + leftright[i];
                int y = node[1] + updown[i];


                if (x < 0 || y < 0) {
                    continue;
                }
                if (x >= N || y >= M) {
                    continue;
                }

                if (!cheked[x][y] && graph[x][y] == 1) {

                    cheked[x][y] = true;
                    queue.add(new int[]{x,y});
                    graph[x][y] = pre + 1;
                }
            }

        }

        System.out.println(graph[N-1][M-1 ]);

    }


}