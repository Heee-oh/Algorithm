import java.io.*;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main {
//
//    static int[] arr;
//    static int[] dp;

    static class Location {
        private int x;
        private int y;

        public Location(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st;

        Location[] arr = new Location[n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            arr[i] = new Location(x, y);
        }

        Arrays.sort(arr, new Comparator<>() {
            @Override
            public int compare(Location o1, Location o2) {
                if (o1.getX() == o2.getX()) {
                    return o1.getY() - o2.getY();
                }

                return o1.getX() - o2.getX();
            }
        });


        for (Location location : arr) {
            bw.write(location.getX() + " ");
            bw.write(location.getY()+ "\n");

        }
        bw.flush();
        bw.close();


    }


}