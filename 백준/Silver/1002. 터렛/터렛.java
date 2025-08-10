import java.beans.Customizer;
import java.io.*;
import java.util.*;

public class Main {


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());

        while (T-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x1 = Integer.parseInt(st.nextToken());
            int y1 = Integer.parseInt(st.nextToken());
            int r1 = Integer.parseInt(st.nextToken());

            int x2 = Integer.parseInt(st.nextToken());
            int y2 = Integer.parseInt(st.nextToken());
            int r2 = Integer.parseInt(st.nextToken());

            int d = (x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2);
            int r_sum = (r1 + r2) * (r1 + r2);
            int r_sub = (r1 - r2) * (r1 - r2);

            if (x1 == x2 && y1 == y2 && r1 == r2) {
                sb.append("-1\n");
            } else if (d > r_sum || d < r_sub) {
                sb.append("0\n");
            } else if (d == r_sum || d == r_sub) {
                sb.append("1\n");
            } else {
                sb.append("2\n");
            }
            continue;


        }

        System.out.print(sb.toString());
    }


}

