import java.io.*;
import java.util.*;

public class Main {

    static int[] dxarr = {0,0,1,-1};
    static int[] dyarr = {1,-1,0,0};
    static int[][] visited; // 연합 그룹 번호도 마킹
    static HashMap<Integer, Integer> map = new HashMap<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int answer = 0;
        int n = Integer.parseInt(st.nextToken());
        int L = Integer.parseInt(st.nextToken());
        int R = Integer.parseInt(st.nextToken());
        int[][] lands = new int[n][n];
        visited = new int[n][n];

        // 땅 배열 값 초기화
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());

            for (int j = 0; j < n; j++) {
                lands[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int maxUnionNum = bfs(lands, L, R);

        while(maxUnionNum > 0) {

            for (int i = 0; i < lands.length; i++)  {
                for (int j = 0; j < lands[0].length; j++) {

                    if (visited[i][j] > 0) {
                        int tmp = map.getOrDefault(visited[i][j], 0);
                        if (tmp > 0)  lands[i][j] = tmp;

                    }
                }
            }
            answer++;
            visited = new int[n][n];
            maxUnionNum = bfs(lands, L, R);
        }


        bw.write(answer + "\n"); // 문자열 or 변수명+""
        // 백준은 값도 줄바꿈 처리되어있으면 해줘야함
        bw.flush();  //버퍼 비우기  이것을 계속 호출하면 시간초과 뜸 한번만 아니면 필요할때만 호
        bw.close(); // 종료
    }

    public static int bfs(int[][] land, int L, int R) {

        Queue<int[]> q = new LinkedList<>();
        int unionNum = 1;

        for (int i = 0; i < land.length; i++) {
            for (int j = 0; j < land[0].length; j++) {
                if (visited[i][j] == 0) {
                    q.add(new int[] {i,j});
                    visited[i][j] = unionNum++;
                    int count = 0; // 연합이 없는 땅 체크
                    int popular = 0;
                    // 연합 땅 번호 새기기
                    while(!q.isEmpty()) {
                        int[] landPoint = q.poll();

//                        System.out.println("i : " + i + " j = " + j);
                        popular += land[landPoint[0]][landPoint[1]];



                        for (int k = 0; k < 4; k++) {
                            int dy = landPoint[0] + dyarr[k]; // 행
                            int dx = landPoint[1] + dxarr[k]; // 열

                            if (dx >= 0 && dx < land[0].length && dy >= 0 && dy < land.length) {
                                if (visited[dy][dx] == 0) {
                                    int gap = land[landPoint[0]][landPoint[1]] - land[dy][dx];
                                    if (gap < 0) gap *= -1;

                                    if (gap < L || gap > R) continue; // 인구수 차가 L ~ R이 아니면 다음땅으로
                                    visited[dy][dx] = visited[i][j];
                                    q.add(new int[] {dy, dx});

                                    count++;
                                }
                            }
                        }
                    }

                    if (count == 0){
                        unionNum--; // 연합이 없다면 연합번호 -1
                        visited[i][j] = 0;
                    }else {
                        map.put(visited[i][j], (popular / (count + 1)));
                    }

                }


            }
        }

        return unionNum - 1;
    }

}


