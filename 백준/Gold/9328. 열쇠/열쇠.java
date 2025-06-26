import java.io.*;
import java.util.*;

public class Main {
    static int r, c;
    static int[] dy = {1, -1, 0, 0};
    static int[] dx = {0, 0, 1, -1};

    static char[][] map; // 맵
    static boolean[][] visited; // 방문 처리
    static List<int[]>[] doorList; // 문 리스트
    static Set<Character> ownKey; // 가지고 있는 키

    // 1. 빈공간 0,0 으로 bfs 탐색 기존 가지고있던 키로 문열기
    // 2. 새로얻은 키는 keyPQ에 저장
    // 3. 1번 탐색이 끝났다면 keyPQ를 이용해서 방 문 열며 탐색
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());

        while (T-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            r = Integer.parseInt(st.nextToken());
            c = Integer.parseInt(st.nextToken());

            doorList = new List['Z' - 'A' + 1];
            map = new char[r + 2][c + 2];
            visited = new boolean[r + 2][c + 2];
            ownKey = new HashSet<>();

            for (int i = 0; i < doorList.length; i++) {
                doorList[i] = new ArrayList<>();
            }

            // 빈 공간 채우기
            for (int i = 0; i < r+2; i++) {
                Arrays.fill(map[i], '.');
            }

            // 맵 입력
            for (int i = 1; i < r + 1; i++) {
                char[] ch = br.readLine().toCharArray();
                for (int j = 1; j < c + 1; j++) {
                    map[i][j] = ch[j - 1];
                }
            }

            // 키 입력
            for (char key : br.readLine().toCharArray()) {
                if (key == '0') break;
                ownKey.add(key);
            }


            sb.append(bfs()).append("\n");

        }

        System.out.print(sb.toString());
    }



    private static int bfs() {
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{0, 0});
        visited[0][0] = true;

        int cnt = 0;

        while (!q.isEmpty()) {
            int[] cur = q.poll();

            for (int i = 0; i < 4; i++) {
                int nextR = cur[0] + dy[i];
                int nextC = cur[1] + dx[i];

                if (nextR < 0 || nextR >= map.length
                        || nextC < 0 || nextC >= map[0].length
                        || visited[nextR][nextC] || map[nextR][nextC] == '*') continue;

                // 문이라면
                if ('A' <= map[nextR][nextC] && map[nextR][nextC] <= 'Z') {
                    char key = (char)(map[nextR][nextC] + 32);
                    int ch = map[nextR][nextC] - 'A';

                    // 키가 없다면 문 리스트에 저장
                    if (!ownKey.contains(key)) {
                        doorList[ch].add(new int[]{nextR, nextC});
                        continue; // 키가 없다면 넘어감
                    }


                    // 키라면
                } else if ('a' <= map[nextR][nextC] && map[nextR][nextC] <= 'z') {
                    ownKey.add(map[nextR][nextC]);

                    // 문서면 훔친다.
                } else if (map[nextR][nextC] == '$') {
                    cnt++;
                }

                map[nextR][nextC] = '.';
                visited[nextR][nextC] = true;
                q.add(new int[]{nextR, nextC});
            }


            // 얻은 새로운 키들로 탐색
            if (q.isEmpty()) {
                for (char key : ownKey) {
                    int idx = key - 'a';


                    if (doorList[idx].isEmpty()) continue;

                    for (int[] pos : doorList[idx]) {
                        int r = pos[0];
                        int c = pos[1];

                        if(visited[r][c]) continue;

                        map[r][c] = '.';
                        visited[r][c] = true;
                        q.add(new int[]{r, c});
                    }
                }
            }

        }

        return cnt;

    }






}
