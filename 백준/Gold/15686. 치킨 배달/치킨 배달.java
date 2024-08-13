import java.io.*;
import java.util.*;

public class Main {

    // 치킨 거리 = 집과 가장 까가운 치킨집 사이의 거리
    // 집 기준, 집은 치킨거리를 가짐
    // 도시의 치킨 거리 = 모든 집의 치킨 거리의 합
    // 임의의 두 칸 (r1, c1)과 (r2, c2) 사이의 거리는 |r1-r2| + |c1-c2|

    // 0 빈칸 , 1 집 , 2 치킨 집
    // 집의 개수 <= 2N , 적어도 1개는 존재
    // M <= 치킨집의 개수 <= 13

    static class Location {
        int r, c;

        public Location(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
    static int m; // 치킨 집 개수
    static int chickenStreet = 0;
    static boolean[] visited;
    static ArrayList<Location> house = new ArrayList<>();
    static ArrayList<Location> chicken = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken()); // 크기 n x n
        m = Integer.parseInt(st.nextToken());

        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());

            for (int j = 1; j <= n; j++) {
                String info = st.nextToken();
                if (info.equals("1")) {
                    house.add(new Location(i, j));

                } else if (info.equals("2")) {
                    chicken.add(new Location(i, j));
                }
            }
        }

        visited = new boolean[chicken.size()];
        recursion(0, 0);

        bw.write(chickenStreet + "\n");
        bw.flush();
        bw.close();
    }

    private static void recursion(int depth, int start) {
        if (depth == m) {
            int tmp = 0;
            for (int i = 0; i < house.size(); i++) {
                int min = Integer.MAX_VALUE;
                Location home = house.get(i);

                for (int j = 0; j < chicken.size(); j++) {
                    if (visited[j]) {
                        Location chick = chicken.get(j);
                        int street = Math.abs(home.r - chick.r) + Math.abs(home.c - chick.c);
                        if (street < min)
                            min = street;
                    }

                }
                tmp += min;
            }

            if (chickenStreet == 0)
                chickenStreet = tmp;
            else
                chickenStreet = Math.min(tmp, chickenStreet);

            return;
        }

        for (int i = start; i < chicken.size(); i++) {
            if (!visited[i]) {
                visited[i] = true;
                recursion(depth + 1,i + 1);
                visited[i] = false;
            }
        }
    }
}