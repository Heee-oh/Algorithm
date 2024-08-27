import java.io.*;
import java.util.*;

public class Main {

    static class Planet {
        int x,y, r;

        public Planet(int x, int y, int cost) {
            this.x = x;
            this.y = y;
            this.r = cost;
        }

        public Planet(int x, int y) {
            this.y = y;
            this.x = x;
            this.r = 0;
        }

        @Override
        public String toString() {
            return "Planet{" +
                    "x=" + x +
                    ", y=" + y +
                    ", r=" + r +
                    '}';
        }
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());

        for (int i = 0; i < T; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            Planet start = new Planet(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
            Planet end = new Planet(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));

            int n = Integer.parseInt(br.readLine());
            Planet[] arr = new Planet[n];


            for (int j = 0; j < n; j++) {
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                int r = Integer.parseInt(st.nextToken());
                arr[j] = new Planet(x,y,r);
            }

            int count = 0;
            for (Planet planet : arr) {
                double startR = getDistance(planet, start);
                double endR = getDistance(planet, end);
                if ((planet.r > startR && planet.r <= endR) ||
                        (planet.r <= startR && planet.r > endR)) {
                    count++;
                }

            }

            sb.append(count).append("\n");
        }


        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    private static double getDistance(Planet planet, Planet dest) {
        return Math.sqrt(Math.pow(dest.x - planet.x, 2) + Math.pow(dest.y - planet.y, 2));
    }
}