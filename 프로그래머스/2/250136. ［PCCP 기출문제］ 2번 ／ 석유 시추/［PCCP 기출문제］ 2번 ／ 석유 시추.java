import java.util.*;

class Solution {
    private static int[][] graph;
    private static int[] upDown = {0, 0, 1, -1};
    private static int[] leftRight = {1, -1, 0, 0};

    public int solution(int[][] land) {
        graph = land;
        int number = 2;
        int maxAmount = 0;
        int tempAmount = 0;
        HashMap<Integer, Integer> map = new HashMap<>();
        Set<Integer> numberSet = new HashSet<>();
        int[] depth = new int[land[0].length];

        for (int col = 0; col < land[0].length; col++) {
            for (int row = 0; row < land.length; row++) {

                // 석유 덩어리 넘버링 및 개수 map에 저장
                if (graph[row][col] == 1) {
                    // 석유 덩이리인 number에 대한 값을 가짐
                    map.put(number, bfs(row,col,number,tempAmount));
                    number++;

                }
                // set에 한 열에 대한 덩어리 넘버링을 저장
                if (graph[row][col] > 1) {
                    numberSet.add(graph[row][col]);
                }

            }
            // 저장해뒀던 넘버링을 꺼내서 각 열에 저장
            for (Integer i : numberSet) {
                depth[col] += map.getOrDefault(i, 0);
            }

            numberSet.clear();
        }
        
        // int max = 0;
        // for (int ia : depth) {
        //     max = Math.max(max, ia);
        // }

        // return max;

        return Arrays.stream(depth)
                .max()
                .getAsInt();
    }

    public  int bfs(int row, int col, int number, int temp) {

        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{row, col});
        graph[row][col] = number;

        while (!q.isEmpty()) {
            int[] poll = q.poll();
            temp++;

            for (int i = 0; i < 4; i++) {
                int tempCol = poll[0] + upDown[i];
                int tempRow = poll[1] + leftRight[i];

                if (tempCol>= 0 && tempRow >= 0 && tempCol < graph.length && tempRow < graph[0].length) {
                    if(graph[tempCol][tempRow] == 1) {
                        q.add(new int[]{tempCol, tempRow});
                        graph[tempCol][tempRow] = number;
                    }

                }
            }
        }
        return temp;
    }
}
