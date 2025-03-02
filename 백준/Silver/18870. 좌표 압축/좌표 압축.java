import java.io.*;
import java.util.*;

public class Main {

    /**
     * 5
     * -12 -55 -44 -24 -10 50
     *
     */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        TreeSet<Integer> set = new TreeSet<>();

        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());

        int[] origin = new int[n];

        // 원본 좌표와, 정렬된 좌표들 저장
        for (int i = 0; i < n; i++) {
            origin[i] = Integer.parseInt(st.nextToken());
            set.add(origin[i]);
        }

        int[] coords = set.stream().mapToInt(Integer::intValue).toArray();


        for (int x : origin) {
            int front = 0, back = coords.length - 1;
            int compressedCoords = 0;

            while (front <= back) {

                int mid = (front + back) >>> 1;

                if (coords[mid] < x) {
                    front = mid + 1;
                } else {
                    compressedCoords = mid;
                    back = mid - 1;
                }
            }
            sb.append(compressedCoords).append(" ");
        }


        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
}