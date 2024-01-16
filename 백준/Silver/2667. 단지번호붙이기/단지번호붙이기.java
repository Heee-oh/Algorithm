import java.io.*;
import java.util.*;

public class Main {

    static int[][] graph;
    static boolean[][] visited;
    static int V,count;

    static int[] UD = {0, 0, -1, 1};
    static int[] LR = {-1, 1, 0, 0};


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        V = Integer.parseInt(br.readLine());

        graph = new int[V][V];
        visited = new boolean[V][V];

        ArrayList<Integer> list = new ArrayList<>();

        // 그래프 초기화
        for (int i = 0; i < V; i++) {
            String line = br.readLine();
            for (int j = 0; j < V; j++) {
                graph[i][j] = line.charAt(j) - '0';
            }
        }

        // 순회하면서 새로운 단지 찾기
        for (int i = 0; i < V; i++) {
            for (int j = 0; j < V; j++) {
                if (!visited[i][j] && graph[i][j] == 1) {
                    list.add(bfs(i, j));
                }
            }
        }

        // 오름차순 정렬
        Collections.sort(list);

        // 출력
        // 단지 총 수
        System.out.println(list.size());

        // 각 단지 내 집의 수
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }
    }


    public static int bfs(int x, int y) {
        visited[x][y] = true;
        int count = 1;

        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{x, y});

        while (!queue.isEmpty()) {

            int[] node = queue.poll();
            for (int i = 0; i < 4; i++) {
                int next_x = node[0] + UD[i];
                int next_y = node[1] + LR[i];

                if (next_x < 0 || next_y < 0) {
                    continue;
                }

                if (next_x >= V || next_y >= V) {
                    continue;
                }


                if (!visited[next_x][next_y] && graph[next_x][next_y] == 1) {

                    queue.add(new int[]{next_x, next_y});
                    visited[next_x][next_y] = true;
                    count++;
                }



            }

        }
        return count;
    }


}


/**
 * 우선 그래프 배열에 다 넣고
 * 순회하면서 값이 1 && visited=false 이면 dfs에 보내면 될 듯
 * ArrayList에 값 넣어주고 나중에 sort 하면 될거같음. 아니면 우선순위 큐 같은 곳에 넣어도 되고
 * 우선순위 큐 넣어도 될듯?
 *
 *
 */