import java.io.*;
import java.util.*;

public class Main {
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int p = Integer.parseInt(br.readLine());

        for (int i = 0; i < p; i++) {
            int[] students = new int[21];
            StringTokenizer st = new StringTokenizer(br.readLine());
            students[0] = Integer.parseInt(st.nextToken());
            students[1] = Integer.parseInt(st.nextToken());

            int answer = 0;
            for (int j = 2; j < 21; j++) {
                int n = Integer.parseInt(st.nextToken());
                int cnt = checkHeight(students, n, j);

                if (cnt == 0) {
                    students[j] = n;
                }
                answer += cnt;

            }
            bw.write(students[0] + " " + answer + "\n");
        }
        bw.flush();
        bw.close();
    }

    private static int checkHeight(int[] students, int target, int size) {

        for (int i = 1; i < size; i++) {
            if (students[i] > target) {

                for (int j = size; j >= i; j--) {
                    students[j] = students[j - 1];
                }
                students[i] = target;
                return size - i;
            }
        }

        return 0;
    }
}


