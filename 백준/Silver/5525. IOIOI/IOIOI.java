import java.io.*;
import java.util.*;

public class Main {

    static boolean[] visited;
    static boolean[][] graph;
    static int max = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder(br.readLine());


        int idx = (2 * n) + 1;

        String match = "IO".repeat(n) + "I";


        int answer = 0;

        for (int i = 0; i < m; i++) {

            if (i + idx > m) {
                break;
            }

            if (sb.charAt(i) == 'I') {
                if (sb.substring(i, i + idx).matches(match)) {
                    answer++;
                }
            }





        }


        bw.write(answer + "");
        bw.flush();
        bw.close();
    }

}