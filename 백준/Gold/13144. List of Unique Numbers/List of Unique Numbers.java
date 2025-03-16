import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] sequence = new int[n];

        for (int i = 0; i < n; i++) {
            sequence[i] = Integer.parseInt(st.nextToken());
        }

        Set<Integer> check = new HashSet<>();

        long answer = 0;
        int front = 0, back = 0;
        while (back < n) {

            while (check.contains(sequence[back])) {
                check.remove(sequence[front]);
                front++;
            }

            check.add(sequence[back]);
            answer += back - front + 1;
            back++;
        }


        bw.write(answer+"");
        bw.flush();
        bw.close();
    }
}
