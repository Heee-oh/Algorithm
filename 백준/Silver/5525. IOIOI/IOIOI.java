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

        String line = br.readLine();

        int answer = 0;
        int count = 0;
        for (int i = 0; i < m; i++) {

            if (i + 2 >= m) {
                break;
            }

            if (line.charAt(i) == 'I' && line.charAt(i+1) == 'O' && line.charAt(i+2) == 'I') {
                count++;
                i++;

                if (count == n) {
                    answer++;
                    count--;
                }

            } else {
                count = 0;
            }

        }

        bw.write(answer + "");
        bw.flush();
        bw.close();
    }

}