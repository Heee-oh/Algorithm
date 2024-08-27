import java.io.*;
import java.util.*;

public class Main {


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        int n = Integer.parseInt(br.readLine());

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            if (b % 2 == 0) {
                a *= a;
                b /= 2;
            }

            int num = 1;
            for (int j = 0; j < b; j++) {
                num *= a;
                num %= 10;
            }

            sb.append(num == 0 ? 10 : num).append("\n");
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

}